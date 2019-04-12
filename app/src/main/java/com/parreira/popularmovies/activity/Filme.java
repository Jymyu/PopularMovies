package com.parreira.popularmovies.activity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by João Parreira on 4/11/2019.
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


@JsonIgnoreProperties(ignoreUnknown = true)
public class Filme implements Serializable {

    String title;

    Integer id;
    @JsonProperty("poster_path")
    String image;
    @JsonProperty("overview")
    String synopsis;
    @JsonProperty("vote_average")
    float userRating;
    @JsonProperty("release_date")
    String releaseDate;

    public Filme() {
    }

    public Filme(String title, String image, String synopsis, float userRating, String releaseDate, Integer id) {
        this.title = title;
        this.image = image;
        this.synopsis = synopsis;
        this.userRating = userRating;
        this.releaseDate = releaseDate;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        String url = "https://image.tmdb.org/t/p/w185/";

        this.image = url + image;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public float getUserRating() {
        return userRating;
    }

    public void setUserRating(float userRating) {
        this.userRating = userRating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
