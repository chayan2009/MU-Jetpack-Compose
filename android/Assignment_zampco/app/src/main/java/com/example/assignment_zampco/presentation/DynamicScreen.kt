package com.example.assignment_zampco.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.assignment_zampco.viewmodel.ProductViewModel

@Composable
fun DynamicScreen(viewModel: ProductViewModel = hiltViewModel()) {

    val products by viewModel.productItem.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            errorMessage != null -> {
                ErrorView(errorMessage = errorMessage, onRetry = { viewModel.retryFetching() })
            }
            else -> {
                LazyColumn {
                    items(products) { block ->
                        when (block.sectionType) {
                            "banner" -> BannerSection(block.items)
                            "horizontalFreeScroll" -> HorizontalFreeScrollSection(block.items)
                            "splitBanner" -> SplitBannerSection(block.items)
                        }
                    }
                }
            }
        }
    }
}

