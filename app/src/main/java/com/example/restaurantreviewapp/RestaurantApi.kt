package com.example.restaurantreviewapp;

public class RestaurantApi {
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import java.util.List;

    /**
     * Retrofit-rajapinta ravintoloiden API-kutsuja varten.
     */
    public interface RestaurantApi {

        /**
         * Hakee kaikki ravintolat ja niiden arvosanat.
         * @return lista ravintoloista
         */
        @GET("/api/restaurants/ratings")
        Call<List<Restaurant>> getRestaurants();

        /**
         * Hakee yksittäisen ravintolan tiedot.
         * @param resid ravintolan id
         * @return ravintolan tiedot
         */
        @GET("/api/restaurants/{resid}")
        Call<Restaurant> getRestaurantById(@Path("resid") int resid);

        /**
         * Hakee ravintolan arvosanat.
         * @param resid ravintolan id
         * @return lista arvosanoista
         */
        @GET("/api/restaurants/{resid}/ratings")
        Call<List<Rating>> getRatingsByRestaurant(@Path("resid") int resid);
    }
