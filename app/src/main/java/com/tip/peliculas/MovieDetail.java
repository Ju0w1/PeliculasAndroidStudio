package com.tip.peliculas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import com.tip.peliculas.model.Movie;

public class MovieDetail extends AppCompatActivity {
    Movie item;
    RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Intent i = getIntent();
        item = (Movie)i.getSerializableExtra("pelicula");

        ratingBar = findViewById(R.id.rating);
        Toast.makeText(MovieDetail.this, item.getTitle(), Toast.LENGTH_LONG).show();


    }

    public void compartir(View view) {
        Intent  i = new Intent();
        i.setAction( Intent.ACTION_SEND);
        Uri imageUri =  Uri.parse(item.getPoster());
        i.putExtra(
                Intent.EXTRA_STREAM,
                imageUri
//                "Mira: "+item.getTitle()+" , yo le dí "+ratingBar.getRating()+" estrellas"
        );
        i.setType("image/*");
        startActivity(i);
    }
}
