package com.tip.peliculas.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchMovieResponse {
    @SerializedName("Search")
    private List<Movie> results;

    public List<Movie> getResults() {
        return results;
    }

}
