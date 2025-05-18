package com.example.restaurantreviewapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.restaurantreviewapp.model.Restaurant
import com.example.restaurantreviewapp.viewmodel.RestaurantViewModel
import com.example.restaurantreviewapp.ui.components.RestaurantItem
import com.example.restaurantreviewapp.ui.components.ReviewItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantDetailScreen(
    restaurant: Restaurant,
    viewModel: RestaurantViewModel = hiltViewModel()
) {
    val ratings = viewModel.ratings.collectAsState().value
    val error = viewModel.errorMessage.collectAsState().value

    if (ratings.isEmpty()) {
        viewModel.fetchRatingsByRestaurant(restaurant.id)
    }

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
                RestaurantItem(
                    restaurant = restaurant,
                    modifier = Modifier //
                )
            }

            items(ratings) { rating ->
                ReviewItem(review = rating)
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

            error?.let {
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}
