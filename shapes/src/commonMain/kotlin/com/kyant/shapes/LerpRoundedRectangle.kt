package com.kyant.shapes

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.util.lerp

fun lerp(start: RoundedRectangularShape, stop: RoundedRectangularShape, fraction: Float): RoundedRectangularShape {
    return when (fraction) {
        0f -> start
        1f -> stop
        else -> LerpRoundedRectangle(start, stop, fraction)
    }
}

private data class LerpRoundedRectangle(
    val start: RoundedRectangularShape,
    val stop: RoundedRectangularShape,
    val fraction: Float
) : RoundedRectangularShape {

    override fun cornerRadii(size: Size, layoutDirection: LayoutDirection, density: Density): FloatArray {
        val startRadii = start.cornerRadii(size, layoutDirection, density)
        val stopRadii = stop.cornerRadii(size, layoutDirection, density)
        return FloatArray(4) { index -> lerp(startRadii[index], stopRadii[index], fraction) }
    }

    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        val cornerRadii = cornerRadii(size, layoutDirection, density)
        val topLeft = cornerRadii[0]
        val topRight = cornerRadii[1]
        val bottomRight = cornerRadii[2]
        val bottomLeft = cornerRadii[3]
        return if (topLeft == topRight && topRight == bottomRight && bottomRight == bottomLeft) {
            createRoundedRectangleOutline(size = size, radius = topLeft)
        } else {
            createRoundedRectangleOutline(
                size = size,
                topLeft = topLeft,
                topRight = topRight,
                bottomRight = bottomRight,
                bottomLeft = bottomLeft
            )
        }
    }
}
