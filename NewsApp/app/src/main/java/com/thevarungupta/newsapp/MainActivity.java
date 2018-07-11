package com.thevarungupta.newsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final String URL = "https://newsapi.org/v2/top-headlines?country=us&apiKey=a1c63f6fa6a645088799c895f54f5ed6";

    ProgressBar progressBar;
    ListView listView;

    AdapterNews adapterNews;
    ArrayList<News> mList = new ArrayList<>();

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        progressBar = findViewById(R.id.progress_bar);
        listView = findViewById(R.id.list_view);

        adapterNews = new AdapterNews(this, mList);
        listView.setAdapter(adapterNews);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                News news = (News) adapterNews.getItem(i);
                Intent intent = new Intent(MainActivity.this, NewsDetailActivity.class);
                intent.putExtra("NEWS", news);
                startActivity(intent);
            }
        });

        requestQueue = Volley.newRequestQueue(this);
        fetchData();
    }

    public void fetchData() {
        String url = "https://newsapi.org/v2/top-headlines?country=us&apiKey=a1c63f6fa6a645088799c895f54f5ed6";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressBar.setVisibility(View.GONE);
                        JSONObject jsonObject = response;
                        try {
                            JSONArray jsonArray = jsonObject.getJSONArray("articles");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                String author = jsonObject1.getString("author");
                                String title = jsonObject1.getString("title");
                                String description = jsonObject1.getString("description");
                                String url = jsonObject1.getString("urlToImage");
                                String date = jsonObject1.getString("publishedAt");

                                News news = new News();
                                news.setAuthor(author);
                                news.setTitle(title);
                                news.setDescription(description);
                                news.setUrl(url);

                                mList.add(news);
                            }
                            adapterNews.setData(mList);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(request);
    }
}
