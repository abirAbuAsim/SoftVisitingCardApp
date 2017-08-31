package com.example.android.softvisitingcardapp.gallery;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.android.softvisitingcardapp.R;
import com.example.android.softvisitingcardapp.activity.MyCardsActivity;
import com.example.android.softvisitingcardapp.activity.SelectDesignActivity;
import com.example.android.softvisitingcardapp.activity.SharedCardsActivity;

public class CardGalleryActivity extends AppCompatActivity {
    CardView myCards, sharedCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_gallery);

        // Find the My Cards option
        myCards = (CardView) findViewById(R.id.my_cards_view);
        myCards.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Create CardSent View is clicked on.
            @Override
            public void onClick(View view) {
                Intent menuIntent = new Intent(CardGalleryActivity.this, MyCardsActivity.class);
                startActivity(menuIntent);
            }
        });

        // Find the Shared Cards option
        sharedCards = (CardView) findViewById(R.id.shared_cards_view);
        sharedCards.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Create CardSent View is clicked on.
            @Override
            public void onClick(View view) {
                Intent menuIntent = new Intent(CardGalleryActivity.this, SharedCardsActivity.class);
                startActivity(menuIntent);
            }
        });

    }
}
