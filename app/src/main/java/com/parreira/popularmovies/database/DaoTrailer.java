package com.parreira.popularmovies.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.parreira.popularmovies.activity.Review;
import com.parreira.popularmovies.activity.Trailer;

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
public interface DaoTrailer {
    @Insert
    void insertTrailer(Trailer trailer);

    @Insert
    void insertAll(List<Trailer> listaTrailer);

    @Query("SELECT * FROM Trailer WHERE idTrailer = :idTrailer")
    Trailer getTrailerById(int idTrailer);

    @Query("SELECT * FROM Trailer WHERE id = :id")
    Trailer getTrailerByFilmeID(String id);


    @Query("SELECT * FROM Trailer")
    List<Trailer> getTrailerAll();

    @Update
    void updateTrailer(Trailer trailer);

    @Delete
    void deleteTrailer(Trailer trailer);
    @Delete
    void deleteTrailerAll(List<Trailer> trailer);
}
