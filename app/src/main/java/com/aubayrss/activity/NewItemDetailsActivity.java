package com.aubayrss.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aubayrss.R;
import com.aubayrss.model.NewsStore;
import com.aubayrss.utils.DateUtils;
import com.bumptech.glide.Glide;

/**
 * Created by Lotfi Fetteni on 08/11/2017.
 */

public class NewItemDetailsActivity extends AppCompatActivity{

    ImageView cardImageView;
    TextView cardTitleTextView;
    TextView cardTimeTextView;
    TextView cardContentTextView;
    Button visitewebsite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final int index = getIntent().getIntExtra(KEY_INDEX, -1);
        if (index != -1) {
            updateNewsDetails(index);
        } else {
            Toast.makeText(NewItemDetailsActivity.this, "Sorry incorrect index passed", Toast.LENGTH_SHORT).show();
        }
        getSupportActionBar().setTitle(NewsStore.getNewsArticles().get(index).getTitle());
        visitewebsite=(Button)findViewById(R.id.showsite);
        visitewebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("index",NewsStore.getNewsArticles().get(index).getUrl() );
                NewsWebViewActivity.launch(view.getContext(), index);
            }
        });
    }

    private void updateNewsDetails(int index) {

       //initialize screen labels

        cardImageView = (ImageView)findViewById(R.id.card_news_image);
        cardTitleTextView = (TextView)findViewById(R.id.card_news_title);
        cardTimeTextView = (TextView) findViewById(R.id.card_news_time);
        cardContentTextView = (TextView)findViewById(R.id.card_news_content);

        //match selected item with suitable data
        Glide.with(cardImageView.getContext()).load(NewsStore.getNewsArticles().get(index).getUrlToImage())
                .centerCrop()
                .into(cardImageView);
        cardImageView.setImageURI(Uri.parse(NewsStore.getNewsArticles().get(index).getUrlToImage()));
        cardTitleTextView.setText(NewsStore.getNewsArticles().get(index).getTitle());
        cardTimeTextView.setText(DateUtils.formatNewsApiDate(NewsStore.getNewsArticles().get(index).getPublishedAt()));
        cardContentTextView.setText(NewsStore.getNewsArticles().get(index).getDescription());

    }


    private static final String KEY_INDEX = "news_index";
    public static void launch(Context context, int index) {
        Intent intent = new Intent(context, NewItemDetailsActivity.class);
        intent.putExtra(KEY_INDEX, index);
        context.startActivity(intent);
    }
}
