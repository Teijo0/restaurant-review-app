package com.example.restaurantreviewapp.model

import java.util.Date

data class Review(
    val rating: Double,
    val text: String,
    val date: Date // Vaihdettu LocalDate -> Date
)
