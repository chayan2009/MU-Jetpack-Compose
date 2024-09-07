package com.example.assignment_zampco.presentation
import androidx.compose.runtime.Composable
import com.example.assignment_zampco.model.Item
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun BannerSection(items: List<Item>) {
    items.forEach { item ->
        Image(
            painter = rememberAsyncImagePainter(model = item.image),
            contentDescription = item.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
            contentScale = ContentScale.Crop
        )
    }
}
