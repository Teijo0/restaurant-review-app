package com.example.restaurantreviewapp.model

data class Restaurant(
    val id: Int,
    val name: String,
    val rating: Double,
    val reviewCount: Int,
    val type: String,
    val priceLevel: String,
    val address: String,
    val isOpen: Boolean,
    val imageUrl: String
)
