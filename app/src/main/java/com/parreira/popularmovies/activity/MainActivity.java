package com.parreira.popularmovies.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;

import android.view.MenuItem;
import android.widget.Toast;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.parreira.popularmovies.R;
import com.parreira.popularmovies.adapter.MyMoviesAdapter;
import com.parreira.popularmovies.network.FilmeService;
import com.parreira.popularmovies.network.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private MyMoviesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        List<Filme> myFilmes = new ArrayList<>();
        criaArrayFilmes(myFilmes);


        //startMovieGrid(myFilmes);
    }


    private void startMovieGrid(List<Filme> filmes) {


        GridLayoutManager layoutManager;

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_movies_images);
        //recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(MainActivity.this, 3);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyMoviesAdapter(this, filmes);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_item_rating:
                Toast toast = Toast.makeText(this, "Sort by rating", Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.menu_item_popularity:
                Toast toast2 = Toast.makeText(this, "Sort by popularity", Toast.LENGTH_SHORT);
                toast2.show();
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_grid, menu);
        return true;
    }

    // cria ListArray de 8 filmes
    void criaArrayFilmes(final List<Filme> filmes) {

        FilmeService service = RetrofitClientInstance.getRetrofitInstance().create(FilmeService.class);
        Call<Api> call = service.getFilmes();
        call.enqueue(new Callback<Api>() {
            @Override
            public void onResponse(Call<Api> call, Response<Api> response) {
                filmes.addAll(response.body().getFilmeList());
                Log.d("Callback", response.message());
               // mAdapter.notifyDataSetChanged();
                startMovieGrid(filmes);
            }

            @Override
            public void onFailure(Call<Api> call, Throwable t) {
                Log.d("Callback", "Failure");
            }
        });

    }


    // cria um filme aleatório
    Filme criaFilme() {
        Filme filme = new Filme();
        filme.setTitle("Interstellar");
        filme.setImage("https://image.tmdb.org/t/p/w342//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg");
        filme.setReleaseDate("1999-10-12");
        filme.setUserRating((float) 5);
        filme.setSynopsis("\"A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \\\"fight clubs\\\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.");

        return filme;
    }
}
