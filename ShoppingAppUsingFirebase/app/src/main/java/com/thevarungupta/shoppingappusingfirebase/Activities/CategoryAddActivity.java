package com.thevarungupta.shoppingappusingfirebase.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.thevarungupta.shoppingappusingfirebase.Model.Category;
import com.thevarungupta.shoppingappusingfirebase.R;

public class CategoryAddActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    EditText categoryEditText;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_add);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("category");

        categoryEditText = findViewById(R.id.edit_text_category_add);
        saveButton = findViewById(R.id.button_category_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveCategory();
            }
        });

    }

    private void saveCategory() {
        String catName = categoryEditText.getText().toString().trim();
        String catId = databaseReference.push().getKey();

        Category category = new Category(catId, catName);
        databaseReference.child(catId).setValue(category);
        Toast.makeText(this, "New Category added", Toast.LENGTH_SHORT).show();
        categoryEditText.setText("");

    }
}
