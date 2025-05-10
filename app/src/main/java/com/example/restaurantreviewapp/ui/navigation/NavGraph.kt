package com.example.restaurantreviewapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.restaurantreviewapp.data.sampleRestaurants
import com.example.restaurantreviewapp.ui.screens.RestaurantDetailScreen
import com.example.restaurantreviewapp.ui.screens.RestaurantListScreen

sealed class Screen(val route: String) {
    object List : Screen("restaurant_list")
    object Detail : Screen("restaurant_detail/{restaurantIndex}") {
        fun createRoute(index: Int) = "restaurant_detail/$index"
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
            val index = backStackEntry.arguments?.getString("restaurantIndex")?.toIntOrNull() ?: 0
            val restaurant = sampleRestaurants.getOrNull(index)
            if (restaurant != null) {
                RestaurantDetailScreen(restaurant = restaurant)
            }
        }
    }
}
