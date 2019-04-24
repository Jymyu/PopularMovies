package com.parreira.popularmovies.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.parreira.popularmovies.R;
import com.parreira.popularmovies.adapter.EndlessRecyclerViewOnScrollListener;
import com.parreira.popularmovies.adapter.MyMoviesAdapter;
import com.parreira.popularmovies.network.FilmeService;
import com.parreira.popularmovies.network.RetrofitClientInstance;
import com.parreira.popularmovies.viewModel.FilmeViewModel;
import com.parreira.popularmovies.viewModel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    FilmeViewModel viewModelPopular;
    private MyMoviesAdapter mAdapter;
    List<Filme> myFilmesRating = new ArrayList<>();

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private int mLoadedItems = 0;
    public boolean isFavouritesOn = false;
    boolean isPopularOn = false;
    boolean isRatedOn = false;
    private TextView tvNoInternet;
    private Button btnNoConnection;
    boolean temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        progressBar = (ProgressBar) findViewById(R.id.pb_progressbar);
        progressBar.setVisibility(View.VISIBLE);
        tvNoInternet = (TextView) findViewById(R.id.tv_no_internet);
        tvNoInternet.setVisibility(View.INVISIBLE);
        btnNoConnection = (Button) findViewById(R.id.btn_tenta_novamente);
        GridLayoutManager layoutManager;

        recyclerView = (RecyclerView) findViewById(R.id.rv_movies_images);

        layoutManager = new GridLayoutManager(MainActivity.this, 3);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyMoviesAdapter(this);


        criaArrayFilmesByPopular();
        recyclerView.clearOnScrollListeners();
        recyclerView.addOnScrollListener(new EndlessRecyclerViewOnScrollListener() {
            @Override
            public void onLoadMore() {
              viewModelPopular.getMorePopularMovies();


            }
        });



        btnNoConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                btnNoConnection.setVisibility(View.GONE);
                tvNoInternet.setVisibility(View.GONE);
                new CountDownTimer(3000,1000) {
                    public void onFinish() {


                    }

                    public void onTick(long millisUntilFinished) {
                        // millisUntilFinished    The amount of time until finished.
                    }
                }.start();


            }
        });
    }


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
                myFilmesRating.clear();
                isFavouritesOn = false;
                mAdapter.setFavourite(isFavouritesOn);
                recyclerView.clearOnScrollListeners();
                recyclerView.addOnScrollListener(new EndlessRecyclerViewOnScrollListener() {
                    @Override
                    public void onLoadMore() {
                        criaArrayFilmesByRating(myFilmesRating, myFilmesRating.size() / 10);

                    }
                });
                criaArrayFilmesByRating(myFilmesRating, 1);


                break;
            case R.id.menu_item_favourite:

                isFavouritesOn = true;
                mAdapter.setFavourite(isFavouritesOn);
                recyclerView.clearOnScrollListeners();
                criaArrayFilmesByFavourites();

                break;

            case R.id.menu_item_popularity:
                criaArrayFilmesByPopular();
                recyclerView.clearOnScrollListeners();
                recyclerView.addOnScrollListener(new EndlessRecyclerViewOnScrollListener() {
                    @Override
                    public void onLoadMore() {
                        viewModelPopular.getMorePopularMovies();


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

    // cria ListArray de 8 filmes
    public void criaArrayFilmesByPopular() {

        viewModelPopular = ViewModelProviders.of(this).get(FilmeViewModel.class);

        viewModelPopular.getFilmes().observe(this, new Observer<List<Filme>>() {
            @Override
            public void onChanged(@Nullable List<Filme> filmes) {
                startMovieGrid(filmes);
            }
        });

    }

    void criaArrayFilmesByRating(final List<Filme> filmes, int page) {
        isFavouritesOn = false;
        isPopularOn = false;
        isRatedOn = true;

        FilmeService service = RetrofitClientInstance.getRetrofitInstance().create(FilmeService.class);
        Call<Api> call = service.getFilmesByRating(page);
        call.enqueue(new Callback<Api>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<Api> call, Response<Api> response) {
                filmes.addAll(response.body().getFilmeList());
                Log.d("Callback", response.message());
                startMovieGrid(filmes);
            }

            @Override
            public void onFailure(Call<Api> call, Throwable t) {
                Log.d("Callback", "Failure");
            }
        });

    }

    private void criaArrayFilmesByFavourites() {


        isFavouritesOn = true;
        isPopularOn = false;
        isRatedOn = false;



        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        viewModel.getFilmes().observe(this, new Observer<List<Filme>>() {
            @Override
            public void onChanged(@Nullable List<Filme> filmes) {
                startMovieGrid(filmes);
            }
        });


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putBoolean("favourite", isFavouritesOn);
        outState.putBoolean("popular", isPopularOn);
        outState.putBoolean("rated", isRatedOn);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy", "onDestroy");
    }
}
