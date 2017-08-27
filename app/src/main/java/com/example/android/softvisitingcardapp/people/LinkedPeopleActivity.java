package com.example.android.softvisitingcardapp.people;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.softvisitingcardapp.R;

public class LinkedPeopleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linked_people);

        Bundle extras = getIntent().getExtras();
        if (extras == null)
        {
            return;
        }
        final String userEmail = extras.getString("userEmail");
        //final int cardId = extras.getInt("cardId");

        Bundle bundle = new Bundle();
        bundle.putString("userEmail", userEmail);
        //bundle.putInt("cardId", cardId);

        Fragment fragment = new PeopleFragment();
        fragment.setArguments(bundle);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,fragment);
        ft.commit();
    }
}
