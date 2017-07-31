package com.example.android.softvisitingcardapp;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.softvisitingcardapp.authentication.UserProfileActivity;


public class HomeMenuFragment extends Fragment {
    // Declare the views
    CardView createCardView, cardGalleryCardView, linkedPeopleCardView, userProfileCardView, aboutUsCardView;

    private SharedPreferences pref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_menu,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        pref = getActivity().getPreferences(0);
        // Find the Create menu card
        createCardView = (CardView) getActivity().findViewById(R.id.create_card_view);
        createCardView.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Create Card View is clicked on.
            @Override
            public void onClick(View view) {
                Intent menuIntent = new Intent(getActivity(), SelectDesignActivity.class);
                startActivity(menuIntent);
            }
        });
        // Find the Card Gallery menu card
        cardGalleryCardView = (CardView) getActivity().findViewById(R.id.card_gallery_card_view);

        cardGalleryCardView.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Card Gallery Card View is clicked on.
            @Override
            public void onClick(View view) {
                Intent menuIntent = new Intent(getActivity(), CardGalleryActivity.class);
                startActivity(menuIntent);
            }
        });
        // Find the Linked People Card view
        linkedPeopleCardView = (CardView) getActivity().findViewById(R.id.link_card_view);
        linkedPeopleCardView.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Linked People View is clicked on.
            @Override
            public void onClick(View view) {
                Intent linkedPeopleIntent = new Intent(getActivity(), LinkedPeopleActivity.class);
                startActivity(linkedPeopleIntent);
            }
        });
        // Find the About us menu card
        aboutUsCardView = (CardView) getActivity().findViewById(R.id.about_us_card_view);
        aboutUsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menuIntent = new Intent(getActivity(), AboutUsActivity.class);
                startActivity(menuIntent);
            }
        });
        userProfileCardView = (CardView) getActivity().findViewById(R.id.user_profile_card_view);
        userProfileCardView.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Create Card View is clicked on.
            @Override
            public void onClick(View view) {
                Intent menuIntent = new Intent(getActivity(), UserProfileActivity.class);
                startActivity(menuIntent);
            }
        });
    }


}
