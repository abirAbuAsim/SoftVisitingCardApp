package com.example.android.softvisitingcardapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class LinkedPeopleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linked_people_list);


        // Create a list of words
        final ArrayList<LinkedPeople> linkedPeoples = new ArrayList<LinkedPeople>();
        linkedPeoples.add(new LinkedPeople("Arif", "arif@gmail.com"));
        linkedPeoples.add(new LinkedPeople("Jamil", "jamil@gmail.com"));
        linkedPeoples.add(new LinkedPeople("Riyad", "riyad@gmail.com"));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        LinkedPeopleAdapter adapter = new LinkedPeopleAdapter(LinkedPeopleActivity.this, linkedPeoples);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);
    }
}
