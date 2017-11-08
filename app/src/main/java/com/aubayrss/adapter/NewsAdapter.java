package com.aubayrss.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.aubayrss.R;
import com.aubayrss.activity.NewItemDetailsActivity;
import com.aubayrss.model.Article;
import com.aubayrss.utils.DateUtils;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Lotfi Fetteni on 08/11/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<Article> newsArticles;

    public NewsAdapter(List<Article> newsArticles) {
        this.newsArticles = newsArticles;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_news, parent, false);
        NewsViewHolder mainNewsViewHolder = new NewsViewHolder(view);
        return mainNewsViewHolder;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, final int position) {
        Article newsArticle = newsArticles.get(position);
        Glide.with(holder.cardImageView.getContext()).load(newsArticle.getUrlToImage())
             .centerCrop() .into(holder.cardImageView);

        holder.cardTitleTextView.setText(newsArticle.getTitle());
        holder.cardTimeTextView.setText(DateUtils.formatNewsApiDate(newsArticle.getPublishedAt()));
        holder.cardContentTextView.setText(newsArticle.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("index", String.valueOf(position));
               NewItemDetailsActivity.launch(v.getContext(), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsArticles.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView cardImageView;
        TextView cardTitleTextView;
        TextView cardTimeTextView;
        TextView cardContentTextView;

        public NewsViewHolder(View itemView) {
            super(itemView);
            cardImageView = (ImageView) itemView.findViewById(R.id.card_newshome_image);
            cardTitleTextView = (TextView) itemView.findViewById(R.id.card_newshome_title);
            cardTimeTextView = (TextView) itemView.findViewById(R.id.card_newshome_time);
            cardContentTextView = (TextView) itemView.findViewById(R.id.card_newshome_content);
        }
    }

}
