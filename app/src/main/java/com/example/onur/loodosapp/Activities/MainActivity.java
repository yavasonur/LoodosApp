package com.example.onur.loodosapp.Activities;

import android.support.transition.Transition;
import android.support.transition.TransitionInflater;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.onur.loodosapp.Adapters.FilmRvAdapter;
import com.example.onur.loodosapp.Model.Film;
import com.example.onur.loodosapp.R;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.circular_progress) ProgressView circularProgress;

    private String Url_JSON = "http://www.omdbapi.com/?s=peace&apikey=1d60cee2";
    private JsonObjectRequest ObjectRequest;
    private RequestQueue requestQueue;
    private FilmRvAdapter filmRvAdapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getFilmListAPI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search,menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        searchView = (SearchView)item.getActionView();
        searchView.setQueryHint("Film Name...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                newText = newText.toLowerCase();
                List<Film> newList = new ArrayList<>();
                for(Film film : Film.filmList){

                    String name = film.getTitle().toLowerCase();
                    if(name.contains(newText)){
                        newList.add(film);
                    }
                }
                filmRvAdapter.setFilter(newList);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void getFilmListAPI(){

        ObjectRequest = new JsonObjectRequest(Request.Method.GET, Url_JSON, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("Search");

                    for(int i=0; i<jsonArray.length(); i++){

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        Film film = new Film(jsonObject.getString("Title"),jsonObject.getString("Year"),
                                jsonObject.getString("imdbID"), jsonObject.getString("Type"),
                                jsonObject.getString("Poster"));

                        Film.filmList.add(film);
                    }
                    circularProgress.setVisibility(View.INVISIBLE);

                }catch (JSONException e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Hata!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(ObjectRequest);

        filmRvAdapter = new FilmRvAdapter(this,Film.filmList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(filmRvAdapter);
    }

}
