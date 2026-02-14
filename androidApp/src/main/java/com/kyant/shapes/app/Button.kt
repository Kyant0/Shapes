package com.kyant.shapes.app

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.kyant.shapes.Capsule

@Composable
fun Button(
    onClick: () -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .clip(Capsule())
            .clickable(
                role = Role.Button,
                onClick = onClick
            )
            .background(Color(0xFF0088FF))
            .padding(16f.dp, 12f.dp)
    ) {
        BasicText(
            label,
            style = TextStyle(color = Color.White)
        )
    }
}
