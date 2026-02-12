package com.kyant.shapes

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.util.fastCoerceIn

fun RoundedRectangle(cornerRadius: Float) =
    RoundedRectangle(cornerRadius = CornerRadius.Px(cornerRadius))

fun RoundedRectangle(cornerRadius: Dp) =
    RoundedRectangle(cornerRadius = CornerRadius.Dp(cornerRadius))

data class RoundedRectangle(val cornerRadius: CornerRadius) : RoundedRectangularShape {

    override fun cornerRadii(size: Size, layoutDirection: LayoutDirection, density: Density): FloatArray {
        val cornerRadiusPx =
            context(size, density) {
                cornerRadius.toPx().fastCoerceIn(0f, size.minDimension * 0.5f)
            }
        return FloatArray(4) { cornerRadiusPx }
    }

    override fun lerp(to: RoundedRectangularShape, fraction: Float): RoundedRectangularShape {
        return when (to) {
            is RoundedRectangle -> RoundedRectangle(
                cornerRadius = lerp(this.cornerRadius, to.cornerRadius, fraction)
            )

            is UnevenRoundedRectangle -> UnevenRoundedRectangle(
                cornerRadii = lerp(RectangleCornerRadii(this.cornerRadius), to.cornerRadii, fraction)
            )

            is Capsule -> RoundedRectangle(
                cornerRadius = lerp(this.cornerRadius, CornerRadius.Max, fraction)
            )

            else -> to.lerp(this, 1f - fraction)
        }
    }

    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        val cornerRadiusPx =
            context(size, density) {
                cornerRadius.toPx().fastCoerceIn(0f, size.minDimension * 0.5f)
            }

        if (cornerRadiusPx <= 0f) {
            return Outline.Rectangle(
                Rect(
                    left = 0f,
                    top = 0f,
                    right = size.width,
                    bottom = size.height
                )
            )
        }

        return createRoundedRectangleOutline(size = size, radius = cornerRadiusPx)
    }

    fun copy(cornerRadius: Float) =
        RoundedRectangle(cornerRadius = cornerRadius)

    fun copy(cornerRadius: Dp) =
        RoundedRectangle(cornerRadius = cornerRadius)

    fun asUneven(rtlAware: Boolean = true): UnevenRoundedRectangle {
        return UnevenRoundedRectangle(
            cornerRadii = RectangleCornerRadii(
                topStart = cornerRadius,
                topEnd = cornerRadius,
                bottomEnd = cornerRadius,
                bottomStart = cornerRadius,
                rtlAware = rtlAware
            )
        )
    }
}
