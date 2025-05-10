package com.example.restaurantreviewapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.restaurantreviewapp.data.sampleReviews
import com.example.restaurantreviewapp.model.Restaurant
import com.example.restaurantreviewapp.ui.components.RestaurantItem
import com.example.restaurantreviewapp.ui.components.ReviewItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantDetailScreen(
    restaurant: Restaurant
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(restaurant.name) }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentPadding = PaddingValues(8.dp)
        ) {
            item {
                // Näytetään ravintolan tiedot ylhäällä
                RestaurantItem(restaurant = restaurant)
            }

            items(sampleReviews) { review ->
                ReviewItem(review = review)
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { /* Ei toiminnallisuutta vielä */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(text = "+ Add Review")
                }
            }
        }
    }
}
