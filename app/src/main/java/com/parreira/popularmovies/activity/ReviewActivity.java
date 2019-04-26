package com.parreira.popularmovies.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import com.parreira.popularmovies.R;
import com.parreira.popularmovies.adapter.ReviewAdapter;
import com.parreira.popularmovies.model.Review;

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
public class ReviewActivity extends AppCompatActivity {

    public static final String KEY_LIST_REVIEW = "List View Key";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        List<Review> reviews = (List<Review>) extras.get(KEY_LIST_REVIEW);

        ReviewAdapter adapter = new ReviewAdapter(this, reviews);
        ListView listView = (ListView) findViewById(R.id.lv_reviews);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
