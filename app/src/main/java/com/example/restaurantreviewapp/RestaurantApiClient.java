package com.example.restaurantreviewapp;

public class RestaurantApiClient {
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.List;

    /**
     * Luokka, joka käyttää Retrofitia API-kutsujen tekemiseen ja datan hakemiseen.
     */
    public class RestaurantApiClient {

        private static final String BASE_URL = "http://localhost:8000";  // Muokkaa tarvittaessa palvelimen URL

        private Retrofit retrofit;
        private RestaurantApi restaurantApi;

        /**
         * Alustaa Retrofitin ja määrittelee API-rajapinnan.
         */
        public RestaurantApiClient() {
            // Luodaan Retrofit-instanssi
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())  // Muuntaa JSON:n Java-objekteiksi
                    .build();

            // Luodaan API-rajapinta
            restaurantApi = retrofit.create(RestaurantApi.class);
        }

        /**
         * Hakee kaikki ravintolat.
         * @param callback palauttaa tulokset tai virheen
         */
        public void getRestaurants(final RestaurantApiCallback callback) {
            Call<List<Restaurant>> call = restaurantApi.getRestaurants();

            call.enqueue(new Callback<List<Restaurant>>() {
                @Override
                public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                    if (response.isSuccessful()) {
                        // Kutsun onnistuminen, palauta ravintolat
                        callback.onSuccess(response.body());
                    } else {
                        // Kutsun epäonnistuminen, palauta virhe
                        callback.onError("Virhe: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                    // Verkko- tai muu virhe
                    callback.onError("Verkkovirhe: " + t.getMessage());
                }
            });
        }

        /**
         * Ravintolan arvosanojen hakeminen
         * @param resid ravintolan id
         * @param callback palauttaa arvosanat tai virheen
         */
        public void getRatingsByRestaurant(int resid, final RestaurantApiCallback callback) {
            Call<List<Rating>> call = restaurantApi.getRatingsByRestaurant(resid);

            call.enqueue(new Callback<List<Rating>>() {
                @Override
                public void onResponse(Call<List<Rating>> call, Response<List<Rating>> response) {
                    if (response.isSuccessful()) {
                        // Kutsun onnistuminen, palauta arvosanat
                        callback.onSuccess(response.body());
                    } else {
                        // Kutsun epäonnistuminen, palauta virhe
                        callback.onError("Virhe: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<List<Rating>> call, Throwable t) {
                    // Verkko- tai muu virhe
                    callback.onError("Verkkovirhe: " + t.getMessage());
                }
            });
        }

        /**
         * Callback-rajapinta, joka käsittelee API-kutsujen vastaukset.
         */
        public interface RestaurantApiCallback {
            void onSuccess(Object data);
            void onError(String error);
        }
    }
