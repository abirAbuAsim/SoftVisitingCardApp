package com.example.android.softvisitingcardapp.gallery;




import com.example.android.softvisitingcardapp.ModelClass.CardSent;

import java.util.ArrayList;

/**
 * Created by Belal on 14/04/17.
 */

public class Cards {

    private ArrayList<CardViewed> cards;

    public Cards() {

    }

    public ArrayList<CardViewed> getCards() {
        return cards;
    }

    public void setUsers(ArrayList<CardViewed> cards) {
        this.cards = cards;
    }
}
