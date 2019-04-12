package com.parreira.popularmovies.network;

import com.parreira.popularmovies.activity.Api;
import com.parreira.popularmovies.activity.Filme;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.Call;

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
public interface FilmeService {

    public static final String API_KEY = "3b86f8b7e600322952e3078f122d4002";


    @GET ("popular?api_key=" + API_KEY)
    Call<Api> getFilmes();
}
