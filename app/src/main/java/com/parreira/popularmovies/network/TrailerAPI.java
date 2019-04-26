package com.parreira.popularmovies.network;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.parreira.popularmovies.model.Trailer;

import java.io.Serializable;
import java.util.List;

/**
 * Created by João Parreira on 4/15/2019.
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
public class TrailerAPI implements Serializable {

    int id;

    @JsonProperty("results")
    private List<Trailer> listaTrailers;

    public TrailerAPI() {
    }

    public TrailerAPI(int id, List<Trailer> listaTrailers) {
        this.id = id;
        this.listaTrailers = listaTrailers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Trailer> getListaTrailers() {
        return listaTrailers;
    }

    public void setListaTrailers(List<Trailer> listaTrailers) {
        this.listaTrailers = listaTrailers;
    }
}
