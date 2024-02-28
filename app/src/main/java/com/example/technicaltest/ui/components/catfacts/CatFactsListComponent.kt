package com.example.technicaltest.ui.components.catfacts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.technicaltest.data.domain.model.CatFactsModel

@Composable
fun CatFactsListComponent(
    catFactsList: List<CatFactsModel>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(catFactsList.size) { index ->
            CatFactItemComponent(catFact = catFactsList[index])
        }
    }
}
