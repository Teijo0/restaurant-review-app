package com.example.restaurantreviewapp.ui.screens

import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.restaurantreviewapp.data.sampleReviews
import com.example.restaurantreviewapp.ui.components.ReviewItem
import com.example.restaurantreviewapp.model.Restaurant
import com.example.restaurantreviewapp.ui.theme.RestaurantReviewAppTheme

@Composable
fun RestaurantDetailScreen(
    restaurant: Restaurant
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(restaurant.name) }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                Text(text = restaurant.type, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(8.dp))

                // Ravintolan arvostelut
                LazyColumn(modifier = Modifier.padding(8.dp)) {
                    items(sampleReviews) { review ->
                        ReviewItem(review = review)
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                // Lisää uusi arvio -nappi
                Button(
                    onClick = { /* Ei toiminnallisuutta */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(text = "+ Add Review")
                }
            }
        }
    )
}
