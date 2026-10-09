package com.example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.BrandProgressEntity
import com.example.data.QuizDatabase
import com.example.data.QuizRepository
import com.example.data.UserProfileEntity
import com.example.model.BrandCatalog
import com.example.model.BrandItem
import com.example.model.LeaderboardEntry
import com.example.ui.components.KeyboardTile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Random

sealed interface Screen {
    data class Game(val brandId: Int, val isDaily: Boolean = false) : Screen
    data object LevelSelect : Screen
    data object DailyChallenge : Screen
    data object Leaderboard : Screen
    data object Settings : Screen
}

class QuizViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: QuizRepository
    private val database = QuizDatabase.getDatabase(application)

    init {
        repository = QuizRepository(database.quizDao())
        viewModelScope.launch {
            repository.ensureProfile()
        }
        observeData()
    }

    private val _currentScreen = MutableStateFlow<Screen>(Screen.LevelSelect)
    val currentScreen: StateFlow<Screen> = _currentScreen.asStateFlow()

    private val _userProfile = MutableStateFlow<UserProfileEntity?>(null)
    val userProfile: StateFlow<UserProfileEntity?> = _userProfile.asStateFlow()

    private val _progressMap = MutableStateFlow<Map<Int, BrandProgressEntity>>(emptyMap())
    val progressMap: StateFlow<Map<Int, BrandProgressEntity>> = _progressMap.asStateFlow()

    // Active Game State
    private val _currentBrand = MutableStateFlow<BrandItem?>(null)
    val currentBrand: StateFlow<BrandItem?> = _currentBrand.asStateFlow()

    private val _isDailyMode = MutableStateFlow(false)
    val isDailyMode: StateFlow<Boolean> = _isDailyMode.asStateFlow()

    private val _placedLetters = MutableStateFlow<Map<Int, Char>>(emptyMap())
    val placedLetters: StateFlow<Map<Int, Char>> = _placedLetters.asStateFlow()

    private val _tilePlacementMap = MutableStateFlow<Map<Int, Int>>(emptyMap()) // slotIndex -> tileId

    private val _keyboardTiles = MutableStateFlow<List<KeyboardTile>>(emptyList())
    val keyboardTiles: StateFlow<List<KeyboardTile>> = _keyboardTiles.asStateFlow()

    private val _revealedIndices = MutableStateFlow<Set<Int>>(emptySet())
    val revealedIndices: StateFlow<Set<Int>> = _revealedIndices.asStateFlow()

    private val _isError = MutableStateFlow(false)
    val isError: StateFlow<Boolean> = _isError.asStateFlow()

    private val _isVictoryDialogVisible = MutableStateFlow(false)
    val isVictoryDialogVisible: StateFlow<Boolean> = _isVictoryDialogVisible.asStateFlow()

    private val _victoryCoinsEarned = MutableStateFlow(15)
    val victoryCoinsEarned: StateFlow<Int> = _victoryCoinsEarned.asStateFlow()

    // Cloud Save Sync Feedback
    private val _cloudSaveCode = MutableStateFlow<String?>(null)
    val cloudSaveCode: StateFlow<String?> = _cloudSaveCode.asStateFlow()

    private val _cloudMessage = MutableStateFlow<String?>(null)
    val cloudMessage: StateFlow<String?> = _cloudMessage.asStateFlow()

    private fun observeData() {
        viewModelScope.launch {
            repository.userProfileFlow.collectLatest {
                _userProfile.value = it
            }
        }
        viewModelScope.launch {
            repository.allProgressFlow.collectLatest { list ->
                _progressMap.value = list.associateBy { it.brandId }
            }
        }
    }

    fun navigateTo(screen: Screen) {
        _currentScreen.value = screen
        if (screen is Screen.Game) {
            loadBrand(screen.brandId, screen.isDaily)
        }
    }

    fun loadBrand(brandId: Int, isDaily: Boolean = false) {
        val brand = BrandCatalog.getBrandById(brandId) ?: BrandCatalog.allBrands.first()
        _currentBrand.value = brand
        _isDailyMode.value = isDaily
        _isError.value = false
        _isVictoryDialogVisible.value = false
        _placedLetters.value = emptyMap()
        _tilePlacementMap.value = emptyMap()

        viewModelScope.launch {
            val progress = repository.getProgressForBrand(brandId)
            val revealed = if (progress.revealedIndices.isNotBlank()) {
                progress.revealedIndices.split(",").mapNotNull { it.toIntOrNull() }.toSet()
            } else {
                emptySet()
            }
            _revealedIndices.value = revealed

            val removedChars = if (progress.removedLetters.isNotBlank()) {
                progress.removedLetters.split(",").mapNotNull { it.firstOrNull() }.toSet()
            } else {
                emptySet()
            }

            // Generate 14 keyboard tiles
            val tiles = generateKeyboardTiles(brand.name, removedChars)
            _keyboardTiles.value = tiles

            // Auto-place already revealed letters
            val preplaced = mutableMapOf<Int, Char>()
            revealed.forEach { idx ->
                if (idx < brand.name.length && brand.name[idx] != ' ') {
                    preplaced[idx] = brand.name[idx]
                }
            }
            _placedLetters.value = preplaced

            if (progress.isSolved) {
                // If already solved, fill all letters
                val allSlots = mutableMapOf<Int, Char>()
                brand.name.forEachIndexed { i, c ->
                    if (c != ' ') allSlots[i] = c
                }
                _placedLetters.value = allSlots
            }
        }
    }

    private fun generateKeyboardTiles(brandName: String, removedChars: Set<Char>): List<KeyboardTile> {
        val cleanName = brandName.filter { it != ' ' }.uppercase()
        val lettersList = cleanName.toMutableList()

        // Needed total tiles: 14 (2 rows of 7)
        val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val random = Random(brandName.hashCode().toLong())

        while (lettersList.size < 14) {
            val decoy = alphabet[random.nextInt(alphabet.length)]
            lettersList.add(decoy)
        }

        // Shuffle deterministically based on brand
        lettersList.shuffle(random)

        return lettersList.mapIndexed { index, char ->
            KeyboardTile(
                id = index,
                char = char,
                isPlaced = false,
                isEliminated = removedChars.contains(char)
            )
        }
    }

    fun onKeyboardTileClicked(tile: KeyboardTile) {
        val brand = _currentBrand.value ?: return
        if (tile.isPlaced || tile.isEliminated) return

        val currentPlaced = _placedLetters.value.toMutableMap()
        val currentTileMap = _tilePlacementMap.value.toMutableMap()

        // Find next empty slot
        var targetSlot: Int? = null
        for (i in brand.name.indices) {
            if (brand.name[i] != ' ' && !currentPlaced.containsKey(i)) {
                targetSlot = i
                break
            }
        }

        if (targetSlot != null) {
            currentPlaced[targetSlot] = tile.char
            currentTileMap[targetSlot] = tile.id

            _placedLetters.value = currentPlaced
            _tilePlacementMap.value = currentTileMap

            // Mark tile placed
            _keyboardTiles.value = _keyboardTiles.value.map {
                if (it.id == tile.id) it.copy(isPlaced = true) else it
            }

            checkAnswerSubmission()
        }
    }

    fun onAnswerSlotClicked(slotIndex: Int) {
        // Cannot remove revealed hint letters
        if (_revealedIndices.value.contains(slotIndex)) return

        val currentPlaced = _placedLetters.value.toMutableMap()
        val currentTileMap = _tilePlacementMap.value.toMutableMap()

        val tileId = currentTileMap[slotIndex]
        currentPlaced.remove(slotIndex)
        currentTileMap.remove(slotIndex)

        _placedLetters.value = currentPlaced
        _tilePlacementMap.value = currentTileMap
        _isError.value = false

        if (tileId != null) {
            _keyboardTiles.value = _keyboardTiles.value.map {
                if (it.id == tileId) it.copy(isPlaced = false) else it
            }
        }
    }

    private fun checkAnswerSubmission() {
        val brand = _currentBrand.value ?: return
        val currentPlaced = _placedLetters.value

        // Check if all slots filled
        val nonSpaceIndices = brand.name.indices.filter { brand.name[it] != ' ' }
        val isComplete = nonSpaceIndices.all { currentPlaced.containsKey(it) }

        if (isComplete) {
            val constructed = StringBuilder()
            for (i in brand.name.indices) {
                if (brand.name[i] == ' ') {
                    constructed.append(' ')
                } else {
                    constructed.append(currentPlaced[i] ?: ' ')
                }
            }

            if (constructed.toString().equals(brand.name, ignoreCase = true)) {
                // Correct!
                onBrandCorrectlySolved(brand)
            } else {
                // Wrong!
                _isError.value = true
            }
        }
    }

    private fun onBrandCorrectlySolved(brand: BrandItem) {
        viewModelScope.launch {
            if (_isDailyMode.value) {
                val (coins, streak) = repository.completeDailyChallenge(brand.id)
                _victoryCoinsEarned.value = coins
            } else {
                repository.markBrandSolved(brand.id, scoreReward = 100, coinReward = 15)
                _victoryCoinsEarned.value = 15
            }
            _isError.value = false
            _isVictoryDialogVisible.value = true
        }
    }

    fun dismissVictoryDialog() {
        _isVictoryDialogVisible.value = false
    }

    fun nextBrand() {
        _isVictoryDialogVisible.value = false
        val brand = _currentBrand.value ?: return
        val nextId = if (brand.id < BrandCatalog.allBrands.size) brand.id + 1 else 1
        loadBrand(nextId, false)
    }

    // Hint: Expose a Letter (15 coins)
    fun useHintExposeLetter() {
        val brand = _currentBrand.value ?: return
        val currentRevealed = _revealedIndices.value

        // Find unrevealed slot
        val availableSlots = brand.name.indices
            .filter { brand.name[it] != ' ' && !currentRevealed.contains(it) }

        if (availableSlots.isEmpty()) return

        val slotToReveal = availableSlots.first()
        val correctChar = brand.name[slotToReveal]

        viewModelScope.launch {
            val success = repository.revealLetter(brand.id, slotToReveal, cost = 15)
            if (success) {
                val newRevealed = currentRevealed + slotToReveal
                _revealedIndices.value = newRevealed

                val currentPlaced = _placedLetters.value.toMutableMap()
                currentPlaced[slotToReveal] = correctChar
                _placedLetters.value = currentPlaced

                checkAnswerSubmission()
            }
        }
    }

    // Hint: Remove Decoy Letters (25 coins)
    fun useHintRemoveDecoys() {
        val brand = _currentBrand.value ?: return
        val brandChars = brand.name.filter { it != ' ' }.toSet()

        // Decoys currently in keyboard that aren't eliminated yet
        val decoys = _keyboardTiles.value
            .filter { !brandChars.contains(it.char) && !it.isEliminated }
            .map { it.char }
            .take(3)

        if (decoys.isEmpty()) return

        viewModelScope.launch {
            val success = repository.removeDecoyLetters(brand.id, decoys, cost = 25)
            if (success) {
                _keyboardTiles.value = _keyboardTiles.value.map {
                    if (decoys.contains(it.char)) it.copy(isEliminated = true, isPlaced = false) else it
                }
            }
        }
    }

    // Hint: Solve Instantly (50 coins)
    fun useHintSolveBrand() {
        val brand = _currentBrand.value ?: return
        viewModelScope.launch {
            val success = repository.solveBrandInstantly(brand.id, cost = 50)
            if (success) {
                val fullSlots = mutableMapOf<Int, Char>()
                brand.name.forEachIndexed { i, c ->
                    if (c != ' ') fullSlots[i] = c
                }
                _placedLetters.value = fullSlots
                _victoryCoinsEarned.value = 0
                _isVictoryDialogVisible.value = true
            }
        }
    }

    // Cloud Saves
    fun exportCloudSave() {
        viewModelScope.launch {
            val code = repository.generateCloudSaveCode()
            _cloudSaveCode.value = code
            _cloudMessage.value = "Cloud Save Code generated! Copy or share it to restore progress on any device."
        }
    }

    fun importCloudSave(code: String) {
        viewModelScope.launch {
            val result = repository.restoreFromCloudSave(code)
            result.onSuccess { msg ->
                _cloudMessage.value = msg
                _cloudSaveCode.value = null
            }.onFailure { err ->
                _cloudMessage.value = err.message
            }
        }
    }

    fun clearCloudMessage() {
        _cloudMessage.value = null
    }

    fun updateUsername(name: String) {
        viewModelScope.launch {
            repository.updateUsername(name)
        }
    }

    fun getLeaderboardLists(): Pair<List<LeaderboardEntry>, List<LeaderboardEntry>> {
        val user = _userProfile.value
        val solved = _progressMap.value.values.count { it.isSolved }
        return repository.getLeaderboards(user, solved)
    }
}
