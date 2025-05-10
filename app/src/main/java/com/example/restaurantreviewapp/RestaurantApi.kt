package com.example.restaurantreviewapp

import com.example.restaurantreviewapp.model.Rating
import com.example.restaurantreviewapp.model.Restaurant
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call

interface RestaurantApi {

    /**
     * Hakee kaikki ravintolat ja niiden arvosanat.
     * @return lista ravintoloista
     */
    @GET("/api/restaurants/ratings")
    fun getRestaurants(): Call<List<Restaurant>>

    /**
     * Hakee yksittäisen ravintolan tiedot.
     * @param resid ravintolan id
     * @return ravintolan tiedot
     */
    @GET("/api/restaurants/{resid}")
    fun getRestaurantById(@Path("resid") resid: Int): Call<Restaurant>

    /**
     * Hakee ravintolan arvosanat.
     * @param resid ravintolan id
     * @return lista arvosanoista
     */
    @GET("/api/restaurants/{resid}/ratings")
    fun getRatingsByRestaurant(@Path("resid") resid: Int): Call<List<Rating>>
}
