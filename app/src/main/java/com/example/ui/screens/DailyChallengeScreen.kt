package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.BrandCatalog
import com.example.ui.components.CategoryRibbonBanner
import com.example.ui.theme.GameGold
import com.example.ui.theme.GameGreen
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate800
import com.example.ui.theme.Slate900
import com.example.util.ShareHelper
import com.example.viewmodel.QuizViewModel
import com.example.viewmodel.Screen
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun DailyChallengeScreen(
    viewModel: QuizViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    BackHandler {
        viewModel.navigateTo(Screen.LevelSelect)
    }

    val userProfile by viewModel.userProfile.collectAsState()
    val progressMap by viewModel.progressMap.collectAsState()

    val dailyBrand = BrandCatalog.getDailyChallengeBrand()
    val isSolved = progressMap[dailyBrand.id]?.isSolved == true
    val streak = userProfile?.streak ?: 1

    val formattedDate = SimpleDateFormat("EEEE, MMMM d, yyyy", Locale.US).format(Date())

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Slate900)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // HEADER
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { viewModel.navigateTo(Screen.LevelSelect) }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
                Text(
                    text = "DAILY CHALLENGE",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.sp,
                    color = GameGold
                )
                IconButton(onClick = { ShareHelper.shareDailyStreak(context, streak) }) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share Streak",
                        tint = GameGold
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // DATE & STREAK BADGE
            Text(
                text = formattedDate,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF94A3B8)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // STREAK FLAME CARD
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Slate800),
                border = androidx.compose.foundation.BorderStroke(1.5.dp, GameGold)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFEA580C)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocalFireDepartment,
                                contentDescription = "Fire",
                                tint = Color.White,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(14.dp))
                        Column {
                            Text(
                                text = "$streak-Day Streak!",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Black,
                                color = Color.White
                            )
                            Text(
                                text = "Streak multiplier active (2x XP + 35 🪙)",
                                fontSize = 12.sp,
                                color = Color(0xFFFDE68A)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // DAILY BRAND CARD WITH 9-PATCH CATEGORY RIBBON
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Slate800),
                border = androidx.compose.foundation.BorderStroke(1.dp, Slate700)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CategoryRibbonBanner(
                        categoryText = dailyBrand.category,
                        modifier = Modifier.padding(top = 4.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Mystery Brand Silhouette / Image
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White)
                            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(16.dp))
                            .padding(14.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = dailyBrand.logoRes),
                            contentDescription = "Daily Brand",
                            modifier = Modifier.fillMaxSize()
                        )

                        if (isSolved) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color(0x77000000)),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Icon(
                                        imageVector = Icons.Default.CheckCircle,
                                        contentDescription = "Solved",
                                        tint = GameGreen,
                                        modifier = Modifier.size(44.dp)
                                    )
                                    Text(
                                        text = "COMPLETED",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = GameGreen
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = if (isSolved) dailyBrand.name else "Mystery ${dailyBrand.category} Brand",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isSolved) GameGreen else Color.White
                    )

                    Text(
                        text = if (isSolved) "You solved today's daily puzzle!" else "Can you identify today's iconic brand?",
                        fontSize = 12.sp,
                        color = Color(0xFF94A3B8)
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // PLAY BUTTON
            Button(
                onClick = {
                    viewModel.navigateTo(Screen.Game(dailyBrand.id, isDaily = true))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSolved) Color(0xFF059669) else Color(0xFFD97706)
                )
            ) {
                Icon(
                    imageVector = if (isSolved) Icons.Default.CheckCircle else Icons.Default.PlayArrow,
                    contentDescription = "Action",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (isSolved) "Replay Daily Puzzle" else "Start Daily Challenge",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // SHARE STREAK BUTTON
            OutlinedButton(
                onClick = { ShareHelper.shareDailyStreak(context, streak) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(16.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, GameGold)
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share",
                    tint = GameGold
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Share Daily Streak with Friends",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = GameGold
                )
            }
        }
    }
}
