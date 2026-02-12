package com.kyant.shapes

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

data object Rectangle : RoundedRectangularShape {

    override fun cornerRadii(size: Size, layoutDirection: LayoutDirection, density: Density): FloatArray {
        return FloatArray(4)
    }

    override fun lerp(to: RoundedRectangularShape, fraction: Float): RoundedRectangularShape {
        return when (to) {
            is Rectangle -> this
            is RoundedRectangle ->
                RoundedRectangle(cornerRadius = lerp(CornerRadius.Zero, to.cornerRadius, fraction))

            is UnevenRoundedRectangle ->
                UnevenRoundedRectangle(cornerRadii = lerp(RectangleCornerRadii.Zero, to.cornerRadii, fraction))

            is Capsule ->
                RoundedRectangle(cornerRadius = lerp(CornerRadius.Zero, CornerRadius.Max, fraction))
        }
    }

    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        return Outline.Rectangle(Rect(0f, 0f, size.width, size.height))
    }
}
