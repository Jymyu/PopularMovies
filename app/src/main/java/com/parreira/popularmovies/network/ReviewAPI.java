package com.parreira.popularmovies.network;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.parreira.popularmovies.model.Review;

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
public class ReviewAPI implements Serializable {

    int id;
    int page;
    @JsonProperty("results")
    private List<Review> reviewList;
    int total_pages;
    int total_results;

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewListo(List<Review> reviewList) {
        this.reviewList = reviewList;
    }


}

