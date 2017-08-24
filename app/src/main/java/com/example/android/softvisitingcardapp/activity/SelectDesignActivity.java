package com.example.android.softvisitingcardapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.android.softvisitingcardapp.R;

public class SelectDesignActivity extends AppCompatActivity {
    CardView designOneCard, designTwoCard, designThreeCard, desighFourCard, designFiveCard, designSixCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_design);

        designOneCard = (CardView) findViewById(R.id.template_design_one);

        // Set a click listener on that View
        designOneCard.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the CardSent Gallery CardSent View is clicked on.
            @Override
            public void onClick(View view) {
                Intent designIntent = new Intent(SelectDesignActivity.this, PreviewDesignActivity.class);
                designIntent.putExtra("res", R.drawable.card_background_one);
                startActivity(designIntent);
            }
        });

        designTwoCard = (CardView) findViewById(R.id.template_design_two);

        // Set a click listener on that View
        designTwoCard.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the CardSent Gallery CardSent View is clicked on.
            @Override
            public void onClick(View view) {
                Intent designIntent = new Intent(SelectDesignActivity.this, PreviewDesignActivity.class);
                designIntent.putExtra("res", R.drawable.card_background_two);
                startActivity(designIntent);
            }
        });

        designThreeCard = (CardView) findViewById(R.id.template_design_three);

        // Set a click listener on that View
        designThreeCard.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the CardSent Gallery CardSent View is clicked on.
            @Override
            public void onClick(View view) {
                Intent designIntent = new Intent(SelectDesignActivity.this, PreviewDesignActivity.class);
                designIntent.putExtra("res", R.drawable.card_background_three);
                startActivity(designIntent);
            }
        });

        desighFourCard = (CardView) findViewById(R.id.template_design_four);

        // Set a click listener on that View
        desighFourCard.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the CardSent Gallery CardSent View is clicked on.
            @Override
            public void onClick(View view) {
                Intent designIntent = new Intent(SelectDesignActivity.this, PreviewDesignActivity.class);
                designIntent.putExtra("res", R.drawable.card_background_four);
                startActivity(designIntent);
            }
        });

        designFiveCard = (CardView) findViewById(R.id.template_design_five);

        // Set a click listener on that View
        designFiveCard.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the CardSent Gallery CardSent View is clicked on.
            @Override
            public void onClick(View view) {
                Intent designIntent = new Intent(SelectDesignActivity.this, PreviewDesignActivity.class);
                designIntent.putExtra("res", R.drawable.card_background_five);
                startActivity(designIntent);
            }
        });

        designSixCard = (CardView) findViewById(R.id.template_design_six);

        // Set a click listener on that View
        designSixCard.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the CardSent Gallery CardSent View is clicked on.
            @Override
            public void onClick(View view) {
                Intent designIntent = new Intent(SelectDesignActivity.this, PreviewDesignActivity.class);
                designIntent.putExtra("res", R.drawable.card_background_six);
                startActivity(designIntent);
            }
        });
    }
}
