package com.kyant.shapes

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

data class RectangleCornerRadii(
    val topStart: CornerRadius = CornerRadius.Zero,
    val topEnd: CornerRadius = CornerRadius.Zero,
    val bottomEnd: CornerRadius = CornerRadius.Zero,
    val bottomStart: CornerRadius = CornerRadius.Zero,
    val rtlAware: Boolean = true
) {

    constructor(all: CornerRadius, rtlAware: Boolean = true) : this(
        topStart = all,
        topEnd = all,
        bottomEnd = all,
        bottomStart = all,
        rtlAware = rtlAware
    )

    context(size: Size, layoutDirection: LayoutDirection, density: Density)
    val topLeft: Float
        get() = if (!rtlAware || layoutDirection == LayoutDirection.Ltr) topStart.toPx() else topEnd.toPx()

    context(size: Size, layoutDirection: LayoutDirection, density: Density)
    val topRight: Float
        get() = if (!rtlAware || layoutDirection == LayoutDirection.Ltr) topEnd.toPx() else topStart.toPx()

    context(size: Size, layoutDirection: LayoutDirection, density: Density)
    val bottomRight: Float
        get() = if (!rtlAware || layoutDirection == LayoutDirection.Ltr) bottomEnd.toPx() else bottomStart.toPx()

    context(size: Size, layoutDirection: LayoutDirection, density: Density)
    val bottomLeft: Float
        get() = if (!rtlAware || layoutDirection == LayoutDirection.Ltr) bottomStart.toPx() else bottomEnd.toPx()

    companion object {

        val Zero = RectangleCornerRadii()

        val Max =
            RectangleCornerRadii(
                topStart = CornerRadius.Max,
                topEnd = CornerRadius.Max,
                bottomEnd = CornerRadius.Max,
                bottomStart = CornerRadius.Max
            )
    }
}
