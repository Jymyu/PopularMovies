package com.parreira.popularmovies.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parreira.popularmovies.R;
import com.parreira.popularmovies.model.Filme;
import com.parreira.popularmovies.model.Review;
import com.parreira.popularmovies.model.Trailer;
import com.parreira.popularmovies.repository.RepositoryFilmes;
import com.parreira.popularmovies.viewModel.FilmeFavoritoViewModel;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.parreira.popularmovies.activity.ReviewActivity.KEY_LIST_REVIEW;

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
    public static final String KEY_IS_FAVOURITE = "KEY_IS_FAVOURITE";
    Filme filme;
    boolean isFavouriteOn;

    List<Review> reviewList = new ArrayList<Review>();
    List<Trailer> trailerList = new ArrayList<Trailer>();

    RepositoryFilmes repository = new RepositoryFilmes();

    MenuItem mDynamicMenuItemFavourite;
    MenuItem mDynamicMenuItemNotFavourite;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        setContentView(R.layout.activity_movie);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FilmeFavoritoViewModel viewModelFavourites = ViewModelProviders.of(this).get(FilmeFavoritoViewModel.class);
        filme = (Filme) getIntent().getExtras().get(KEY_FILME);
        isFavouriteOn = (boolean) getIntent().getExtras().get(KEY_IS_FAVOURITE);


        TextView tvTitle = (TextView) findViewById(R.id.tv_title_name2);
        TextView tvDate = (TextView) findViewById(R.id.tv_date2);
        TextView tvSynopsys = (TextView) findViewById(R.id.tv_synopsis2);
        TextView tvrating = (TextView) findViewById(R.id.tv_user_rating2);
        ImageView img = (ImageView) findViewById(R.id.img_movie2);
        Button btnTrailer = (Button) findViewById(R.id.btn_trailer);
        Button btnReview = (Button) findViewById(R.id.btn_review);

        tvSynopsys.setText(filme.getSynopsis());
        tvTitle.setText(filme.getTitle());
        tvDate.setText(filme.getReleaseDate());
        tvrating.setText(Float.toString(filme.getUserRating()) + " / 10");
        String url = "https://image.tmdb.org/t/p/w185/" + filme.getImage();
        Picasso.get().load(url).into(img);


        // get reviews list
        reviewList = repository.getReviewList(filme.getId());

        // get trailer list
        trailerList = repository.getTrailerList(filme.getId());

        btnReview.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reviewList.isEmpty()) {
                    Toast.makeText(MovieActivity.this, "There is no review", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent3 = new Intent(MovieActivity.this, ReviewActivity.class);
                    intent3.putExtra(KEY_LIST_REVIEW, (Serializable) reviewList);
                    startActivity(intent3);
                }

            }
        });

        btnTrailer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (trailerList.isEmpty()) {
                    Toast.makeText(MovieActivity.this, "No trailers to present", Toast.LENGTH_SHORT).show();
                } else {
                    watchYoutubeVideo(MovieActivity.this, trailerList.get(0).getKey());
                }
            }
        });
    }

    // Favourite and not Favourite selection
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FilmeFavoritoViewModel viewModelFavourites = ViewModelProviders.of(this).get(FilmeFavoritoViewModel.class);

        switch (item.getItemId()) {

            case R.id.menu_item_favourite2:
                mDynamicMenuItemFavourite.setVisible(false);
                mDynamicMenuItemNotFavourite.setVisible(true);
                viewModelFavourites.deleteFavorito(filme);


                break;
            case R.id.menu_item_not_favourite2:
                mDynamicMenuItemFavourite.setVisible(true);
                mDynamicMenuItemNotFavourite.setVisible(false);
                viewModelFavourites.insertFavorito(filme);

                break;


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        FilmeFavoritoViewModel viewModelFavourites = ViewModelProviders.of(this).get(FilmeFavoritoViewModel.class);
        getMenuInflater().inflate(R.menu.menu_movie, menu);
        mDynamicMenuItemFavourite = menu.findItem(R.id.menu_item_favourite2);
        mDynamicMenuItemNotFavourite = menu.findItem(R.id.menu_item_not_favourite2);

        if (viewModelFavourites.getFilmeById(filme.getId()) != null) {
            mDynamicMenuItemFavourite.setVisible(true);
            mDynamicMenuItemNotFavourite.setVisible(false);
        }
        return true;
    }

    public static void watchYoutubeVideo(Context context, String id) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }
}
