package com.kyant.shapes

import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

fun RoundedRectangularShape.asStandardShape() =
    StandardRoundedRectangle(this)

data class StandardRoundedRectangle(val shape: RoundedRectangularShape) : RoundedRectangularShape {

    override fun cornerRadii(size: Size, layoutDirection: LayoutDirection, density: Density): FloatArray {
        return shape.cornerRadii(size, layoutDirection, density)
    }

    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        val cornerRadii = cornerRadii(size, layoutDirection, density)
        return Outline.Rounded(
            RoundRect(
                left = 0f,
                top = 0f,
                right = size.width,
                bottom = size.height,
                topLeftCornerRadius = androidx.compose.ui.geometry.CornerRadius(cornerRadii[0]),
                topRightCornerRadius = androidx.compose.ui.geometry.CornerRadius(cornerRadii[1]),
                bottomRightCornerRadius = androidx.compose.ui.geometry.CornerRadius(cornerRadii[2]),
                bottomLeftCornerRadius = androidx.compose.ui.geometry.CornerRadius(cornerRadii[3])
            )
        )
    }
}
