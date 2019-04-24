package com.parreira.popularmovies.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.parreira.popularmovies.activity.Filme;
import com.parreira.popularmovies.activity.Review;

import java.util.List;

/**
 * Created by João Parreira on 4/16/2019.
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
@Dao
public interface DaoFilme {


    @Insert
    void insertFilme(Filme filme);


    @Query("SELECT * FROM Filme WHERE id = :idFilme")
    Filme getFilmeById(int idFilme);


    @Query("SELECT * FROM Filme")
    LiveData<List<Filme>> getFilmeAll();



    @Delete
    void deleteFilme(Filme filme);

}
