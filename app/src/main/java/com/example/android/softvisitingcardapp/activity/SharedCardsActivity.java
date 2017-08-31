package com.example.android.softvisitingcardapp.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.softvisitingcardapp.R;
import com.example.android.softvisitingcardapp.gallery.GalleryFragment;
import com.example.android.softvisitingcardapp.gallery.SharedGalleryFragment;

public class SharedCardsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_cards);

        Fragment fragment = new SharedGalleryFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,fragment);
        ft.commit();
    }
}
