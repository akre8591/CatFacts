package com.example.technicaltest.ui.components.catfacts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.technicaltest.data.domain.model.CatFactsModel

@Composable
fun CatFactItemComponent(catFact: CatFactsModel) {
    Column(modifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp)) {
        Text(
            text = catFact.type,
            fontSize = 24.sp
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = catFact.text,
            fontSize = 16.sp
        )
    }
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(0.3.dp)
            .background(color = Color.Gray)
    )
}
