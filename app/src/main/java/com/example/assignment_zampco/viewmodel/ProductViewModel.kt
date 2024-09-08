package com.example.assignment_zampco.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment_zampco.data.DataRepository
import com.example.assignment_zampco.model.ProductItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    private val _productItem = MutableStateFlow<List<ProductItem>>(emptyList())
    val productItem: StateFlow<List<ProductItem>> get() = _productItem

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    init {
        fetchProductItem()
    }

    fun fetchProductItem() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _productItem.value = repository.getProductList()
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load data"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun retryFetching() {
        _errorMessage.value = null
        fetchProductItem()
    }
}
