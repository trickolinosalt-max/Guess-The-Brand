package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.ui.screens.CloudSaveSettingsScreen
import com.example.ui.screens.DailyChallengeScreen
import com.example.ui.screens.GamePlayScreen
import com.example.ui.screens.LeaderboardScreen
import com.example.ui.screens.LevelSelectScreen
import com.example.ui.theme.GuessTheBrandTheme
import com.example.ui.theme.Slate900
import com.example.viewmodel.QuizViewModel
import com.example.viewmodel.Screen

class MainActivity : ComponentActivity() {

    private val viewModel: QuizViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GuessTheBrandTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Slate900
                ) {
                    val currentScreen by viewModel.currentScreen.collectAsState()

                    when (currentScreen) {
                        is Screen.LevelSelect -> LevelSelectScreen(viewModel = viewModel)
                        is Screen.Game -> GamePlayScreen(viewModel = viewModel)
                        is Screen.DailyChallenge -> DailyChallengeScreen(viewModel = viewModel)
                        is Screen.Leaderboard -> LeaderboardScreen(viewModel = viewModel)
                        is Screen.Settings -> CloudSaveSettingsScreen(viewModel = viewModel)
                    }
                }
            }
        }
    }
}
