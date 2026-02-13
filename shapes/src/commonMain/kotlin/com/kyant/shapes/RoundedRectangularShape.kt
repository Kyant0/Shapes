package com.kyant.shapes

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

sealed interface RoundedRectangularShape : Shape {

    fun cornerRadii(size: Size, layoutDirection: LayoutDirection, density: Density): FloatArray
}
