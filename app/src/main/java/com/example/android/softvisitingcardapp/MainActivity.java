package com.example.android.softvisitingcardapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the Create menu card
        CardView createCardView = (CardView) findViewById(R.id.create_card_view);

        // Set a click listener on that View
        createCardView.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Create Card View is clicked on.
            @Override
            public void onClick(View view) {
                Intent menuIntent = new Intent(MainActivity.this, SelectDesignActivity.class);
                startActivity(menuIntent);
            }
        });

        // Find the Card Gallery menu card
        CardView cardGalleryCardView = (CardView) findViewById(R.id.card_gallery_card_view);

        // Set a click listener on that View
        cardGalleryCardView.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Card Gallery Card View is clicked on.
            @Override
            public void onClick(View view) {
                Intent menuIntent = new Intent(MainActivity.this, CardGalleryActivity.class);
                startActivity(menuIntent);
            }
        });

        // Find the Linked People Card view
        CardView linkedPeopleCardView = (CardView) findViewById(R.id.link_card_view);

        // Set a click listener on that View
        linkedPeopleCardView.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Linked People View is clicked on.
            @Override
            public void onClick(View view) {
                Intent linkedPeopleIntent = new Intent(MainActivity.this, LinkedPeopleActivity.class);
                startActivity(linkedPeopleIntent);
            }
        });

        // Find the About us menu card
        CardView aboutUsCardView = (CardView) findViewById(R.id.about_us_card_view);

        // Set a click listener on that View
        aboutUsCardView.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the About us View is clicked on.
            @Override
            public void onClick(View view) {
                Intent menuIntent = new Intent(MainActivity.this, AboutUsActivity.class);
                startActivity(menuIntent);
            }
        });
    }
}
