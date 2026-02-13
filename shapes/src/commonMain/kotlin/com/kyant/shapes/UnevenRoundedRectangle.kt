package com.kyant.shapes

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastCoerceIn

@Suppress("FunctionName")
fun RoundedRectangle(
    topStart: CornerRadius = CornerRadius.Zero,
    topEnd: CornerRadius = CornerRadius.Zero,
    bottomEnd: CornerRadius = CornerRadius.Zero,
    bottomStart: CornerRadius = CornerRadius.Zero,
    rtlAware: Boolean = true
) =
    UnevenRoundedRectangle(
        cornerRadii = RectangleCornerRadii(
            topStart = topStart,
            topEnd = topEnd,
            bottomEnd = bottomEnd,
            bottomStart = bottomStart,
            rtlAware = rtlAware
        )
    )

@Suppress("FunctionName")
fun RoundedRectangle(
    topStart: Float = 0f,
    topEnd: Float = 0f,
    bottomEnd: Float = 0f,
    bottomStart: Float = 0f,
    rtlAware: Boolean = true
) =
    UnevenRoundedRectangle(
        cornerRadii = RectangleCornerRadii(
            topStart = CornerRadius.Px(topStart),
            topEnd = CornerRadius.Px(topEnd),
            bottomEnd = CornerRadius.Px(bottomEnd),
            bottomStart = CornerRadius.Px(bottomStart),
            rtlAware = rtlAware
        )
    )

@Suppress("FunctionName")
fun RoundedRectangle(
    topStart: Dp = 0f.dp,
    topEnd: Dp = 0f.dp,
    bottomEnd: Dp = 0f.dp,
    bottomStart: Dp = 0f.dp,
    rtlAware: Boolean = true
) =
    UnevenRoundedRectangle(
        cornerRadii = RectangleCornerRadii(
            topStart = CornerRadius.Dp(topStart),
            topEnd = CornerRadius.Dp(topEnd),
            bottomEnd = CornerRadius.Dp(bottomEnd),
            bottomStart = CornerRadius.Dp(bottomStart),
            rtlAware = rtlAware
        )
    )

data class UnevenRoundedRectangle(val cornerRadii: RectangleCornerRadii) : RoundedRectangularShape {

    override fun cornerRadii(size: Size, layoutDirection: LayoutDirection, density: Density): FloatArray {
        val maxRadius = size.minDimension * 0.5f
        return context(size, layoutDirection, density) {
            floatArrayOf(
                cornerRadii.topLeft.fastCoerceIn(0f, maxRadius),
                cornerRadii.topRight.fastCoerceIn(0f, maxRadius),
                cornerRadii.bottomRight.fastCoerceIn(0f, maxRadius),
                cornerRadii.bottomLeft.fastCoerceIn(0f, maxRadius)
            )
        }
    }

    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        val maxRadius = size.minDimension * 0.5f
        return context(size, layoutDirection, density) {
            createRoundedRectangleOutline(
                size = size,
                topLeft = cornerRadii.topLeft.fastCoerceIn(0f, maxRadius),
                topRight = cornerRadii.topRight.fastCoerceIn(0f, maxRadius),
                bottomRight = cornerRadii.bottomRight.fastCoerceIn(0f, maxRadius),
                bottomLeft = cornerRadii.bottomLeft.fastCoerceIn(0f, maxRadius)
            )
        }
    }

    fun copy(
        topStart: CornerRadius = cornerRadii.topStart,
        topEnd: CornerRadius = cornerRadii.topEnd,
        bottomEnd: CornerRadius = cornerRadii.bottomEnd,
        bottomStart: CornerRadius = cornerRadii.bottomStart,
        rtlAware: Boolean = cornerRadii.rtlAware
    ) =
        UnevenRoundedRectangle(
            cornerRadii = RectangleCornerRadii(
                topStart = topStart,
                topEnd = topEnd,
                bottomEnd = bottomEnd,
                bottomStart = bottomStart,
                rtlAware = rtlAware
            )
        )
}
