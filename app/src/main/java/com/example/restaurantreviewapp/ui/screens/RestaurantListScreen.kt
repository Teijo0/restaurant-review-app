package com.example.restaurantreviewapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.restaurantreviewapp.ui.components.RestaurantItem
import com.example.restaurantreviewapp.viewmodel.RestaurantViewModel
import com.example.restaurantreviewapp.model.Restaurant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantListScreen(
    onRestaurantClick: (Int) -> Unit,
    viewModel: RestaurantViewModel = hiltViewModel()
) {
    val restaurants by viewModel.restaurants.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    // Hae ravintolat vain jos lista on tyhjä
    if (restaurants.isEmpty()) {
        viewModel.fetchRestaurants()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Restaurants") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(8.dp)
        ) {
            // Näytä virheviesti, jos sellainen on
            if (errorMessage != null) {
                Text(
                    text = errorMessage ?: "Unknown error",
                    color = MaterialTheme.colorScheme.error
                )
            }

            // Listaa ravintolat LazyColumnilla
            LazyColumn {
                items(restaurants) { restaurant: Restaurant ->
                    RestaurantItem(
                        restaurant = restaurant,
                        onItemClick = { onRestaurantClick(restaurant.id) }, // Klikkaus välitetään ylös
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }
    }
}
