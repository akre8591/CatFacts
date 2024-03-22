package com.example.technicaltest.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CatFactsError(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center,
    message: String
) {
    Box(
        modifier = modifier,
        contentAlignment = contentAlignment
    ) {
        Text(text = message)
    }
}
