package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate800
import com.example.ui.theme.Slate900

data class KeyboardTile(
    val id: Int,
    val char: Char,
    val isPlaced: Boolean = false,
    val isEliminated: Boolean = false
)

@Composable
fun LetterKeyboard(
    tiles: List<KeyboardTile>,
    onTileClick: (KeyboardTile) -> Unit,
    modifier: Modifier = Modifier
) {
    val row1 = tiles.take(7)
    val row2 = tiles.drop(7).take(7)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            row1.forEach { tile ->
                LetterTileButton(tile = tile, onClick = { onTileClick(tile) })
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            row2.forEach { tile ->
                LetterTileButton(tile = tile, onClick = { onTileClick(tile) })
            }
        }
    }
}

@Composable
private fun LetterTileButton(
    tile: KeyboardTile,
    onClick: () -> Unit
) {
    val isVisible = !tile.isEliminated
    val isEnabled = isVisible && !tile.isPlaced

    Box(
        modifier = Modifier
            .width(42.dp)
            .height(48.dp)
            .then(
                if (isVisible && !tile.isPlaced) {
                    Modifier
                        .shadow(elevation = 3.dp, shape = RoundedCornerShape(8.dp))
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xFF3B82F6),
                                    Color(0xFF1D4ED8)
                                )
                            )
                        )
                        .border(1.dp, Color(0xFF60A5FA), RoundedCornerShape(8.dp))
                        .drawBehind {
                            // 3D Bottom Lip Bevel
                            drawRect(
                                color = Color(0xFF1E3A8A),
                                topLeft = Offset(0f, size.height - 4.dp.toPx()),
                                size = Size(size.width, 4.dp.toPx())
                            )
                        }
                        .clickable { onClick() }
                } else if (tile.isPlaced) {
                    Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF1E293B).copy(alpha = 0.5f))
                        .border(1.dp, Color(0xFF334155), RoundedCornerShape(8.dp))
                } else {
                    Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.Transparent)
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        if (isVisible && !tile.isPlaced) {
            Text(
                text = tile.char.toString(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Black,
                color = Color.White
            )
        }
    }
}
