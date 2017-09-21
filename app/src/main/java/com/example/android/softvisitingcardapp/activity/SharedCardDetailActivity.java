package com.example.android.softvisitingcardapp.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.softvisitingcardapp.R;
import com.example.android.softvisitingcardapp.api.APIService;
import com.example.android.softvisitingcardapp.api.APIUrl;
import com.example.android.softvisitingcardapp.helper.SharedPrefManager;
import com.example.android.softvisitingcardapp.models.Result;
import com.example.android.softvisitingcardapp.people.OtherUsersActivity;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SharedCardDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private Button removeSharedCardButton;
    private TextView nameTextView, organizationTextView, designationTextView;
    private TextView addressTextView, websiteTextView, emailTextView, contactTextView;
    private int cardId;
    private String cardName, cardEmail, cardDesignation, cardContact;
    private String cardWebsite, cardAddress, cardOrganization, cardMakerEmail;
    private String imageURL = "https://card.thehumblelearner.com/file_upload_api/files/";
    private String logoImagePath;
    private ImageView shareIconImageView, emailIconImageView, callIconImageView;
    private String senderEmail; // the card maker email
    private String receiverEmail; // app user email
    private ImageView backgroundImage, logoImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_card_detail);

        // Check to see if there is any bundled info
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }

        // Get the card details from the bundle
        // Store the info in appropriate variables as sent
        cardId = extras.getInt("cardId");
        cardName = extras.getString("cardName");
        cardEmail = extras.getString("cardEmail");
        cardDesignation = extras.getString("cardDesignation");
        cardContact = extras.getString("cardContact");
        cardWebsite = extras.getString("cardWebsite");
        cardAddress = extras.getString("cardAddress");
        cardOrganization = extras.getString("cardOrganization");
        cardMakerEmail = extras.getString("cardMakerEmail");

        logoImagePath = extras.getString("logoImagePath");

        // Initialize the views
        initViews();

        // Set the TextViews from the bundle info
        // maintain the order as seen in the UI
        nameTextView.setText(cardName);
        organizationTextView.setText(cardOrganization);
        designationTextView.setText(cardDesignation);
        addressTextView.setText(cardAddress);
        websiteTextView.setText(cardWebsite);
        emailTextView.setText(cardEmail);
        contactTextView.setText(cardContact);

        // Get the resource id of the background image
        int imageResource = extras.getInt("imageResource");

        // Get drawable resource from the resource id
        final Drawable res = getResources().getDrawable(imageResource);

        // find the ImageView in the layout
        backgroundImage = (ImageView) findViewById(R.id.card_background_image_view);

        // Set the ImageView with the appropriate drawable
        backgroundImage.setImageDrawable(res);

        // Initialize logoImage
        logoImage = (ImageView) findViewById(R.id.logo_image);
        Picasso.with(SharedCardDetailActivity.this).load(imageURL + logoImagePath).into(logoImage);


        // Find the Online Share button and set click listener
        //onlineShareButton = (Button) findViewById(R.id.online_share);
        shareIconImageView.setOnClickListener(this);
        emailIconImageView.setOnClickListener(this);
        //callIconImageView.setOnClickListener(this);

        // Debug: test screenshot capture using this icon
        callIconImageView.setOnClickListener(this);

        int[] attrs = new int[]{R.attr.selectableItemBackground};
        TypedArray typedArray = this.obtainStyledAttributes(attrs);
        int backgroundResource = typedArray.getResourceId(0, 0);
        shareIconImageView.setBackgroundResource(backgroundResource);
        emailIconImageView.setBackgroundResource(backgroundResource);
        callIconImageView.setBackgroundResource(backgroundResource);


        // set the sender email
        senderEmail = cardMakerEmail;

        // set the receiver email with the app user email from Shared preference
        receiverEmail = SharedPrefManager.getInstance(this).getUser().getEmail();

        // Find the Update button and set click listener
        removeSharedCardButton = (Button) findViewById(R.id.remove_card);
        removeSharedCardButton.setOnClickListener(this);
    }



    private void initViews() {
        nameTextView = (TextView) findViewById(R.id.name_text_view);
        organizationTextView = (TextView) findViewById(R.id.company_text_view);
        designationTextView = (TextView) findViewById(R.id.designation_text_view);
        addressTextView = (TextView) findViewById(R.id.address_text_view);
        websiteTextView = (TextView) findViewById(R.id.website_text_view);
        emailTextView = (TextView) findViewById(R.id.email_text_view);
        contactTextView = (TextView) findViewById(R.id.contact_text_view);

        shareIconImageView = (ImageView) findViewById(R.id.share_icon_image_view);
        emailIconImageView = (ImageView) findViewById(R.id.email_icon_image_view);
        callIconImageView = (ImageView) findViewById(R.id.call_icon_image_view);
    }

    private void removeSharedCard() {
        // Code here executes on main thread after user presses button
        final ProgressDialog progressDialog = new ProgressDialog(SharedCardDetailActivity.this);
        progressDialog.setMessage("Removing shared card...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);


        Call<Result> call = service.removeSharedCard(cardId, senderEmail, receiverEmail);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();

                if (!response.body().getError()) {
                    Toast.makeText(SharedCardDetailActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SharedCardDetailActivity.this, MainActivity.class);
                    startActivity(intent);
                    //SharedPrefManager.getInstance(getActivity()).userLogin(response.body().getUser());

                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(SharedCardDetailActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }



    @Override
    public void onClick(View view) {
        if (view == shareIconImageView) {
            // Go to OtherUsersActivity
            Intent intent = new Intent(SharedCardDetailActivity.this, OtherUsersActivity.class);
            // send the card at the selected position in bundle
            intent.putExtra("cardId", cardId);
            startActivity(intent);
        } else if (view == emailIconImageView) {
            // Make an email intent to send order summary to the email
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{cardEmail});
            intent.putExtra(Intent.EXTRA_SUBJECT, "Interested to connect");

            startActivity(Intent.createChooser(intent, "Send Email"));
        } else if (view == callIconImageView) {

        } else if (view == removeSharedCardButton) {
            //Intent intent = new Intent(SharedCardDetailActivity.this, DeleteCardActivity.class);
            //intent.putExtra("cardId", cardId);
            //startActivity(intent);

            LayoutInflater li = LayoutInflater.from(SharedCardDetailActivity.this);
            View promptsView = li.inflate(R.layout.dialog_delete_card, null);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SharedCardDetailActivity.this);
            alertDialogBuilder.setView(promptsView);

            //final EditText editTextTitle = (EditText) promptsView.findViewById(R.id.editTextTitle);
            //final EditText editTextMessage = (EditText) promptsView.findViewById(R.id.editTextMessage);

            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("Remove",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    removeSharedCard();
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
    }
}
