package com.example.technicaltest.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CatFactsProgress(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center,
) {
    Box(
        modifier = modifier,
        contentAlignment = contentAlignment
    ) {
        CircularProgressIndicator()
    }
}
