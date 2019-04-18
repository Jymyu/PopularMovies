package com.parreira.popularmovies.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.parreira.popularmovies.activity.Filme;
import com.parreira.popularmovies.activity.MainActivity;
import com.parreira.popularmovies.activity.MovieActivity;
import com.parreira.popularmovies.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.parreira.popularmovies.activity.MovieActivity.KEY_IS_FAVOURITE;

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
public class MyMoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String TAG = MyMoviesAdapter.class.getSimpleName();
    private static Context mContext;
    private List<Filme> mArray;
    private static boolean mIsFavouriteOn;

    public MyMoviesAdapter(Context context, List<Filme> array, boolean isFavouriteOn) {
        this.mContext = context;
        this.mArray = array;
        this.mIsFavouriteOn = isFavouriteOn;
    }

    public MyMoviesAdapter(Context context) {
        this.mContext = context;
        this.mArray = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View contextView = mInflater.inflate(R.layout.recycler_view_image, null);

        MyViewHolder vh = new MyViewHolder(contextView) {
            @Override
            public void onClick(View v) {

            }
        };

        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

        ImageView img = viewHolder.itemView.findViewById(R.id.img_movie);
        String url = "https://image.tmdb.org/t/p/w185/" + mArray.get(i).getImage();
        Picasso.get().load(url).into(img);

        viewHolder.itemView.setTag(mArray.get(i));


    }

    @Override
    public int getItemCount() {
        return mArray.size();
    }

    public static abstract class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView img;
        CardView cvMovie;


        public MyViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_movie);
            cvMovie = itemView.findViewById(R.id.cv_movie_card);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Filme filme = (Filme) v.getTag();
                    Log.d("Debug", "está a clicar " + filme.getTitle());

                    Intent intent = new Intent(MyMoviesAdapter.mContext, MovieActivity.class);
                    intent.putExtra(MovieActivity.KEY_FILME, filme);
                    intent.putExtra(KEY_IS_FAVOURITE, mIsFavouriteOn);
                    MyMoviesAdapter.mContext.startActivity(intent);
                }
            });

        }

    }

    public void setFavourite(boolean favourite) {
        mIsFavouriteOn = favourite;
    }
    public void setItens(List<Filme> itens) {
        mArray.clear();
        mArray.addAll(itens);
    }
}
