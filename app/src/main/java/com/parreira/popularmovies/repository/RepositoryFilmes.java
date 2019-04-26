package com.parreira.popularmovies.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.parreira.popularmovies.model.Api;
import com.parreira.popularmovies.model.Filme;
import com.parreira.popularmovies.model.Review;
import com.parreira.popularmovies.model.Trailer;
import com.parreira.popularmovies.database.DaoFilme;
import com.parreira.popularmovies.database.FilmeDatabase;
import com.parreira.popularmovies.network.FilmeService;
import com.parreira.popularmovies.network.RetrofitClientInstance;
import com.parreira.popularmovies.network.ReviewAPI;
import com.parreira.popularmovies.network.TrailerAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by João Parreira on 4/23/2019.
 * <p>
 * ITSector ITSector
 * joao.parreira@itsector.pt
 * <p>
 * Copyright (c) ITSector Software. All rights reserved.
 * <p>
 * ITSector Software Confidential and Proprietary information. It is strictly forbidden for 3rd
 * parties to modify, decompile, disassemble, defeat, disable or circumvent any protection
 * mechanism; to sell, license, lease, rent, redistribute or make accessible to any third party,
 * whether for profit or without charge.
 */
public class RepositoryFilmes {

    Boolean isLoading;
    private DaoFilme mDaoFilme;
    private LiveData<List<Filme>> mFilmesFavoritos;
    private LiveData<List<Filme>> mFilmesPopular;
    private LiveData<List<Filme>> mFilmesRating;
    final List<Filme> filmesPopular = new ArrayList<>();
    final List<Filme> filmesRating = new ArrayList<>();
    private int mCurrentPagePop = 1;
    private int mCurrentPageRat = 1;


    public RepositoryFilmes() {

    }


    public RepositoryFilmes(Application application) {
        FilmeDatabase database = FilmeDatabase.getAppDatabase(application);
        mDaoFilme = database.daoFilme();
        mFilmesFavoritos = mDaoFilme.getFilmeAll();
        mFilmesPopular = getFilmesByPopular(mCurrentPagePop);
        mFilmesRating = getFilmesByRating(mCurrentPageRat);
        mCurrentPagePop = mCurrentPagePop + 1;
        mCurrentPageRat = mCurrentPageRat + 1;
        isLoading = false;
    }

    //add one more page of movies by popularity
    public void adicionaFilmesPopular() {
        mFilmesPopular = getFilmesByPopular(mCurrentPagePop);
        mCurrentPagePop = mCurrentPagePop + 1;
    }

    //add one more page of movies by rating
    public void adicionaFilmesRating() {
        mFilmesRating = getFilmesByRating(mCurrentPageRat);
        mCurrentPageRat = mCurrentPageRat + 1;

    }


    public void insert(Filme filme) {
        new InsertFilmeAsyncTask(mDaoFilme).execute(filme);
    }

    public void delete(Filme filme) {
        new DeleteFilmeAsyncTask(mDaoFilme).execute(filme);
    }


