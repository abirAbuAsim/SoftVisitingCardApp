package com.example.android.softvisitingcardapp.gallery;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.android.softvisitingcardapp.R;
import com.example.android.softvisitingcardapp.activity.CardDetailActivity;
import com.example.android.softvisitingcardapp.people.LinkedPeopleActivity;
import com.example.android.softvisitingcardapp.people.OtherUsersActivity;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by absak on 8/21/2017.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private ArrayList<CardViewed> cards;
    private Context mCtx;

    private String imageURL = "https://card.thehumblelearner.com/file_upload_api/files/";
    private String logoImagePath;

    public CardAdapter(ArrayList<CardViewed> cards, Context mCtx) {
        this.cards = cards;
        this.mCtx = mCtx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_cards, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CardAdapter.ViewHolder holder, int position) {
        final CardViewed card = cards.get(position);
        holder.nameTextView.setText(card.getName());
        holder.organizationTextView.setText(card.getOrganization());
        holder.designationTextView.setText(card.getDesignation());
        holder.addressTextView.setText(card.getAddress());
        holder.websiteTextView.setText(card.getWebsite());
        holder.emailTextView.setText(card.getEmail());
        holder.contactTextView.setText(card.getContact());

        String uri = "@drawable/" + card.getBackgroundImage();

        final int imageResource = mCtx.getResources().getIdentifier(uri, null, mCtx.getPackageName());


        final Drawable res = mCtx.getResources().getDrawable(imageResource);

        holder.backgroundImage.setImageDrawable(res);

        logoImagePath = card.getLogoImage();
        Picasso.with(mCtx).load(imageURL+logoImagePath).into(holder.logoImage);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cardIntent = new Intent(mCtx, CardDetailActivity.class);
                // send the card at the selected position in bundle
                cardIntent.putExtra("cardId", card.getId());
                cardIntent.putExtra("cardName", card.getName());
                cardIntent.putExtra("cardEmail", card.getEmail());
                cardIntent.putExtra("cardDesignation", card.getDesignation());
                cardIntent.putExtra("cardContact", card.getContact());
                cardIntent.putExtra("cardWebsite", card.getWebsite());
                cardIntent.putExtra("cardAddress", card.getAddress());
                cardIntent.putExtra("cardOrganization", card.getOrganization());
                cardIntent.putExtra("imageResource", imageResource);
                cardIntent.putExtra("logoImagePath", card.getLogoImage());
                cardIntent.putExtra("cardMakerEmail", card.getCardMakerEmail());
                mCtx.startActivity(cardIntent);

            }
        });

    }

/*    //method to send message to the user
    private void sendMessage(int id, String title, String message) {

        final ProgressDialog progressDialog = new ProgressDialog(mCtx);
        progressDialog.setMessage("Sending Message...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);


        Call<MessageResponse> call = service.sendMessage(
                SharedPrefManager.getInstance(mCtx).getUser().getId(),
                id,
                title,
                message
        );

        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                progressDialog.dismiss();
                Toast.makeText(mCtx, response.body().getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(mCtx, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }*/

    @Override
    public int getItemCount() {
        return cards.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView, designationTextView, emailTextView, contactTextView;
        public TextView organizationTextView, addressTextView, websiteTextView;
        public ImageView backgroundImage, logoImage;

        // Initialize the card view in the list
        public CardView cardView;
        //public ImageButton imageButtonMessage;

        public ViewHolder(View itemView) {
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.name_text_view);
            organizationTextView = (TextView) itemView.findViewById(R.id.company_text_view);
            designationTextView = (TextView) itemView.findViewById(R.id.designation_text_view);
            addressTextView = (TextView) itemView.findViewById(R.id.address_text_view);
            websiteTextView = (TextView) itemView.findViewById(R.id.website_text_view);
            emailTextView = (TextView) itemView.findViewById(R.id.email_text_view);
            contactTextView = (TextView) itemView.findViewById(R.id.contact_text_view);

            backgroundImage = (ImageView) itemView.findViewById(R.id.card_background_image_view);
            logoImage = (ImageView) itemView.findViewById(R.id.logo_image);

            cardView = (CardView) itemView.findViewById(R.id.card);

            //imageButtonMessage = (ImageButton) itemView.findViewById(R.id.imageButtonMessage);
        }
    }
}