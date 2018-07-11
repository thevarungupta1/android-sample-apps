package com.thevarungupta.newsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsDetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView titleText, authorText, descriptionText;
    Button shareButton;

    News news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        Intent intent = getIntent();
        news = (News) intent.getSerializableExtra("NEWS");

        imageView = findViewById(R.id.image_view);
        titleText = findViewById(R.id.text_title);
        authorText = findViewById(R.id.text_author);
        descriptionText = findViewById(R.id.text_description);
        shareButton = findViewById(R.id.button_share);

        Picasso.with(this).load(news.getUrl()).placeholder(R.drawable.image).into(imageView);
        titleText.setText(news.getTitle());
        authorText.setText(news.getAuthor());
        descriptionText.setText(news.getDescription());

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share();
            }
        });
    }

    private void share() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, news.getTitle());
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}
