package com.kyant.shapes

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

data object Capsule : RoundedRectangularShape {

    override fun cornerRadii(size: Size, layoutDirection: LayoutDirection, density: Density): FloatArray {
        val cornerRadiusPx = size.minDimension * 0.5f
        return FloatArray(4) { cornerRadiusPx }
    }

    override fun lerp(to: RoundedRectangularShape, fraction: Float): RoundedRectangularShape {
        return when (to) {
            is Capsule -> this
            else -> to.lerp(this, 1f - fraction)
        }
    }

    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        val cornerRadiusPx = size.minDimension * 0.5f
        return createRoundedRectangleOutline(size = size, radius = cornerRadiusPx)
    }
}
