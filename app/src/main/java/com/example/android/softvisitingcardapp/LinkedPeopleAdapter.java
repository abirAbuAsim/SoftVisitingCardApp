package com.example.android.softvisitingcardapp;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by absak on 7/25/2017.
 */

public class LinkedPeopleAdapter extends ArrayAdapter<LinkedPeople> {
    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param linkedPeoples A List of AndroidFlavor objects to display in a list
     */
    public LinkedPeopleAdapter(Activity context, ArrayList<LinkedPeople> linkedPeoples) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, linkedPeoples);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

         // Get the {@link AndroidFlavor} object located at this position in the list
        final LinkedPeople currentLinkedPeople = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView linkNameTextView = (TextView) listItemView.findViewById(R.id.link_name_text_view);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        linkNameTextView.setText(currentLinkedPeople.getmLinkName());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView linkEmailTextView = (TextView) listItemView.findViewById(R.id.link_email_text_view);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        linkEmailTextView.setText(currentLinkedPeople.getmLinkEmail());


        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
