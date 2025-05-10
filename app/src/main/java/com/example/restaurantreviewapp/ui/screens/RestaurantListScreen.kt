package com.example.restaurantreviewapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.restaurantreviewapp.viewmodel.RestaurantViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantListScreen(
    onRestaurantClick: (Int) -> Unit // Funktio ravintolan klikkaukselle
) {
    // Hakee ViewModelin instanssin
    val viewModel: RestaurantViewModel = viewModel()
    val restaurants = viewModel.restaurants.collectAsState().value
    val errorMessage = viewModel.errorMessage.collectAsState().value

    // Varmistetaan, että tiedot haetaan vain kerran
    if (restaurants.isEmpty()) {
        viewModel.fetchRestaurants()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Restaurants") }
            )
        },
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                if (errorMessage != null) {
                    Text(text = "Error: $errorMessage")
                } else {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(restaurants) { restaurant ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .clickable { onRestaurantClick(restaurant.id) }
                            ) {
                                // Näytetään ravintolan tiedot
                                Text(
                                    text = restaurant.name,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}
