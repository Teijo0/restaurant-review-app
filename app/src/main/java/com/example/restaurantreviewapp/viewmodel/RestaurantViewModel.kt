package com.example.restaurantreviewapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    // Ravintolat StateFlow:nä UI-käyttöä varten
    private val _restaurants = MutableStateFlow<List<Restaurant>>(emptyList())
    val restaurants: StateFlow<List<Restaurant>> = _restaurants

    // Virheilmoitukset
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    /**
     * Hakee ravintolat API:sta ja päivittää StateFlow:n.
     */
    fun fetchRestaurants() {
        viewModelScope.launch {
            val call = RetrofitClient.restaurantApi.getRestaurants()

            call.enqueue(object : Callback<List<Restaurant>> {
                override fun onResponse(call: Call<List<Restaurant>>, response: Response<List<Restaurant>>) {
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
}
