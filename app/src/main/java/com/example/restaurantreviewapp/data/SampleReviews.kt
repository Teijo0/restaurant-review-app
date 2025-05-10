package com.example.restaurantreviewapp.data

import com.example.restaurantreviewapp.model.Review
import java.text.SimpleDateFormat
import java.util.*

val sampleReviews = listOf(
    Review(
        rating = 5.0,
        text = "Mahtava ruoka ja erinomainen palvelu!",
        date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse("2023-12-15")!!
    ),
    Review(
        rating = 4.5,
        text = "Todella hyvä burgeri, hieman ruuhkaa.",
        date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse("2024-01-10")!!
    ),
    Review(
        rating = 4.0,
        text = "Hyvä kokemus, tulen uudelleen.",
        date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse("2024-02-05")!!
    )
)
