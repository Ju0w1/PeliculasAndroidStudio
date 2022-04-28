package com.tip.peliculas.rest;

import com.tip.peliculas.model.SearchMovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApiService {

    //http://www.omdbapi.com/?s=texto
    //http://www.omdbapi.com/search?text=texto
    @GET(".")
    Call<SearchMovieResponse> search(@Query("apikey") String apiKey, @Query("s") String query);



}