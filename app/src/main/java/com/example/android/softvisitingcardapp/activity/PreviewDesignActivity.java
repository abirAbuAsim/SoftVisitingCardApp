package com.example.android.softvisitingcardapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.android.softvisitingcardapp.R;

public class PreviewDesignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_design);
        Bundle extras = getIntent().getExtras();
        if (extras == null)
        {
            return;
        }
        final int res = extras.getInt("res");

        ImageView templateImage = (ImageView) findViewById(R.id.card_background_image_view);
        templateImage.setImageResource(res);

        Button selectDesignButton = (Button) findViewById(R.id.select_design_button);
        selectDesignButton.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Create CardSent View is clicked on.
            @Override
            public void onClick(View view) {
                Intent designIntent = new Intent(PreviewDesignActivity.this, CreateActivity.class);
                designIntent.putExtra("res", res);
                startActivity(designIntent);
            }
        });
        Button seeMoreDesignButton = (Button) findViewById(R.id.see_more_design);
        seeMoreDesignButton.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Create CardSent View is clicked on.
            @Override
            public void onClick(View view) {
                Intent menuIntent = new Intent(PreviewDesignActivity.this, SelectDesignActivity.class);
                startActivity(menuIntent);
            }
        });


        // For debugging purpose: go to test page
        Button goToTestPageButton = (Button) findViewById(R.id.go_to_test_page);
        goToTestPageButton.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Create CardSent View is clicked on.
            @Override
            public void onClick(View view) {
                Intent menuIntent = new Intent(PreviewDesignActivity.this, TestActivity.class);
                startActivity(menuIntent);
            }
        });
    }
}
