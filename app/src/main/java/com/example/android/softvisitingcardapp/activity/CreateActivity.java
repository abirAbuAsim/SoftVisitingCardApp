package com.example.android.softvisitingcardapp.activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.android.softvisitingcardapp.ModelClass.EventModel;
import com.example.android.softvisitingcardapp.ModelClass.CardSent;
import com.example.android.softvisitingcardapp.NetworkRelatedClass.NetworkCall;
import com.example.android.softvisitingcardapp.R;
import com.example.android.softvisitingcardapp.api.APIService;
import com.example.android.softvisitingcardapp.api.APIUrl;
import com.example.android.softvisitingcardapp.gallery.CardViewed;
import com.example.android.softvisitingcardapp.helper.SharedPrefManager;
import com.example.android.softvisitingcardapp.models.Result;
import com.example.android.softvisitingcardapp.models.User;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.attr.logo;
import static android.R.attr.password;


public class CreateActivity extends AppCompatActivity {
    private EditText nameEditText, emailEditText, designationEditText;
    private EditText contactEditText, websiteEditText, addressEditText;
    private EditText organizationEditText;
    private AppCompatButton createCardButton;
    String name, companyName, designation, address, website, email, mobileNumber;
    private ImageView logoImage, backgroundImage;
    String mediaPath;




    private String filePath;
    private int backgroundImageID;
    String selectedDesignName;
    private static final int PICK_PHOTO = 1958;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventModel event) throws ClassNotFoundException {
        if (event.isTagMatchWith("response")) {
            String responseMessage = "Response from Server:\n" + event.getMessage();
            Toast.makeText(this, responseMessage,
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);


        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        int res = extras.getInt("res");



        ImageView templateImage = (ImageView) findViewById(R.id.card_background_image_view);
        templateImage.setImageResource(res);
        backgroundImageID = res;

        // Initialize the views
        createCardButton = (AppCompatButton)findViewById(R.id.create_card_button);

        nameEditText = (EditText)findViewById(R.id.name_edit_text);
        emailEditText = (EditText)findViewById(R.id.email_edit_text);
        designationEditText = (EditText)findViewById(R.id.designation_edit_text);
        contactEditText = (EditText)findViewById(R.id.contact_edit_text);
        websiteEditText = (EditText)findViewById(R.id.website_edit_text);
        addressEditText = (EditText)findViewById(R.id.address_edit_text);
        organizationEditText = (EditText)findViewById(R.id.organization_edit_text);

        verifyStoragePermissions(this);

        backgroundImage = (ImageView) findViewById(R.id.card_background_image_view);
        // get the logo image from layout
        logoImage = (ImageView) findViewById(R.id.logo_image);
        // open select dialog to select image from the options
        logoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_PHOTO);
            }
        });


        // Editing the name and seeing the change in the card
        nameEditText = (EditText) findViewById(R.id.name_edit_text);
        final TextView nameTextView = (TextView) findViewById(R.id.name_text_view);
        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                nameTextView.setText(s.toString());
            }
        });

        // Editing the company name and seeing the change in the card
        organizationEditText = (EditText) findViewById(R.id.organization_edit_text);
        final TextView companyNameTextView = (TextView) findViewById(R.id.company_text_view);
        organizationEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                companyNameTextView.setText(s.toString());
            }
        });

        // Editing the designation and seeing the change in the card
        designationEditText = (EditText) findViewById(R.id.designation_edit_text);
        final TextView designationTextView = (TextView) findViewById(R.id.designation_text_view);
        designationEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                designationTextView.setText(s.toString());
            }
        });

        // Editing the address and seeing the change in the card
        addressEditText = (EditText) findViewById(R.id.address_edit_text);
        final TextView addressTextView = (TextView) findViewById(R.id.address_text_view);
        addressEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                addressTextView.setText(s.toString());
            }
        });

        // Editing the website and seeing the change in the card
        websiteEditText = (EditText) findViewById(R.id.website_edit_text);
        final TextView websiteTextView = (TextView) findViewById(R.id.website_text_view);
        websiteEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                websiteTextView.setText(s.toString());
            }
        });

        // Editing the email and seeing the change in the card
        emailEditText = (EditText) findViewById(R.id.email_edit_text);
        final TextView emailTextView = (TextView) findViewById(R.id.email_text_view);
        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                emailTextView.setText(s.toString());
            }
        });

        // Editing the email and seeing the change in the card
        contactEditText = (EditText) findViewById(R.id.contact_edit_text);
        final TextView mobileNumberTextView = (TextView) findViewById(R.id.company_text_view);
        contactEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mobileNumberTextView.setText(s.toString());
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_PHOTO) {
            Uri imageUri = data.getData();
            filePath = getPath(imageUri);
            logoImage.setImageURI(imageUri);
            createCardButton.setVisibility(View.VISIBLE);
        }
    }

    public void uploadButtonClicked(View view) {
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String designation = designationEditText.getText().toString();
        String contact = contactEditText.getText().toString();
        String website = websiteEditText.getText().toString();
        String address = addressEditText.getText().toString();
        String organization = organizationEditText.getText().toString();

        // get the selected image's filename from filepath
        String logoImage = filePath.substring(filePath.lastIndexOf("/")+1);

        String backgroundImageName = backgroundImage.getResources().getResourceName(backgroundImageID);
        backgroundImageName = backgroundImageName.substring(backgroundImageName.lastIndexOf("/")+1);

        // get the user email from SharedPreference and use it as the maker of the card being created.
        //SharedPreferences userDetails = getApplicationContext().getSharedPreferences("userDetails", Context.MODE_PRIVATE);


        //String cardMakerEmail= userDetails.getString(Constants.EMAIL, "");
        //String cardMakerEmail= "abir.sakif@gmail.com";

        String cardMakerEmail = SharedPrefManager.getInstance(this).getUser().getEmail();

        NetworkCall.fileUpload(filePath, new CardSent(name, email, designation, contact,
                website, address, organization, backgroundImageName,logoImage, cardMakerEmail));



        // Create card using api package

        //calling the api
        //defining a progress dialog to show while signing up
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Creating card, please wait ...");
        progressDialog.show();

        //building retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Defining retrofit api service
        APIService service = retrofit.create(APIService.class);

        //Defining the user object as we need to pass it with the call
        // sending card id as -1 as default value
        CardViewed card = new CardViewed(-1, name, email, designation, contact, website, address,
                                        organization, backgroundImageName, logoImage, cardMakerEmail);

        //defining the call
        Call<Result> call = service.createCard(
                card.getName(),
                card.getEmail(),
                card.getDesignation(),
                card.getContact(),
                card.getWebsite(),
                card.getAddress(),
                card.getOrganization(),
                card.getBackgroundImage(),
                card.getLogoImage(),
                card.getCardMakerEmail()
        );


        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                //hiding progress dialog
                progressDialog.dismiss();

                //displaying the message from the response as toast
                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();

                //if there is no error
                if (!response.body().getError()) {
                    //starting profile activity
                    finish();

                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }




}
