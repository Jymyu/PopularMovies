package com.parreira.popularmovies.network;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by João Parreira on 4/12/2019.
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
public class RetrofitClientInstance {

    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance () {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
