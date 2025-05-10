package com.example.restaurantreviewapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantreviewapp.model.Restaurant
import com.example.restaurantreviewapp.model.Review
import com.example.restaurantreviewapp.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel, joka huolehtii ravintoloiden ja arvostelujen hakemisesta REST-rajapinnan kautta.
 * Käyttää StateFlow:ta datan hallintaan ja Hilt:iä riippuvuuksien injektointiin.
 */
@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    // Ravintolat StateFlow:nä UI-käyttöä varten
    private val _restaurants = MutableStateFlow<List<Restaurant>>(emptyList())
    val restaurants: StateFlow<List<Restaurant>> = _restaurants

    // Arvostelut StateFlow:nä UI-käyttöä varten
    private val _reviews = MutableStateFlow<List<Review>>(emptyList())
    val reviews: StateFlow<List<Review>> = _reviews

    /**
     * Hakee ravintolat API:sta ja päivittää StateFlow:n.
     */
    fun fetchRestaurants() {
        viewModelScope.launch {
            try {
                val result = apiService.getRestaurants()
                _restaurants.value = result
            } catch (e: Exception) {
                // TODO: Virheenkäsittely (näytä viesti, loggaa, jne.)
            }
        }
    }

    /**
     * Hakee arvostelut API:sta ja päivittää StateFlow:n.
     */
    fun fetchReviews() {
        viewModelScope.launch {
            try {
                val result = apiService.getReviews()
                _reviews.value = result
            } catch (e: Exception) {
                // TODO: Virheenkäsittely
            }
        }
    }
}
