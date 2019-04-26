package com.parreira.popularmovies.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.parreira.popularmovies.R;
import com.parreira.popularmovies.adapter.EndlessRecyclerViewOnScrollListener;
import com.parreira.popularmovies.adapter.MyMoviesAdapter;
import com.parreira.popularmovies.model.Filme;
import com.parreira.popularmovies.viewModel.FilmePopularViewModel;

import com.parreira.popularmovies.viewModel.FilmeRatingViewModel;
import com.parreira.popularmovies.viewModel.FilmeFavoritoViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FilmePopularViewModel viewModelPopular;
    FilmeRatingViewModel viewModelRating;
    private MyMoviesAdapter mAdapter;


    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    private TextView tvNoInternet;
    private Button btnNoConnection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        progressBar = (ProgressBar) findViewById(R.id.pb_progressbar);
        progressBar.setVisibility(View.VISIBLE);
        tvNoInternet = (TextView) findViewById(R.id.tv_no_internet);
        tvNoInternet.setVisibility(View.INVISIBLE);
        btnNoConnection = (Button) findViewById(R.id.btn_tenta_novamente);
        btnNoConnection.setVisibility(View.INVISIBLE);
        GridLayoutManager layoutManager;

        recyclerView = (RecyclerView) findViewById(R.id.rv_movies_images);

        layoutManager = new GridLayoutManager(MainActivity.this, 3);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyMoviesAdapter(this);


        // Start the presentation of movies in the MainActivity by popularity
        criaArrayFilmesByPopular();


        // ad more movies as the list is scrolled
        recyclerView.clearOnScrollListeners();
        recyclerView.addOnScrollListener(new EndlessRecyclerViewOnScrollListener() {
            @Override
            public void onLoadMore() {
                if (viewModelPopular.getFilmes().getValue().size() == mAdapter.getItemCount()) {
                    viewModelPopular.getMorePopularMovies();
                }
                criaArrayFilmesByPopular();

            }
        });

        // Button try connection again
        btnNoConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                btnNoConnection.setVisibility(View.GONE);
                tvNoInternet.setVisibility(View.GONE);
                new CountDownTimer(3000, 1000) {
                    public void onFinish() {
                    }

                    public void onTick(long millisUntilFinished) {
                        // millisUntilFinished    The amount of time until finished.
                    }
                }.start();

            }
        });
    }

    //upload the list filmes to the adapter of recyclerView
    private void startMovieGrid(List<Filme> filmes) {

        if (mAdapter.getItemCount() == 0) {
            mAdapter.setItens(filmes);
            recyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setItens(filmes);
            mAdapter.notifyDataSetChanged();

        }

        progressBar.setVisibility(View.GONE);
        tvNoInternet.setVisibility(View.GONE);
        btnNoConnection.setVisibility(View.GONE);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_item_rating:
                criaArrayFilmesByRating();

                recyclerView.clearOnScrollListeners();
                recyclerView.addOnScrollListener(new EndlessRecyclerViewOnScrollListener() {
                    @Override
                    public void onLoadMore() {
                        if (viewModelRating.getFilmes().getValue().size() == mAdapter.getItemCount()) {
                            viewModelRating.getMoreRatingMovies();
                        }
                        criaArrayFilmesByRating();
                    }
                });
                break;

            case R.id.menu_item_favourite:
                recyclerView.clearOnScrollListeners();
                criaArrayFilmesByFavourites();
                break;

            case R.id.menu_item_popularity:
                criaArrayFilmesByPopular();
                recyclerView.clearOnScrollListeners();
                recyclerView.addOnScrollListener(new EndlessRecyclerViewOnScrollListener() {
                    @Override
                    public void onLoadMore() {
                        if (viewModelPopular.getFilmes().getValue().size() == mAdapter.getItemCount()) {
                            viewModelPopular.getMorePopularMovies();
                        }
                        criaArrayFilmesByPopular();

                    }
                });

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_grid, menu);
        return true;
    }

    // presents the Movies by popularity
    public void criaArrayFilmesByPopular() {

        viewModelPopular = ViewModelProviders.of(this).get(FilmePopularViewModel.class);

        viewModelPopular.getFilmes().observe(this, new Observer<List<Filme>>() {
            @Override
            public void onChanged(@Nullable List<Filme> filmes) {
                startMovieGrid(filmes);
            }
        });

    }
    // presents the Movies by rating
    void criaArrayFilmesByRating() {

        viewModelRating = ViewModelProviders.of(this).get(FilmeRatingViewModel.class);

        viewModelRating.getFilmes().observe(this, new Observer<List<Filme>>() {
            @Override
            public void onChanged(@Nullable List<Filme> filmes) {
                startMovieGrid(filmes);
            }
        });

    }
    // presents the Favourite Movies
    private void criaArrayFilmesByFavourites() {


        FilmeFavoritoViewModel viewModel = ViewModelProviders.of(this).get(FilmeFavoritoViewModel.class);

        viewModel.getFilmes().observe(this, new Observer<List<Filme>>() {
            @Override
            public void onChanged(@Nullable List<Filme> filmes) {
                startMovieGrid(filmes);
            }
        });


    }

}
