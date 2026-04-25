package com.example.loginandroid.data.repository

import com.example.loginandroid.data.network.client
import com.example.loginandroid.data.network.model.Category
import io.ktor.client.call.body
import io.ktor.client.request.get

class CategoryRepository {
    suspend fun getCategories(): List<Category> {
        return client.get("https://api.escuelajs.co/api/v1/categories").body()
    }
}
