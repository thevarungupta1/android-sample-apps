package com.thevarungupta.shoppingappusingfirebase.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thevarungupta.shoppingappusingfirebase.Adapter.AdapterCategory;
import com.thevarungupta.shoppingappusingfirebase.Model.Category;
import com.thevarungupta.shoppingappusingfirebase.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button categoryAddButton;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    AdapterCategory adapterCategory;
    ArrayList<Category> list = new ArrayList<>();

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference = FirebaseDatabase.getInstance().getReference("category");

        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        adapterCategory = new AdapterCategory(this, list);
        recyclerView.setAdapter(adapterCategory);
        recyclerView.setLayoutManager(layoutManager);
        getAllCategory();

        categoryAddButton = findViewById(R.id.button_category_add);
        categoryAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CategoryAddActivity.class));
            }
        });
    }

    private void getAllCategory() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot categorySnapShot : dataSnapshot.getChildren()) {
                    Category category = categorySnapShot.getValue(Category.class);
                    list.add(category);
                    adapterCategory.setData(list);
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
