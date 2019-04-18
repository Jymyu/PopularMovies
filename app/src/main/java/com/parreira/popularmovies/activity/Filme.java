package com.parreira.popularmovies.activity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import static android.arch.persistence.room.ForeignKey.CASCADE;

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

@Entity()
@JsonIgnoreProperties(ignoreUnknown = true)
public class Filme implements Serializable , Comparable<Filme> {


    String title;

    @PrimaryKey

    Integer id;

    float popularity;
    @JsonProperty("poster_path")
    String image;
    @JsonProperty("overview")
    String synopsis;
    @JsonProperty("vote_average")
    float userRating;
    @JsonProperty("release_date")
    String releaseDate;

    @ForeignKey(entity = Review.class, parentColumns = "idReview",childColumns = "review", onDelete = CASCADE)
    int review;
    @ForeignKey(entity = Trailer.class, parentColumns = "idTrailer",childColumns = "trailer", onDelete = CASCADE)
    int trailer;

    public Filme() {
    }

    public Filme(String title, Integer id, float popularity, String image, String synopsis, float userRating, String releaseDate, int review, int trailer) {
        this.title = title;
        this.id = id;
        this.popularity = popularity;
        this.image = image;
        this.synopsis = synopsis;
        this.userRating = userRating;
        this.releaseDate = releaseDate;
        this.review = review;
        this.trailer = trailer;
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

        this.image = image;
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

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public int getTrailer() {
        return trailer;
    }

    public void setTrailer(int trailer) {
        this.trailer = trailer;
    }

    @Override
    public int compareTo(Filme d) {
        return 0;
    }
}
