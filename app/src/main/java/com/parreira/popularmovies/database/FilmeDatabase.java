package com.parreira.popularmovies.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.parreira.popularmovies.activity.Filme;
import com.parreira.popularmovies.activity.Review;
import com.parreira.popularmovies.activity.Trailer;

import java.util.concurrent.Executors;

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

@Database(entities = {Filme.class, Review.class, Trailer.class }, version = 1, exportSchema = true)
public abstract class FilmeDatabase extends RoomDatabase {

    private static FilmeDatabase INSTANCE;
    public abstract DaoFilme daoFilme();
    public abstract DaoReview daoReview();
    public abstract DaoTrailer daoTrailer();

    public static FilmeDatabase getAppDatabase(final Context context){

        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),FilmeDatabase.class,"Filme Database")
                    .allowMainThreadQueries().build();
                    }

          return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}




