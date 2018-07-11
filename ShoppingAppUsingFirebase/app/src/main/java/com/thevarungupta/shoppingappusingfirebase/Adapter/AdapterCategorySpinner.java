package com.thevarungupta.shoppingappusingfirebase.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.thevarungupta.shoppingappusingfirebase.Model.Category;
import com.thevarungupta.shoppingappusingfirebase.R;

import java.util.ArrayList;

public class AdapterCategorySpinner extends BaseAdapter {

    Context context;
    ArrayList<Category> mList;

    public AdapterCategorySpinner(Context context, ArrayList<Category> list) {
        this.context = context;
        this.mList = list;
    }

    public void setData(ArrayList<Category> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Category getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.row_spinner_item, viewGroup, false);
        }
        Category category = mList.get(i);

        TextView textViewCategoryName = view.findViewById(R.id.text_view_spinner);
        textViewCategoryName.setText(category.getCatName());
        return view;
    }
}
