package com.example.android.softvisitingcardapp.gallery;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.softvisitingcardapp.R;

public class CardGalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_gallery);

        Bundle extras = getIntent().getExtras();
        if (extras == null)
        {
            return;
        }
        final String userEmail = extras.getString("userEmail");

        Bundle emailBundle = new Bundle();
        emailBundle.putString("userEmail", userEmail);

        Fragment fragment = new GalleryFragment();
        fragment.setArguments(emailBundle);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,fragment);
        ft.commit();
    }
}
