package com.example.android.softvisitingcardapp.people;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.example.android.softvisitingcardapp.R;
import com.example.android.softvisitingcardapp.api.APIService;
import com.example.android.softvisitingcardapp.api.APIUrl;
import com.example.android.softvisitingcardapp.models.Result;
import com.example.android.softvisitingcardapp.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Belal on 14/04/17.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<User> users;
    private Context mCtx;

    public UserAdapter(List<User> users, Context mCtx) {
        this.users = users;
        this.mCtx = mCtx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_users, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UserAdapter.ViewHolder holder, int position) {
        final User user = users.get(position);
        holder.textViewName.setText(user.getName());
        holder.textViewUserName.setText(user.getEmail());

        holder.deleteLinkImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater li = LayoutInflater.from(mCtx);
                View promptsView = li.inflate(R.layout.dialog_remove_linked_user, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mCtx);
                alertDialogBuilder.setView(promptsView);

                //final EditText editTextTitle = (EditText) promptsView.findViewById(R.id.editTextTitle);
                //final EditText editTextMessage = (EditText) promptsView.findViewById(R.id.editTextMessage);

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Remove",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        // remove linked user
                                        String receiverEmail = user.getEmail(); // the linked user
                                        String senderEmail = PeopleFragment.userEmail; // the logged in app user
                                        removeLinkedUser(senderEmail, receiverEmail);


                                        //getting the values
                                        //String title = editTextTitle.getText().toString().trim();
                                        //String message = editTextMessage.getText().toString().trim();

                                        //sending the message
                                        //sendMessage(user.getEmail());
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
    }

    /**
     *  Remove linked user from the linked people list
     **/
    private void removeLinkedUser(final String senderEmail, final String receiverEmail) {
        // Code here executes on main thread after user presses button
        final ProgressDialog progressDialog = new ProgressDialog(mCtx);
        progressDialog.setMessage("Removing linked user...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);


        Call<Result> call = service.removeLinkedUser(senderEmail, receiverEmail);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();

                if (!response.body().getError()) {
                    Toast.makeText(mCtx, response.body().getMessage(), Toast.LENGTH_LONG).show();
                    //Toast.makeText(mCtx, senderEmail + " " + receiverEmail, Toast.LENGTH_LONG).show(); // debug

                    //SharedPrefManager.getInstance(getActivity()).userLogin(response.body().getUser());

                }

                //Toast.makeText(mCtx, senderEmail + " " + receiverEmail, Toast.LENGTH_LONG).show(); // debug
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(mCtx, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName, textViewUserName;
        public ImageButton deleteLinkImageButton;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            textViewUserName = (TextView) itemView.findViewById(R.id.textViewUserName);
            deleteLinkImageButton = (ImageButton) itemView.findViewById(R.id.delete_link_image_button);
        }
    }
}