package com.example.onur.loodosapp.Activities;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.onur.loodosapp.Model.FDetail;
import com.example.onur.loodosapp.R;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rey.material.widget.ProgressView;


import butterknife.BindView;
import butterknife.ButterKnife;

public class FilmDetail extends AppCompatActivity {

    @BindView(R.id.imageViewFilmDetail) ImageView imageViewFilmDetail;
    @BindView(R.id.txtFilmDetailTitle) TextView txtFilmDetailTitle;
    @BindView(R.id.txtFilmDetailType) TextView txtFilmDetailType;
    @BindView(R.id.txtFilmDetailRating) TextView txtFilmDetailRating;
    @BindView(R.id.txtFilmDetailRunTime) TextView txtFilmDetailRunTime;
    @BindView(R.id.txtFilmDetailPlot) TextView txtFilmDetailPlot;
    @BindView(R.id.collapsingToolBar) CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.circular_progress_filmDetail) ProgressView circularProgressFilmDetail;

    private String Url_JSON = "http://www.omdbapi.com/?apikey=1d60cee2&i=";
    private StringRequest stringRequest;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        getComingIntent();
    }

    private void getComingIntent(){
        if(getIntent().hasExtra("imdbId")){
            String imdbID = getIntent().getStringExtra("imdbId");
            getFilmDetailAPI(imdbID);
        }else{
            Log.d( "TAG","Not found ImdbID");
            Toast.makeText(this, "Not found ImdbID", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void getFilmDetailAPI(String imdbID) {

        final Gson gson = new Gson();

        Url_JSON += imdbID;

        stringRequest = new StringRequest(Request.Method.GET, Url_JSON, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
                FDetail fDetail = new FDetail();
                fDetail.setTitle(jsonObject.get("Title").getAsString());
                fDetail.setGenre(jsonObject.get("Genre").getAsString());
                fDetail.setImdbRating(jsonObject.get("imdbRating").getAsString());
                fDetail.setPlot(jsonObject.get("Plot").getAsString());
                fDetail.setRunTime(jsonObject.get("Runtime").getAsString());
                fDetail.setPoster(jsonObject.get("Poster").getAsString());
                circularProgressFilmDetail.setVisibility(View.INVISIBLE);
                setValue(fDetail);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(FilmDetail.this, "Error Request!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        requestQueue = Volley.newRequestQueue(getBaseContext());
        requestQueue.add(stringRequest);
    }

    private void setValue(FDetail fDetail) {
        txtFilmDetailTitle.setText(fDetail.getTitle());
        txtFilmDetailType.setText(fDetail.getGenre());
        txtFilmDetailRating.setText(fDetail.getImdbRating());
        txtFilmDetailPlot.setText(fDetail.getPlot());
        txtFilmDetailRunTime.setText(fDetail.getRunTime());
        collapsingToolbarLayout.setTitleEnabled(true);
        collapsingToolbarLayout.setTitle(fDetail.getTitle());
        Glide.with(getBaseContext()).load(fDetail.getPoster()).into(imageViewFilmDetail);
    }
}
