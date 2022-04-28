package com.tip.peliculas.rest;

import com.tip.peliculas.Config;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {
    private static <T> T builder(Class<T> endpoint) {
        return new Retrofit.Builder()
                .baseUrl(Config.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(endpoint);
    }

    public static MovieApiService movies() {
        return builder(MovieApiService.class);
    }


}
