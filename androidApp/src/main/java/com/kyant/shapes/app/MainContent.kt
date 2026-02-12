package com.kyant.shapes.app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.kyant.shapes.RoundedRectangle

@Composable
fun MainContent() {
    val uniformAspectRatio = remember { mutableFloatStateOf(0f) }
    val cornerRadiusRatio = remember { mutableFloatStateOf(0.5f) }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16f.dp)
            .safeDrawingPadding(),
        verticalArrangement = Arrangement.spacedBy(16f.dp)
    ) {
        Box(
            Modifier.aspectRatio(1f),
            contentAlignment = Alignment.Center
        ) {
            Box(
                Modifier
                    .drawBehind {
                        val radius = size.minDimension * 0.5f * cornerRadiusRatio.floatValue
                        val shape = RoundedRectangle(radius)
                        drawOutline(
                            shape.createOutline(size, layoutDirection, this),
                            color = Color(0xFF0088FF)
                        )
                    }
                    .layout { measurable, constraints ->
                        val width: Int
                        val height: Int
                        val aspectRatio = aspectRatio(uniformAspectRatio.floatValue)
                        if (constraints.maxWidth.toFloat() / constraints.maxHeight.toFloat() >= aspectRatio) {
                            height = constraints.maxHeight
                            width = (height.toFloat() * aspectRatio).toInt()
                        } else {
                            width = constraints.maxWidth
                            height = (width.toFloat() / aspectRatio).toInt()
                        }
                        val placeable = measurable.measure(Constraints.fixed(width, height))
                        layout(width, height) {
                            placeable.place(IntOffset.Zero)
                        }
                    }
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(8f.dp)) {
            Slider(
                state = uniformAspectRatio,
                valueRange = -4f..4f,
                label = "Aspect ratio",
                value = { "%.3f".format(aspectRatio(it)) }
            )
            Slider(
                state = cornerRadiusRatio,
                valueRange = 0f..1f,
                label = "Corner radius ratio",
                value = { "%.3f".format(it) }
            )
            Button(
                onClick = {
                    uniformAspectRatio.floatValue = 0f
                    cornerRadiusRatio.floatValue = 0.5f
                },
                label = "Reset"
            )
        }
    }
}

private fun aspectRatio(value: Float): Float =
    if (value >= 0f) {
        1f + value
    } else {
        1f / (1f - value)
    }
