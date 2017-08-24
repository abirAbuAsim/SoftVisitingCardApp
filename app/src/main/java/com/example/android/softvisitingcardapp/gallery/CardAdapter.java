package com.example.android.softvisitingcardapp.gallery;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.android.softvisitingcardapp.ModelClass.CardSent;
import com.example.android.softvisitingcardapp.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by absak on 8/21/2017.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private ArrayList<CardViewed> cards;
    private Context mCtx;

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
        holder.designationTextView.setText(card.getDesignation());
        holder.emailTextView.setText(card.getEmail());
        holder.contactTextView.setText(card.getContact());

        String uri = "@drawable/" + card.getBackgroundImage();

        int imageResource = mCtx.getResources().getIdentifier(uri, null, mCtx.getPackageName());


        Drawable res = mCtx.getResources().getDrawable(imageResource);


        holder.backgroundImage.setImageDrawable(res);


        /*
        holder.imageButtonMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater li = LayoutInflater.from(mCtx);
                View promptsView = li.inflate(R.layout.dialog_send_message, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mCtx);
                alertDialogBuilder.setView(promptsView);

                final EditText editTextTitle = (EditText) promptsView.findViewById(R.id.editTextTitle);
                final EditText editTextMessage = (EditText) promptsView.findViewById(R.id.editTextMessage);

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Send",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        //getting the values
                                        String title = editTextTitle.getText().toString().trim();
                                        String message = editTextMessage.getText().toString().trim();

                                        //sending the message
                                        sendMessage(card.getId(), title, message);
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });
        */
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
        public ImageView backgroundImage;
        //public ImageButton imageButtonMessage;

        public ViewHolder(View itemView) {
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.name_text_view);
            designationTextView = (TextView) itemView.findViewById(R.id.designation_text_view);
            emailTextView = (TextView) itemView.findViewById(R.id.email_text_view);
            contactTextView = (TextView) itemView.findViewById(R.id.contact_text_view);

            backgroundImage = (ImageView) itemView.findViewById(R.id.card_background_image_view);

            //imageButtonMessage = (ImageButton) itemView.findViewById(R.id.imageButtonMessage);
        }
    }
}