    public Filme getFilmeById(int filmeId) {
        Filme filme = null;
        try {
            filme = new GetFilmeByIdAsyncTask(mDaoFilme).execute(filmeId).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return filme;
    }

    public LiveData<List<Filme>> getmFilmesPopular() {
        return mFilmesPopular;
    }

    public LiveData<List<Filme>> getmFilmesFavoritos() {
        return mFilmesFavoritos;
    }


    public LiveData<List<Filme>> getFilmesByPopular(int page) {

        MutableLiveData<List<Filme>> filmesLiveData = new MutableLiveData<>();
        isLoading = true;

        FilmeService service = RetrofitClientInstance.getRetrofitInstance().create(FilmeService.class);
        Call<Api> call = service.getFilmesByPopular(page);
        call.enqueue(new Callback<Api>() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<Api> call, Response<Api> response) {


                filmesPopular.addAll(response.body().getFilmeList());
                filmesLiveData.setValue(filmesPopular);
                isLoading = false;

                Log.d("Callback", response.message());
            }

            @Override
            public void onFailure(Call<Api> call, Throwable t) {
                Log.d("Callback", "Failure");
            }
        });
        return filmesLiveData;
    }

    public LiveData<List<Filme>> getFilmesByRating(int page) {

        MutableLiveData<List<Filme>> filmesLiveData = new MutableLiveData<>();

        FilmeService service = RetrofitClientInstance.getRetrofitInstance().create(FilmeService.class);
        Call<Api> call = service.getFilmesByRating(page);
        call.enqueue(new Callback<Api>() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<Api> call, Response<Api> response) {


                filmesRating.addAll(response.body().getFilmeList());
                filmesLiveData.setValue(filmesRating);
                isLoading = false;

                Log.d("Callback", response.message());
            }

            @Override
            public void onFailure(Call<Api> call, Throwable t) {
                Log.d("Callback", "Failure");
            }
        });
        return filmesLiveData;
    }

    public List<Review> getReviewList(int filmeId) {

        List<Review> reviews = new ArrayList<>();

        FilmeService serviceReview = RetrofitClientInstance.getRetrofitInstance().create(FilmeService.class);
        Call<ReviewAPI> callReview = serviceReview.getReview(filmeId);
        callReview.enqueue(new Callback<ReviewAPI>() {

            @Override
            public void onResponse(Call<ReviewAPI> call, Response<ReviewAPI> response) {
                reviews.addAll(response.body().getReviewList());
            }

            @Override
            public void onFailure(Call<ReviewAPI> call, Throwable t) {

            }
        });
        return reviews;
    }

    public List<Trailer> getTrailerList(int filmeId) {

        List<Trailer> trailerList = new ArrayList<>();

        FilmeService serviceTrailer = RetrofitClientInstance.getRetrofitInstance().create(FilmeService.class);
        Call<TrailerAPI> callTrailer = serviceTrailer.getTrailer(filmeId);
        callTrailer.enqueue(new Callback<TrailerAPI>() {
            @Override
            public void onResponse(Call<TrailerAPI> call, Response<TrailerAPI> response) {
                trailerList.addAll(response.body().getListaTrailers());
            }

            @Override
            public void onFailure(Call<TrailerAPI> call, Throwable t) {

            }
        });
        return trailerList;

    }

    private static class InsertFilmeAsyncTask extends AsyncTask<Filme, Void, Void> {
        private DaoFilme daoFilme;

        private InsertFilmeAsyncTask(DaoFilme daoFilme) {
            this.daoFilme = daoFilme;
        }

        @Override
        protected Void doInBackground(Filme... filmes) {
            daoFilme.insertFilme(filmes[0]);
            return null;
        }
    }

    private static class DeleteFilmeAsyncTask extends AsyncTask<Filme, Void, Void> {
        private DaoFilme daoFilme;

        private DeleteFilmeAsyncTask(DaoFilme daoFilme) {
            this.daoFilme = daoFilme;
        }

        @Override
        protected Void doInBackground(Filme... filmes) {
            daoFilme.deleteFilme(filmes[0]);
            return null;
        }
    }

    private static class GetFilmeByIdAsyncTask extends AsyncTask<Integer, Void, Filme> {
        private DaoFilme daoFilme;

        private GetFilmeByIdAsyncTask(DaoFilme daoFilme) {
            this.daoFilme = daoFilme;
        }

        @Override
        protected Filme doInBackground(Integer... ints) {
            return daoFilme.getFilmeById(ints[0]);
        }

        @Override
        protected void onPostExecute(Filme filme) {
            super.onPostExecute(filme);

        }
    }

    public static FilmeService getFilmeService() {
        return RetrofitClientInstance.getRetrofitInstance().create(FilmeService.class);
    }

    public LiveData<List<Filme>> getmFilmesRating() {
        return mFilmesRating;
    }

    public Boolean getLoading() {
        return isLoading;
    }
}
