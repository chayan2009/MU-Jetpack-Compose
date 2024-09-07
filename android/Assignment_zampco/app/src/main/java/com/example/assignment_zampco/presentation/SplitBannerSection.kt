package com.example.assignment_zampco.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.assignment_zampco.model.Item
import androidx.compose.foundation.Image
import coil.compose.rememberAsyncImagePainter

@Composable
fun SplitBannerSection(items: List<Item>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items.forEach { item ->
            Image(
                painter = rememberAsyncImagePainter(model = item.image),
                contentDescription = item.title,
                modifier = Modifier.weight(1f),
                contentScale = ContentScale.Crop
            )
        }
    }
}
