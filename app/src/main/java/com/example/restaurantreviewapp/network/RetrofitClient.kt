package com.example.restaurantreviewapp.network

import com.example.restaurantreviewapp.RestaurantApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:8000/" // backend-URL

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val restaurantApi: RestaurantApi = retrofit.create(RestaurantApi::class.java)
}
