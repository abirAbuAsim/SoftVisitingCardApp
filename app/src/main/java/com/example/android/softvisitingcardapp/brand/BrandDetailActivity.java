package com.example.android.softvisitingcardapp.brand;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.softvisitingcardapp.R;

public class BrandDetailActivity extends AppCompatActivity {
    private TextView brandNameTextView, brandDetailsTextView;
    private Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_detail);
        setTitle("Details");

        Bundle extras = getIntent().getExtras();
        if (extras == null)
        {
            return;
        }
        final int brandId = extras.getInt("brandId");
        final String brandName = extras.getString("brandName");
        final String brandDetails = extras.getString("brandDetails");

        brandNameTextView = findViewById(R.id.brand_name_text_view);
        brandDetailsTextView = findViewById(R.id.brand_details_text_view);

        editButton = findViewById(R.id.edit_button);

        brandNameTextView.setText(brandName);
        brandDetailsTextView.setText(brandDetails);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BrandDetailActivity.this, BrandEditActivity.class);
                intent.putExtra("brandId", brandId);
                intent.putExtra("brandName", brandName);
                intent.putExtra("brandDetails", brandDetails);
                startActivity(intent);
            }
        });
    }

}
