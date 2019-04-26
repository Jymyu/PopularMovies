package com.parreira.popularmovies.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.parreira.popularmovies.model.Filme;
import com.parreira.popularmovies.repository.RepositoryFilmes;

import java.util.List;

/**
 * Created by João Parreira on 4/26/2019.
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
public class FilmeRatingViewModel extends AndroidViewModel {

        private LiveData<List<Filme>> filmes;
        private RepositoryFilmes repository;


        public FilmeRatingViewModel(@NonNull Application application) {
            super(application);
            repository = new RepositoryFilmes(application);
            filmes = repository.getmFilmesRating();

        }


        public void getMoreRatingMovies() {
            repository.adicionaFilmesRating();

        }


        public LiveData<List<Filme>> getFilmes() {
            return filmes;
        }


    }



