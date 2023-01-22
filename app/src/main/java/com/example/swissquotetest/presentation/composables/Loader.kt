package com.example.swissquotetest.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.swissquotetest.presentation.ui.theme.Orange

@Composable
fun Loader(
    isVisible: Boolean
) {
    if (isVisible) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clickable(enabled = false) {}
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
        ) {
            CircularProgressIndicator(
                color = Orange
            )
        }
    }
}