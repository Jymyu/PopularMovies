package com.parreira.popularmovies.activity;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.parreira.popularmovies.database.FilmeDatabase;

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
public class MainViewModel extends AndroidViewModel {

    private LiveData<List<Filme>> filmes;

    public MainViewModel(@NonNull Application application) {
        super(application);
        FilmeDatabase database = FilmeDatabase.getAppDatabase(this.getApplication());
        filmes = database.daoFilme().getFilmeAll();
    }

    public LiveData<List<Filme>> getFilmes() {
        return filmes;
    }

    public void setFilmes(LiveData<List<Filme>> filmes) {
        this.filmes = filmes;
    }
}
