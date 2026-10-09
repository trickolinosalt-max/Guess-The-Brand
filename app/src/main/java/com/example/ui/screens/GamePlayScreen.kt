package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.BrandCatalog
import com.example.ui.components.AnswerSlotsRow
import com.example.ui.components.CategoryRibbonBanner
import com.example.ui.components.HintsBar
import com.example.ui.components.LetterKeyboard
import com.example.ui.components.VictoryFanfareDialog
import com.example.ui.theme.GameGold
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate800
import com.example.ui.theme.Slate900
import com.example.util.ShareHelper
import com.example.util.SoundAndHapticHelper
import com.example.viewmodel.QuizViewModel
import com.example.viewmodel.Screen

@Composable
fun GamePlayScreen(
    viewModel: QuizViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    BackHandler {
        viewModel.navigateTo(Screen.LevelSelect)
    }

    val currentBrand by viewModel.currentBrand.collectAsState()
    val isDailyMode by viewModel.isDailyMode.collectAsState()
    val placedLetters by viewModel.placedLetters.collectAsState()
    val keyboardTiles by viewModel.keyboardTiles.collectAsState()
    val revealedIndices by viewModel.revealedIndices.collectAsState()
    val isError by viewModel.isError.collectAsState()
    val isVictoryDialogVisible by viewModel.isVictoryDialogVisible.collectAsState()
    val victoryCoinsEarned by viewModel.victoryCoinsEarned.collectAsState()
    val userProfile by viewModel.userProfile.collectAsState()

    var showHintClue by remember { mutableStateOf(false) }

    val brand = currentBrand ?: return
    val userCoins = userProfile?.coins ?: 120

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0F172A),
                        Color(0xFF1E293B),
                        Color(0xFF0F172A)
                    )
                )
            )
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // TOP BAR
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { viewModel.navigateTo(Screen.LevelSelect) },
                    modifier = Modifier.testTag("back_button")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }

                // Level indicator
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = if (isDailyMode) "DAILY CHALLENGE" else "BRAND #${brand.id}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 1.sp,
                        color = if (isDailyMode) GameGold else Color.White
                    )
                    Text(
                        text = "${brand.difficulty} • LEVEL ${brand.levelNumber}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF94A3B8)
                    )
                }

                // Coins pill
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Slate800)
                        .border(1.dp, GameGold, RoundedCornerShape(16.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.MonetizationOn,
                        contentDescription = "Coins",
                        tint = GameGold,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "$userCoins",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            // BRAND CARD WITH UPLOADED 9-PATCH CATEGORY RIBBON
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.92f)
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Slate800),
                border = androidx.compose.foundation.BorderStroke(1.dp, Slate700)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 14.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Category Ribbon Banner matching sub_menu_bg.9.png!
                    CategoryRibbonBanner(
                        categoryText = brand.category,
                        modifier = Modifier.padding(top = 4.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Clean Logo Box
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .shadow(elevation = 6.dp, shape = RoundedCornerShape(16.dp))
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White)
                            .border(1.5.dp, Color(0xFFE2E8F0), RoundedCornerShape(16.dp))
                            .padding(14.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = brand.logoRes),
                            contentDescription = "Brand Logo",
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    if (brand.hint.isNotBlank()) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { showHintClue = !showHintClue }
                                .padding(horizontal = 8.dp, vertical = 2.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = "Clue",
                                tint = Color(0xFF60A5FA),
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = if (showHintClue) brand.hint else "Tap for clue",
                                fontSize = 11.sp,
                                color = if (showHintClue) Color(0xFF93C5FD) else Color(0xFF94A3B8)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            // ANSWER SLOTS
            AnswerSlotsRow(
                targetName = brand.name,
                placedLetters = placedLetters,
                revealedIndices = revealedIndices,
                isError = isError,
                onSlotClick = { slotIndex ->
                    SoundAndHapticHelper.vibrateTap(context)
                    viewModel.onAnswerSlotClicked(slotIndex)
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            // HINTS BAR
            HintsBar(
                coins = userCoins,
                onExposeLetterClick = {
                    SoundAndHapticHelper.vibrateHint(context)
                    viewModel.useHintExposeLetter()
                },
                onRemoveDecoysClick = {
                    SoundAndHapticHelper.vibrateHint(context)
                    viewModel.useHintRemoveDecoys()
                },
                onSolveBrandClick = {
                    SoundAndHapticHelper.vibrateSuccess(context)
                    viewModel.useHintSolveBrand()
                },
                onAskFriendClick = {
                    val availableLetters = keyboardTiles
                        .filter { !it.isEliminated }
                        .map { it.char }
                        .joinToString(", ")
                    ShareHelper.shareAskForHelp(
                        context = context,
                        category = brand.category,
                        scrambledLetters = availableLetters,
                        length = brand.name.replace(" ", "").length
                    )
                }
            )

            // TACTILE LETTER KEYBOARD
            LetterKeyboard(
                tiles = keyboardTiles,
                onTileClick = { tile ->
                    SoundAndHapticHelper.vibrateTap(context)
                    viewModel.onKeyboardTileClicked(tile)
                }
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        // VICTORY DIALOG
        if (isVictoryDialogVisible) {
            SoundAndHapticHelper.vibrateSuccess(context)
            VictoryFanfareDialog(
                brand = brand,
                coinsEarned = victoryCoinsEarned,
                onNextBrand = {
                    viewModel.nextBrand()
                },
                onShareClick = {
                    ShareHelper.shareSolvedBrand(
                        context = context,
                        brandName = brand.name,
                        category = brand.category,
                        score = (userProfile?.totalScore ?: 0) + 100
                    )
                },
                onDismiss = {
                    viewModel.dismissVictoryDialog()
                }
            )
        }
    }
}
