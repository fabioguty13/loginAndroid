package com.example.loginandroid.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val id: Int,
    val name: String,
    val image: String
)
