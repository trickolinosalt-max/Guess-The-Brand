package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.GameGold
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate800

@Composable
fun HintsBar(
    coins: Int,
    onExposeLetterClick: () -> Unit,
    onRemoveDecoysClick: () -> Unit,
    onSolveBrandClick: () -> Unit,
    onAskFriendClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HintActionButton(
            icon = Icons.Default.Lightbulb,
            label = "Expose",
            costText = "15 🪙",
            enabled = coins >= 15,
            onClick = onExposeLetterClick
        )
        HintActionButton(
            icon = Icons.Default.DeleteSweep,
            label = "Remove",
            costText = "25 🪙",
            enabled = coins >= 25,
            onClick = onRemoveDecoysClick
        )
        HintActionButton(
            icon = Icons.Default.Bolt,
            label = "Solve",
            costText = "50 🪙",
            enabled = coins >= 50,
            onClick = onSolveBrandClick
        )
        HintActionButton(
            icon = Icons.Default.Share,
            label = "Ask Friend",
            costText = "FREE",
            enabled = true,
            isShare = true,
            onClick = onAskFriendClick
        )
    }
}

@Composable
private fun HintActionButton(
    icon: ImageVector,
    label: String,
    costText: String,
    enabled: Boolean,
    isShare: Boolean = false,
    onClick: () -> Unit
) {
    val alpha = if (enabled) 1f else 0.4f
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Slate800.copy(alpha = alpha))
            .border(1.dp, if (isShare) Color(0xFF38BDF8) else Slate700, RoundedCornerShape(12.dp))
            .clickable(enabled = enabled) { onClick() }
            .padding(horizontal = 10.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (isShare) Color(0xFF38BDF8) else GameGold,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White.copy(alpha = alpha)
        )
        Text(
            text = costText,
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            color = if (isShare) Color(0xFF38BDF8) else GameGold.copy(alpha = alpha)
        )
    }
}
