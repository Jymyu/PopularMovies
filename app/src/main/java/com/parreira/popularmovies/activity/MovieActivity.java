package com.parreira.popularmovies.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.parreira.popularmovies.R;
import com.squareup.picasso.Picasso;

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
public class MovieActivity extends AppCompatActivity {

    public static final String KEY_FILME = "filme";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movie);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Filme filme = (Filme) getIntent().getExtras().get(KEY_FILME);

        TextView tvTitle = (TextView) findViewById(R.id.tv_title_name2);
        TextView tvDate = (TextView) findViewById(R.id.tv_date2);
        TextView tvSynopsys = (TextView) findViewById(R.id.tv_synopsis2);
        RatingBar rbRating = (RatingBar) findViewById(R.id.rating_bar);
        ImageView img = (ImageView) findViewById(R.id.img_movie2);

        tvSynopsys.setText(filme.getSynopsis());
        tvTitle.setText(filme.getTitle());
        tvDate.setText(filme.getReleaseDate());
        rbRating.setNumStars(10);
        rbRating.setMax(10);
        rbRating.setRating(filme.getUserRating());
        Picasso.get().load(filme.getImage()).into(img);

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
