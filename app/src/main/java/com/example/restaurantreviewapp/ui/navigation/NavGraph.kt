package com.example.restaurantreviewapp.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.restaurantreviewapp.model.Restaurant
import com.example.restaurantreviewapp.ui.screens.RestaurantDetailScreen
import com.example.restaurantreviewapp.ui.screens.RestaurantListScreen
import com.example.restaurantreviewapp.viewmodel.RestaurantViewModel

sealed class Screen(val route: String) {
    object List : Screen("restaurant_list")
    object Detail : Screen("restaurant_detail/{restaurantId}") {
        fun createRoute(id: Int) = "restaurant_detail/$id"
    }
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.List.route) {
        composable(Screen.List.route) {
            RestaurantListScreen(
                onRestaurantClick = { index ->
                    navController.navigate(Screen.Detail.createRoute(index))
                }
            )
        }

        composable(Screen.Detail.route) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("restaurantId")?.toIntOrNull()
            val viewModel: RestaurantViewModel = viewModel()
            val restaurantList = viewModel.restaurants.collectAsState().value
            val fallbackRestaurant = viewModel.restaurant.collectAsState().value

            val restaurant = restaurantList.firstOrNull { it.id == id }

            if (restaurant != null) {
                RestaurantDetailScreen(restaurant = restaurant)
            } else if (id != null) {
                // Jos ei löydy listalta, hae suoraan back-endistä
                if (fallbackRestaurant?.id != id) {
                    viewModel.fetchRestaurantById(id)
                }

                fallbackRestaurant?.let {
                    RestaurantDetailScreen(restaurant = it)
                }
            }
        }
    }
}
