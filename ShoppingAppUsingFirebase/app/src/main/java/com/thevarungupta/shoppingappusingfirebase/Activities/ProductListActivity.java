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
import com.thevarungupta.shoppingappusingfirebase.Adapter.AdapterProduct;
import com.thevarungupta.shoppingappusingfirebase.Model.Category;
import com.thevarungupta.shoppingappusingfirebase.Model.Product;
import com.thevarungupta.shoppingappusingfirebase.R;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {

    String catId;
    Button buttonProductAdd;
    ArrayList<Product> list = new ArrayList<>();

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    AdapterProduct adapterProduct;


    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        catId = "-LGtA4v0LeQANVWsp_Cu";
        databaseReference = FirebaseDatabase.getInstance().getReference("product");

        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        adapterProduct = new AdapterProduct(this, list);
        recyclerView.setAdapter(adapterProduct);
        recyclerView.setLayoutManager(layoutManager);
        getProductByCat(catId);

        buttonProductAdd = findViewById(R.id.button_product_add);
        buttonProductAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductListActivity.this, ProductAddActivity.class));
            }
        });
    }

    private void getProductByCat(String catId){
        databaseReference.child("catId").child(catId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot productData : dataSnapshot.getChildren()) {
                    Product product = productData.getValue(Product.class);
                    list.add(product);
                    adapterProduct.setData(list);
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
