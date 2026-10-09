package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.GameGold
import com.example.ui.theme.GameRed
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate800
import com.example.ui.theme.Slate900

@Composable
fun AnswerSlotsRow(
    targetName: String,
    placedLetters: Map<Int, Char>, // index -> Char
    revealedIndices: Set<Int>,
    isError: Boolean,
    onSlotClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val shakeOffset = remember { Animatable(0f) }

    LaunchedEffect(isError) {
        if (isError) {
            shakeOffset.animateTo(
                targetValue = 0f,
                animationSpec = keyframes {
                    durationMillis = 400
                    0f at 0
                    -12f at 50
                    12f at 100
                    -10f at 150
                    10f at 200
                    -5f at 250
                    5f at 300
                    0f at 400
                }
            )
        }
    }

    // Split target name into words to wrap onto lines if multi-word
    val words = targetName.split(" ")
    var globalIndexCounter = 0

    Column(
        modifier = modifier
            .fillMaxWidth()
            .offset(x = shakeOffset.value.dp)
            .padding(horizontal = 12.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        words.forEachIndexed { wordIndex, word ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                word.forEach { _ ->
                    val slotIndex = globalIndexCounter++
                    val char = placedLetters[slotIndex]
                    val isRevealed = revealedIndices.contains(slotIndex)

                    val borderColor by animateColorAsState(
                        targetValue = when {
                            isError -> GameRed
                            isRevealed -> GameGold
                            char != null -> Color(0xFF60A5FA)
                            else -> Slate700
                        },
                        label = "slotBorder"
                    )

                    val bgColor by animateColorAsState(
                        targetValue = when {
                            isError -> Color(0x33EF4444)
                            isRevealed -> Color(0x22F59E0B)
                            char != null -> Slate800
                            else -> Color(0xFF1E293B)
                        },
                        label = "slotBg"
                    )

                    Box(
                        modifier = Modifier
                            .size(if (targetName.length > 8) 36.dp else 44.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(bgColor)
                            .border(
                                width = if (char != null || isRevealed) 2.dp else 1.5.dp,
                                color = borderColor,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable(enabled = char != null && !isRevealed) {
                                onSlotClick(slotIndex)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        if (char != null) {
                            Text(
                                text = char.toString(),
                                fontSize = if (targetName.length > 8) 18.sp else 22.sp,
                                fontWeight = FontWeight.Black,
                                color = if (isRevealed) GameGold else Color.White
                            )
                        } else {
                            // Underline indicator
                            Box(
                                modifier = Modifier
                                    .width(16.dp)
                                    .height(2.dp)
                                    .align(Alignment.BottomCenter)
                                    .padding(bottom = 6.dp)
                                    .background(Slate700)
                            )
                        }
                    }
                }
            }
            // Skip the space for indexing if next word exists
            if (wordIndex < words.size - 1) {
                globalIndexCounter++
            }
        }
    }
}
