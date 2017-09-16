package com.example.android.softvisitingcardapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.softvisitingcardapp.R;
import com.example.android.softvisitingcardapp.api.APIService;
import com.example.android.softvisitingcardapp.api.APIUrl;
import com.example.android.softvisitingcardapp.helper.SharedPrefManager;
import com.example.android.softvisitingcardapp.models.Result;
import com.example.android.softvisitingcardapp.models.User;
import com.example.android.softvisitingcardapp.people.OtherUsersActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.android.softvisitingcardapp.R.id.editTextEmail;
import static com.example.android.softvisitingcardapp.R.id.editTextName;
import static com.example.android.softvisitingcardapp.R.id.editTextPassword;

public class UpdateCardActivity extends AppCompatActivity implements View.OnClickListener{
    Button updateButton;

    private int cardId;

    private String cardName, cardEmail, cardDesignation, cardContact;
    private String cardWebsite, cardAddress, cardOrganization;

    private EditText nameEditText, emailEditText, designationEditText, contactEditText;
    private EditText websiteEditText, addressEditText, organizationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_card);

        Bundle extras = getIntent().getExtras();
        if (extras == null)
        {
            return;
        }

        cardId = extras.getInt("cardId");
        cardName = extras.getString("cardName");
        cardEmail = extras.getString("cardEmail");
        cardDesignation = extras.getString("cardDesignation");
        cardContact = extras.getString("cardContact");
        cardWebsite = extras.getString("cardWebsite");
        cardAddress = extras.getString("cardAddress");
        cardOrganization = extras.getString("cardOrganization");

        initViews();

        updateButton = (Button) findViewById(R.id.buttonUpdate);
        updateButton.setOnClickListener(this);
    }

    private void initViews(){
        nameEditText = (EditText) findViewById(R.id.name_edit_text);
        emailEditText = (EditText) findViewById(R.id.email_edit_text);
        designationEditText = (EditText) findViewById(R.id.designation_edit_text);
        contactEditText = (EditText) findViewById(R.id.contact_edit_text);
        websiteEditText = (EditText) findViewById(R.id.website_edit_text);
        addressEditText = (EditText) findViewById(R.id.address_edit_text);
        organizationEditText = (EditText) findViewById(R.id.organization_edit_text);

        nameEditText.setText(cardName);
        emailEditText.setText(cardEmail);
        designationEditText.setText(cardDesignation);
        contactEditText.setText(cardContact);
        websiteEditText.setText(cardWebsite);
        addressEditText.setText(cardAddress);
        organizationEditText.setText(cardOrganization);
    }

    private void updateCard() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        String updatedName = nameEditText.getText().toString().trim();
        String updatedEmail = emailEditText.getText().toString().trim();
        String updatedDesignation = designationEditText.getText().toString().trim();
        String updatedContact = contactEditText.getText().toString().trim();
        String updatedWebsite = websiteEditText.getText().toString().trim();
        String updatedAddress = addressEditText.getText().toString().trim();
        String updatedOrganization = organizationEditText.getText().toString().trim();


        Call<Result> call = service.updateCard(
                cardId,
                updatedName,
                updatedEmail,
                updatedDesignation,
                updatedContact,
                updatedWebsite,
                updatedAddress,
                updatedOrganization
        );

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                Toast.makeText(getBaseContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                if (!response.body().getError()) {
                    //SharedPrefManager.getInstance(getActivity()).userLogin(response.body().getUser());
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == updateButton) {
            updateCard();
        }
    }
}
