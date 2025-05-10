package com.example.restaurantreviewapp.data

import com.example.restaurantreviewapp.model.Review
import java.time.LocalDate

val sampleReviews = listOf(
    Review(
        rating = 5.0,
        text = "Mahtava ruoka ja erinomainen palvelu!",
        date = LocalDate.of(2023, 12, 15)
    ),
    Review(
        rating = 4.5,
        text = "Todella hyvä burgeri, hieman ruuhkaa.",
        date = LocalDate.of(2024, 1, 10)
    ),
    Review(
        rating = 4.0,
        text = "Hyvä kokemus, tulen uudelleen.",
        date = LocalDate.of(2024, 2, 5)
    )
)
