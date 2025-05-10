package com.example.restaurantreviewapp.model

import java.time.LocalDate

data class Review(
    val rating: Double,
    val text: String,
    val date: LocalDate
)