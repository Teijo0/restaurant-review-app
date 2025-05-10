package com.example.restaurantreviewapp.data

import com.example.restaurantreviewapp.model.Restaurant

val sampleRestaurants = listOf(
    Restaurant(
        name = "Mahtava Ravintola",
        rating = 4.5,
        reviewCount = 132,
        type = "Italian",
        priceLevel = "€€",
        address = "Katutie 1, Helsinki",
        isOpen = true,
        imageUrl = "https://example.com/cheese_burger.jpg"
    ),
    Restaurant(
        name = "Burger Heaven",
        rating = 4.0,
        reviewCount = 89,
        type = "American",
        priceLevel = "€",
        address = "Burgerkatu 5, Espoo",
        isOpen = false,
        imageUrl = "https://example.com/cheese_burger.jpg"
    )
)