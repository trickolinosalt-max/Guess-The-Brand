package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.RibbonBlue
import com.example.ui.theme.RibbonBlueDark
import com.example.ui.theme.RibbonBlueLight

/**
 * Category Ribbon Banner replicating the uploaded sub_menu_bg.9.png asset.
 * Displays the logo's category name in crisp, 3D game ribbon style.
 */
@Composable
fun CategoryRibbonBanner(
    categoryText: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        contentAlignment = Alignment.Center
    ) {
        // 3D Ribbon Artwork matching sub_menu_bg.9.png
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .drawBehind {
                    val w = size.width
                    val h = size.height

                    // 1. Folded Back Wings (Darker ribbon behind center)
                    val wingHeight = h * 0.75f
                    val wingTop = h * 0.25f

                    // Left Wing
                    val leftWingPath = Path().apply {
                        moveTo(-28.dp.toPx(), wingTop)
                        lineTo(16.dp.toPx(), wingTop)
                        lineTo(16.dp.toPx(), wingTop + wingHeight)
                        lineTo(-28.dp.toPx(), wingTop + wingHeight)
                        close()
                    }
                    drawPath(leftWingPath, color = Color(0xFF11587C))

                    // Left fold shadow triangle behind main ribbon
                    val leftFoldTriangle = Path().apply {
                        moveTo(0f, wingTop)
                        lineTo(16.dp.toPx(), wingTop + wingHeight)
                        lineTo(0f, wingTop + wingHeight)
                        close()
                    }
                    drawPath(leftFoldTriangle, color = Color(0xFF072434))

                    // Right Wing
                    val rightWingPath = Path().apply {
                        moveTo(w - 16.dp.toPx(), wingTop)
                        lineTo(w + 28.dp.toPx(), wingTop)
                        lineTo(w + 28.dp.toPx(), wingTop + wingHeight)
                        lineTo(w - 16.dp.toPx(), wingTop + wingHeight)
                        close()
                    }
                    drawPath(rightWingPath, color = Color(0xFF11587C))

                    // Right fold shadow triangle behind main ribbon
                    val rightFoldTriangle = Path().apply {
                        moveTo(w, wingTop)
                        lineTo(w - 16.dp.toPx(), wingTop + wingHeight)
                        lineTo(w, wingTop + wingHeight)
                        close()
                    }
                    drawPath(rightFoldTriangle, color = Color(0xFF072434))

                    // Center bottom bevel shadow
                    drawRect(
                        color = Color(0xFF0D4360),
                        topLeft = Offset(0f, h - 5.dp.toPx()),
                        size = Size(w, 5.dp.toPx())
                    )

                    // Center top highlight
                    drawRect(
                        color = Color(0xFF2BA2D6),
                        topLeft = Offset(0f, 0f),
                        size = Size(w, 3.dp.toPx())
                    )
                }
                .shadow(elevation = 6.dp, shape = RoundedCornerShape(4.dp))
                .clip(RoundedCornerShape(4.dp))
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            RibbonBlueLight,
                            RibbonBlue,
                            RibbonBlueDark
                        )
                    )
                )
                .padding(horizontal = 32.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = categoryText.uppercase(),
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 2.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
