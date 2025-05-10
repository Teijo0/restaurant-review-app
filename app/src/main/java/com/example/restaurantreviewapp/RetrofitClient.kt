package com.example.restaurantreviewapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://example.com"  // Käytä oikeaa backend-URL:ia

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val restaurantApi: RestaurantApi = retrofit.create(RestaurantApi::class.java)
}
