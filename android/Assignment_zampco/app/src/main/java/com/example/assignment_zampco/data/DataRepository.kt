package com.example.assignment_zampco.data

import com.example.assignment_zampco.model.ProductItem
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getUiBlocks(): List<ProductItem> {
        return apiService.getData()  // Replace with actual API call
    }
}
