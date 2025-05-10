package com.example.restaurantreviewapp

data class Restaurant(
    val name: String,
    val rating: Double,
    val reviewCount: Int,
    val type: String,
    val priceLevel: String,
    val address: String,
    val isOpen: Boolean,
    val imageUrl: String
)
