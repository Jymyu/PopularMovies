package com.parreira.popularmovies.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.parreira.popularmovies.activity.Review;

import java.util.List;

import retrofit2.http.DELETE;

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
public interface DaoReview {

    @Insert
    void insertReview(Review review);

    @Insert
    void insertAll(List<Review> listReview);

    @Query("SELECT * FROM Review WHERE idReview = :idReview")
    Review getReviewById(int idReview);

    @Query("SELECT * FROM Review WHERE id = :id")
    Review getReviewByFilmeID(String id);


    @Query("SELECT * FROM Review")
    List<Review> getReviewAll();

    @Update
    void updateReview(Review review);

    @Delete
    void deleteReview(Review review);
    @Delete
    void deleteReviewAll(List<Review> listaReview);
}
