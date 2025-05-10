package com.example.restaurantreviewapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.restaurantreviewapp.data.sampleRestaurants
import com.example.restaurantreviewapp.ui.components.RestaurantItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantListScreen(
    onRestaurantClick: (Int) -> Unit // Funktio ravintolan klikkaukselle
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Restaurants") } // Otsikko
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues) // Sisältö ja padding
                    .fillMaxSize() // Täyttää koko tilan
            ) {
                itemsIndexed(sampleRestaurants) { index, restaurant ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth() // Täyttää koko leveyden
                            .padding(8.dp) // Lisää paddingia
                            .clickable { onRestaurantClick(index) } // Kutsuu navigointifunktiota
                    ) {
                        RestaurantItem(restaurant = restaurant) // Näyttää ravintolan tiedot
                    }
                }
            }
        }
    )
}
