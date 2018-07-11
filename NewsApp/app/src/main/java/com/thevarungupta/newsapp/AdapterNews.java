package com.thevarungupta.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterNews extends BaseAdapter {

    ArrayList<News> mList;
    Context context;

    public AdapterNews(Context context, ArrayList<News> list) {
        this.context = context;
        this.mList = list;
    }

    public void setData(ArrayList<News> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.row_news_adapter, viewGroup, false);

        ImageView imageView = view.findViewById(R.id.image_view);
        TextView titleTextView = view.findViewById(R.id.text_title);
        TextView authorTextView = view.findViewById(R.id.text_author);

        News news = mList.get(i);
        titleTextView.setText(news.getTitle());
        authorTextView.setText(news.getAuthor());

        Picasso.with(context).load(news.getUrl()).placeholder(R.drawable.image).into(imageView);
        return view;
    }
}
