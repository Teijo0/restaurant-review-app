package com.example.restaurantreviewapp.data

import com.example.restaurantreviewapp.model.Restaurant

val sampleRestaurants = listOf(
    Restaurant(
        id = 1,
        name = "Mahtava Ravintola",
        rating = 4.5,
        reviewCount = 132,
        type = "Italian",
        priceLevel = "€€",
        address = "Katutie 1, Helsinki",
        isOpen = true,
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4d/Cheeseburger.jpg/960px-Cheeseburger.jpg"
    ),
    Restaurant(
        id = 2,
        name = "Burger Heaven",
        rating = 4.0,
        reviewCount = 89,
        type = "American",
        priceLevel = "€",
        address = "Burgerkatu 5, Espoo",
        isOpen = false,
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4d/Cheeseburger.jpg/960px-Cheeseburger.jpg"
    )
)