package com.example.android.softvisitingcardapp.category;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.softvisitingcardapp.R;
import com.example.android.softvisitingcardapp.supplier.SupplierDetailActivity;
import com.example.android.softvisitingcardapp.supplier.SupplierEditActivity;

public class CategoryDetailActivity extends AppCompatActivity {
    private TextView categoryNameTextView, categoryDetailsTextView;

    private Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);

        Bundle extras = getIntent().getExtras();
        if (extras == null)
        {
            return;
        }
        final int categoryId = extras.getInt("categoryId");
        final String categoryName = extras.getString("categoryName");
        final String categoryDetails = extras.getString("categoryDetails");

        initializeViews();

        setDataInViews(categoryName, categoryDetails);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryDetailActivity.this,
                        CategoryEditActivity.class);

                intent.putExtra("categoryId", categoryId);
                intent.putExtra("categoryName", categoryName);
                intent.putExtra("categoryDetails", categoryDetails);

                startActivity(intent);
            }
        });
    }

    private void initializeViews() {
        editButton = findViewById(R.id.edit_button);

        categoryNameTextView = findViewById(R.id.category_name_text_view);
        categoryDetailsTextView = findViewById(R.id.category_details_text_view);
    }

    private void setDataInViews(String categoryName, String categoryDetails) {

        categoryNameTextView.setText(categoryName);
        categoryDetailsTextView.setText(categoryDetails);

    }
}
