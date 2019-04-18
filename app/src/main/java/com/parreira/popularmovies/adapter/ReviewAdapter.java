package com.parreira.popularmovies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parreira.popularmovies.R;
import com.parreira.popularmovies.activity.Review;

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
public class ReviewAdapter extends ArrayAdapter{


    public ReviewAdapter(Context context, List<Review> reviewList) {
        super(context,0, reviewList);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Review review = (Review) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.review_list_item, parent, false);
        }

        TextView tvAuthor = (TextView) convertView.findViewById(R.id.review_item_author);
        TextView tvReviewContent = (TextView) convertView.findViewById(R.id.review_item_content);

        tvAuthor.setText(review.getAuthor());
        tvReviewContent.setText(review.getContent());

        return convertView;
    }
}
