package com.example.assignment_zampco.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.assignment_zampco.data.DataRepository
import com.example.assignment_zampco.model.Item
import com.example.assignment_zampco.model.ProductItem
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProductViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: ProductViewModel
    private val repository: DataRepository = mockk()

    private val mockProductItems = listOf(
        ProductItem(listOf(Item("Jacket", "https://images.pexels.com/photos/789812/pexels-photo-789812.jpeg")), "banner"),
        ProductItem(listOf(
            Item("Laptop", "https://images.pexels.com/photos/7974/pexels-photo.jpg"),
            Item("Hat", "https://images.pexels.com/photos/984619/pexels-photo-984619.jpeg")
        ), "horizontalFreeScroll"),
        ProductItem(listOf(
            Item("Camera", "https://images.pexels.com/photos/225157/pexels-photo-225157.jpeg"),
            Item("Perfume", "https://images.pexels.com/photos/264819/pexels-photo-264819.jpeg")
        ), "splitBanner")
    )

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ProductViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun loadingStateIsHandledCorrectly() = runTest {
        assertEquals(false, viewModel.isLoading.first())
        coEvery { repository.getProductList() } returns mockProductItems
        viewModel.fetchProductItem()
        assertEquals(false, viewModel.isLoading.first())
    }
}
