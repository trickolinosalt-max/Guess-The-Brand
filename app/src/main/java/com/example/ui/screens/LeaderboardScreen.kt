package com.example.ui.screens

import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.LeaderboardEntry
import com.example.ui.theme.GameGold
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate800
import com.example.ui.theme.Slate900
import com.example.util.ShareHelper
import com.example.viewmodel.QuizViewModel
import com.example.viewmodel.Screen

@Composable
fun LeaderboardScreen(
    viewModel: QuizViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    BackHandler {
        viewModel.navigateTo(Screen.LevelSelect)
    }

    val userProfile by viewModel.userProfile.collectAsState()
    var selectedTab by remember { mutableIntStateOf(0) } // 0: Global, 1: Friends

    val (globalList, friendsList) = viewModel.getLeaderboardLists()
    val activeList = if (selectedTab == 0) globalList else friendsList

    val currentUserEntry = globalList.find { it.isCurrentUser }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Slate900)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // HEADER
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
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
                    text = "GLOBAL LEADERBOARDS",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.sp,
                    color = Color.White
                )
                IconButton(
                    onClick = {
                        val rank = currentUserEntry?.rank ?: 1
                        val score = currentUserEntry?.score ?: 0
                        val name = userProfile?.username ?: "You"
                        ShareHelper.shareLeaderboardInvite(context, name, rank, score)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share",
                        tint = GameGold
                    )
                }
            }

            // TABS (Global vs Friends)
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = Slate800,
                contentColor = Color.White,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                        color = Color(0xFF2563EB),
                        height = 3.dp
                    )
                }
            ) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = {
                        Text(
                            text = "GLOBAL RANKINGS",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (selectedTab == 0) Color.White else Color(0xFF94A3B8)
                        )
                    }
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = {
                        Text(
                            text = "FRIENDS & RIVALS",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (selectedTab == 1) Color.White else Color(0xFF94A3B8)
                        )
                    }
                )
            }

            // USER'S STICKY RANK CARD
            if (currentUserEntry != null) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp, vertical = 10.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1D4ED8)),
                    border = androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFF60A5FA))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "#${currentUserEntry.rank}",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Black,
                                color = GameGold
                            )
                            Spacer(modifier = Modifier.width(14.dp))
                            Column {
                                Text(
                                    text = "${currentUserEntry.username} (You)",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Text(
                                    text = "Tier: ${currentUserEntry.tier} • Solved: ${currentUserEntry.solvedCount}",
                                    fontSize = 12.sp,
                                    color = Color(0xFFBFDBFE)
                                )
                            }
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.LocalFireDepartment,
                                contentDescription = "Streak",
                                tint = GameGold,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(
                                text = "${currentUserEntry.streak}d",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = GameGold
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "${currentUserEntry.score} pts",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Black,
                                color = Color.White
                            )
                        }
                    }
                }
            }

            // LEADERBOARD LIST
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 14.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(activeList) { entry ->
                    LeaderboardRowItem(entry = entry)
                }
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }

            // INVITE FRIENDS FOOTER BUTTON
            Button(
                onClick = {
                    val name = userProfile?.username ?: "You"
                    val rank = currentUserEntry?.rank ?: 1
                    val score = currentUserEntry?.score ?: 0
                    ShareHelper.shareLeaderboardInvite(context, name, rank, score)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2563EB))
            ) {
                Icon(
                    imageVector = Icons.Default.PersonAdd,
                    contentDescription = "Invite",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Challenge Friends & Invite to League",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
private fun LeaderboardRowItem(entry: LeaderboardEntry) {
    val isTop3 = entry.rank <= 3
    val medalColor = when (entry.rank) {
        1 -> Color(0xFFF59E0B) // Gold
        2 -> Color(0xFF94A3B8) // Silver
        3 -> Color(0xFFB45309) // Bronze
        else -> Color.Transparent
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (entry.isCurrentUser) Color(0xFF1E3A8A) else Slate800
        ),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (entry.isCurrentUser) Color(0xFF3B82F6) else Slate700
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Rank number / Medal
                Box(
                    modifier = Modifier.size(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    if (isTop3) {
                        Icon(
                            imageVector = Icons.Default.EmojiEvents,
                            contentDescription = "Rank ${entry.rank}",
                            tint = medalColor,
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Text(
                            text = "#${entry.rank}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF94A3B8)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(10.dp))

                // Avatar
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color(entry.avatarBgColor)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = entry.username.take(1).uppercase(),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = entry.username,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "${entry.tier} • ${entry.solvedCount} solved",
                        fontSize = 11.sp,
                        color = Color(0xFF94A3B8)
                    )
                }
            }

            // Score & Streak
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (entry.streak > 1) {
                    Icon(
                        imageVector = Icons.Default.LocalFireDepartment,
                        contentDescription = "Streak",
                        tint = GameGold,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "${entry.streak}d",
                        fontSize = 11.sp,
                        color = GameGold
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }
                Text(
                    text = "${entry.score} pts",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )
            }
        }
    }
}
