package com.thevarungupta.shoppingappusingfirebase.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thevarungupta.shoppingappusingfirebase.Adapter.AdapterCategorySpinner;
import com.thevarungupta.shoppingappusingfirebase.Model.Category;
import com.thevarungupta.shoppingappusingfirebase.Model.Product;
import com.thevarungupta.shoppingappusingfirebase.R;

import java.util.ArrayList;

public class ProductAddActivity extends AppCompatActivity {

    Spinner spinner;
    AdapterCategorySpinner adapterCategorySpinner;
    ArrayList<Category> list = new ArrayList<>();

    EditText editProductName, editProductMrp, editProductPrice, editProductDescription;
    Button buttonSave;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReferenceProduct;
    DatabaseReference databaseReferenceCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReferenceProduct = firebaseDatabase.getReference("products");
        databaseReferenceCategory = firebaseDatabase.getReference("category");

        spinner = findViewById(R.id.spinner);
        adapterCategorySpinner = new AdapterCategorySpinner(this, list);
        spinner.setAdapter(adapterCategorySpinner);
        getAllCategory();

        editProductName = findViewById(R.id.edit_text_product_name);
        editProductMrp = findViewById(R.id.edit_text_product_mrp);
        editProductPrice = findViewById(R.id.edit_text_product_price);
        editProductDescription = findViewById(R.id.edit_text_product_description);
        buttonSave = findViewById(R.id.button_product_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveProduct();
            }
        });
    }

    private void getAllCategory() {
        databaseReferenceCategory.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot categoryData : dataSnapshot.getChildren()) {
                    Category category = categoryData.getValue(Category.class);
                    list.add(category);
                }
                adapterCategorySpinner.setData(list);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void saveProduct() {
        String name = editProductName.getText().toString().trim();
        int mrp = Integer.parseInt(editProductMrp.getText().toString().trim());
        int price = Integer.parseInt(editProductPrice.getText().toString().trim());
        String desc = editProductDescription.getText().toString().trim();
        Category category = (Category) spinner.getSelectedItem();
        String catId= category.getCatId();

        String pid = databaseReferenceProduct.push().getKey();
        Product product = new Product(pid, name, mrp, price, desc, catId);

        databaseReferenceProduct.child(pid).setValue(product);
        Toast.makeText(this, "Product Added Successfully", Toast.LENGTH_SHORT).show();
        clearForm();
    }

    private void clearForm() {
        editProductName.setText("");
        editProductMrp.setText("");
        editProductPrice.setText("");
        editProductDescription.setText("");
    }
}
