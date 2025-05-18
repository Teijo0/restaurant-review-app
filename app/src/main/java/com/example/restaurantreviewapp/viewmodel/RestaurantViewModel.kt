package com.example.restaurantreviewapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantreviewapp.model.Rating
import com.example.restaurantreviewapp.model.Restaurant
import com.example.restaurantreviewapp.network.RetrofitClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor() : ViewModel() {

    // Ravintolalista
    private val _restaurants = MutableStateFlow<List<Restaurant>>(emptyList())
    val restaurants: StateFlow<List<Restaurant>> = _restaurants

    // Valitun ravintolan arvostelut
    private val _ratings = MutableStateFlow<List<Rating>>(emptyList())
    val ratings: StateFlow<List<Rating>> = _ratings

    // Virheilmoitukset
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    /**
     * Hakee kaikki ravintolat backendistä.
     */
    fun fetchRestaurants() {
        viewModelScope.launch {
            val call = RetrofitClient.restaurantApi.getRestaurants()

            call.enqueue(object : Callback<List<Restaurant>> {
                override fun onResponse(
                    call: Call<List<Restaurant>>,
                    response: Response<List<Restaurant>>
                ) {
                    if (response.isSuccessful) {
                        _restaurants.value = response.body() ?: emptyList()
                    } else {
                        _errorMessage.value = "Failed to fetch restaurants"
                    }
                }

                override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
                    _errorMessage.value = t.message
                }
            })
        }
    }

    /**
     * Hakee yksittäisen ravintolan arvostelut ID:n perusteella.
     */
    fun fetchRatingsByRestaurant(resId: Int) {
        viewModelScope.launch {
            val call = RetrofitClient.restaurantApi.getRatingsByRestaurant(resId)

            call.enqueue(object : Callback<List<Rating>> {
                override fun onResponse(
                    call: Call<List<Rating>>,
                    response: Response<List<Rating>>
                ) {
                    if (response.isSuccessful) {
                        _ratings.value = response.body() ?: emptyList()
                    } else {
                        _errorMessage.value = "Failed to fetch ratings"
                    }
                }

                override fun onFailure(call: Call<List<Rating>>, t: Throwable) {
                    _errorMessage.value = t.message
                }
            })
        }
    }

    // Yksittäinen ravintola (kun haetaan suoraan ID:llä)
    private val _restaurant = MutableStateFlow<Restaurant?>(null)
    val restaurant: StateFlow<Restaurant?> = _restaurant

    /**
     * Hakee yksittäisen ravintolan tiedot ID:n perusteella.
     */
    fun fetchRestaurantById(id: Int) {
        viewModelScope.launch {
            val call = RetrofitClient.restaurantApi.getRestaurantById(id)

            call.enqueue(object : Callback<Restaurant> {
                override fun onResponse(call: Call<Restaurant>, response: Response<Restaurant>) {
                    if (response.isSuccessful) {
                        _restaurant.value = response.body()
                    } else {
                        _errorMessage.value = "Failed to fetch restaurant details"
                    }
                }

                override fun onFailure(call: Call<Restaurant>, t: Throwable) {
                    _errorMessage.value = t.message
                }
            })
        }
    }

    /**
     * Tyhjentää virheilmoituksen (esim. kun käyttäjä sulkee virheviestin).
     */
    fun clearError() {
        _errorMessage.value = null
    }
}
