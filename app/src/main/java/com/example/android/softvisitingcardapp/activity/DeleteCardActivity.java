package com.example.android.softvisitingcardapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.softvisitingcardapp.R;
import com.example.android.softvisitingcardapp.api.APIService;
import com.example.android.softvisitingcardapp.api.APIUrl;
import com.example.android.softvisitingcardapp.fragment.HomeMenuFragment;
import com.example.android.softvisitingcardapp.models.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DeleteCardActivity extends AppCompatActivity {
    private int cardId;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_card);

        // Check to see if there is any bundled info
        Bundle extras = getIntent().getExtras();
        if (extras == null)
        {
            return;
        }

        // Get the card details from the bundle
        // Store the info in appropriate variables as sent
        cardId = extras.getInt("cardId");

        deleteButton = (Button) findViewById(R.id.confirm_delete_button);
        deleteButton.setText(Integer.toString(cardId));
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                final ProgressDialog progressDialog = new ProgressDialog(DeleteCardActivity.this);
                progressDialog.setMessage("Deleting...");
                progressDialog.show();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(APIUrl.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                APIService service = retrofit.create(APIService.class);



                Call<Result> call = service.deleteCard(cardId);

                call.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        progressDialog.dismiss();

                        if (!response.body().getError()) {
                            Toast.makeText(DeleteCardActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(DeleteCardActivity.this, MainActivity.class);
                            startActivity(intent);
                            //SharedPrefManager.getInstance(getActivity()).userLogin(response.body().getUser());

                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(DeleteCardActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }

    private void deleteUser(){

    }
}
