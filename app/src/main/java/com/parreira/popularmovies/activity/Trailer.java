package com.parreira.popularmovies.activity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

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

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Trailer implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int idTrailer;
    String id;
    String key;
    String name;
    String site;

    public Trailer() {
    }

    public Trailer(int idTrailer, String id, String key, String name, String site) {
        this.idTrailer = idTrailer;
        this.id = id;
        this.key = key;
        this.name = name;
        this.site = site;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getIdTrailer() {
        return idTrailer;
    }

    public void setIdTrailer(int idTrailer) {
        this.idTrailer = idTrailer;
    }
}
