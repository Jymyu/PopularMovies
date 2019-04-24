package com.parreira.popularmovies.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.parreira.popularmovies.activity.Filme;
import com.parreira.popularmovies.database.FilmeDatabase;

import com.parreira.popularmovies.network.RetrofitClientInstance;
import com.parreira.popularmovies.repository.RepositoryFilmes;

import java.util.List;

/**
 * Created by João Parreira on 4/17/2019.
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
public class FilmeViewModel extends AndroidViewModel {

    private LiveData<List<Filme>> filmes;
    private RepositoryFilmes repository;


    public FilmeViewModel(@NonNull Application application) {
        super(application);
        repository = new RepositoryFilmes(application);
        filmes = repository.getmFilmes();

    }


    public void getMorePopularMovies() {
        repository.adicionaFilmesPopular();
        return;
    }


    public LiveData<List<Filme>> getFilmes() {
        return filmes;
    }


}
