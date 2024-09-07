package com.example.assignment_zampco.data

import com.example.assignment_zampco.model.ProductItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("5BEJ")
    suspend fun getData(): List<ProductItem>
}
