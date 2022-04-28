package com.tip.peliculas;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tip.peliculas.adapter.MoviesAdapter;
import com.tip.peliculas.model.Movie;
import com.tip.peliculas.model.SearchMovieResponse;
import com.tip.peliculas.rest.MovieApiService;
import com.tip.peliculas.utils.OnItemClickListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText inputPelicula;
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String BASE_URL = Config.API_BASE_URL;
    private static Retrofit retrofit = null;
    private RecyclerView recyclerView = null;
    // insert your themoviedb.org API KEY here
    private final static String API_KEY = Config.API_KEY;
    private MoviesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        inputPelicula = findViewById(R.id.inputPelicula);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    
    public void buscarPelicula(View view){
        if(inputPelicula.getText().toString().equals("")){
            Toast.makeText(MainActivity.this, "No ingresó ningún dato", Toast.LENGTH_SHORT).show();
        }else{
            connectAndGetApiData(inputPelicula.getText().toString());
        }
    }
    
    public void connectAndGetApiData(String nombrePelicula){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        MovieApiService movieApiService = retrofit.create(MovieApiService.class);
        Call<SearchMovieResponse> call = movieApiService.search(API_KEY,nombrePelicula);
        call.enqueue(new Callback<SearchMovieResponse>() {
            @Override
            public void onResponse(Call<SearchMovieResponse> call, Response<SearchMovieResponse> response) {
                List<Movie> movies = response.body().getResults();

                adapter=new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext());
                //enlazo el clicklistener al adapter
                adapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(Movie item) {
                        //Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();
                        Intent i = new Intent(MainActivity.this, MovieDetail.class);
                        i.putExtra("pelicula", item);

                        startActivity(i);
                        //finish(); //Si quiero cerrar la actividad y quitarla del stack

                    }
                });
                recyclerView.setAdapter(adapter);
                Log.d(TAG, "Number of movies received: " + movies.size());
            }
            @Override
            public void onFailure(Call<SearchMovieResponse> call, Throwable throwable) {
                Log.e(TAG, throwable.toString());
            }
        });
    }
}
