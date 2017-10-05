package com.example.android.softvisitingcardapp.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.softvisitingcardapp.R;
import com.example.android.softvisitingcardapp.api.APIService;
import com.example.android.softvisitingcardapp.api.APIUrl;
import com.example.android.softvisitingcardapp.gallery.CardGalleryActivity;
import com.example.android.softvisitingcardapp.gallery.CardViewed;
import com.example.android.softvisitingcardapp.models.Result;
import com.example.android.softvisitingcardapp.people.OtherUsersActivity;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Locale;

import github.nisrulz.screenshott.ScreenShott;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.attr.name;
import static com.example.android.softvisitingcardapp.R.drawable.phone;
import static com.example.android.softvisitingcardapp.R.id.add;
import static com.example.android.softvisitingcardapp.R.id.buttonSignIn;
import static com.example.android.softvisitingcardapp.R.id.card;

public class CardDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private Button onlineShareButton, offlineShareButton, updateButton, deleteButton;
    private TextView nameTextView, organizationTextView, designationTextView;
    private TextView addressTextView, websiteTextView, emailTextView, contactTextView;
    private int cardId;
    private String cardName, cardEmail, cardDesignation, cardContact;
    private String cardWebsite, cardAddress, cardOrganization, cardMakerEmail;
    private String imageURL = "https://card.thehumblelearner.com/file_upload_api/files/";
    private String logoImagePath;
    private ImageView shareIconImageView, bluetoothIconImageView, emailIconImageView, callIconImageView;
    private LinearLayout addressLayout, websiteLayout, emailLayout, contactLayout;

    private CardView userCardView;

    private ImageView backgroundImage, logoImage;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);

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

        verifyStoragePermissions(this);

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
        Picasso.with(CardDetailActivity.this).load(imageURL + logoImagePath).into(logoImage);

        // set listeners on the layout containing the text views and icons

        // when clicked opens the map with the card holders office address
        addressLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String map = "http://maps.google.co.in/maps?q=" + cardAddress;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
                startActivity(intent);

            }
        });

        // when clicked it will open the website link with existing browsers
        websiteLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String url = cardWebsite;
                if (!url.startsWith("http://") && !url.startsWith("https://"))
                    url = "http://" + url;
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);

            }
        });

        // when clicked it will open the email app to send email to the card holder
        emailLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Make an email intent to send order summary to the email
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{cardEmail});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Interested to connect");

                startActivity(Intent.createChooser(intent, "Send Email"));

            }
        });
        contactLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+cardContact));
                startActivity(callIntent);

            }
        });

        // Find the Online Share button and set click listener
        //onlineShareButton = (Button) findViewById(R.id.online_share);
        shareIconImageView.setOnClickListener(this);
        bluetoothIconImageView.setOnClickListener(this);
        //emailIconImageView.setOnClickListener(this);
        //callIconImageView.setOnClickListener(this);


        int[] attrs = new int[]{R.attr.selectableItemBackground};
        TypedArray typedArray = this.obtainStyledAttributes(attrs);
        int backgroundResource = typedArray.getResourceId(0, 0);
        shareIconImageView.setBackgroundResource(backgroundResource);
        bluetoothIconImageView.setBackgroundResource(backgroundResource);
        //emailIconImageView.setBackgroundResource(backgroundResource);
        //callIconImageView.setBackgroundResource(backgroundResource);


        // Find the Update button and set click listener
        updateButton = (Button) findViewById(R.id.update_card);
        updateButton.setOnClickListener(this);

        // Find the Delete button and set click listener
        deleteButton = (Button) findViewById(R.id.delete_card);
        deleteButton.setOnClickListener(this);
    }



    private void initViews() {
        nameTextView = (TextView) findViewById(R.id.name_text_view);
        organizationTextView = (TextView) findViewById(R.id.company_text_view);
        designationTextView = (TextView) findViewById(R.id.designation_text_view);
        addressTextView = (TextView) findViewById(R.id.address_text_view);
        websiteTextView = (TextView) findViewById(R.id.website_text_view);
        emailTextView = (TextView) findViewById(R.id.email_text_view);
        contactTextView = (TextView) findViewById(R.id.contact_text_view);

        addressLayout = (LinearLayout) findViewById(R.id.address_layout);
        websiteLayout = (LinearLayout) findViewById(R.id.website_layout);
        emailLayout = (LinearLayout) findViewById(R.id.email_layout);
        contactLayout = (LinearLayout) findViewById(R.id.contact_layout);

        shareIconImageView = (ImageView) findViewById(R.id.share_icon_image_view);
        bluetoothIconImageView = (ImageView) findViewById(R.id.bluetooth_icon_image_view);

        //emailIconImageView = (ImageView) findViewById(R.id.email_icon_image_view);
        //callIconImageView = (ImageView) findViewById(R.id.call_icon_image_view);

        userCardView = (CardView) findViewById(R.id.card);
    }

    private void deleteCard() {
        // Code here executes on main thread after user presses button
        final ProgressDialog progressDialog = new ProgressDialog(CardDetailActivity.this);
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
                    Toast.makeText(CardDetailActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(CardDetailActivity.this, MainActivity.class);
                    startActivity(intent);
                    //SharedPrefManager.getInstance(getActivity()).userLogin(response.body().getUser());

                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CardDetailActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void takeScreenshot() {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = Environment.getExternalStorageDirectory().toString() + "/PICTURES/Screenshots/" + now + ".jpg";

            // create bitmap screen capture
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);
            File imageFile = new File(mPath);
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();


            MediaScannerConnection.scanFile(this,
                    new String[]{imageFile.toString()}, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {
                            Log.i("ExternalStorage", "Scanned " + path + ":");
                            Log.i("ExternalStorage", "-> uri=" + uri);
                        }
                    });

            openScreenshot(imageFile);
        } catch (Throwable e) {
            // Several error may come out with file handling or OOM
            e.printStackTrace();
        }
    }

    private void openScreenshot(File imageFile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(imageFile);
        intent.setDataAndType(uri, "image/*");
        startActivity(intent);
    }


    @Override
    public void onClick(View view) {
        if (view == shareIconImageView) {
            // Go to OtherUsersActivity
            Intent intent = new Intent(CardDetailActivity.this, OtherUsersActivity.class);
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
        } else if (view == bluetoothIconImageView) {
            // View with spaces as per constraints
            Bitmap bitmap_view = ScreenShott.getInstance().takeScreenShotOfView(userCardView);
            OutputStream output;
            try {
                File file = ScreenShott.getInstance().saveScreenshotToPicturesFolder(this, bitmap_view, cardName + "-business card");
                String bitmapFilePath = file.getAbsolutePath();
                Toast.makeText(this, "Offline card created in " + bitmapFilePath, Toast.LENGTH_LONG).show();
                // Share Intent
                Intent share = new Intent(Intent.ACTION_SEND);

                // Type of file to share
                share.setType("image/jpeg");

                /*
                // Compress into png format image from 0% - 100%
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
                output.flush();
                output.close();
                */

                // Locate the image to Share
                Uri uri = Uri.fromFile(file);

                // Pass the image into an Intnet
                share.putExtra(Intent.EXTRA_STREAM, uri);

                // Show the social share chooser list
                startActivity(Intent.createChooser(share, "Send Card offline"));
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "No screenshots", Toast.LENGTH_LONG).show();
            }
        }else if (view == updateButton) {
            // put the card info in a bundle
            Intent intent = new Intent(CardDetailActivity.this, UpdateCardActivity.class);

            intent.putExtra("cardId", cardId);
            intent.putExtra("cardName", cardName);
            intent.putExtra("cardEmail", cardEmail);
            intent.putExtra("cardDesignation", cardDesignation);
            intent.putExtra("cardContact", cardContact);
            intent.putExtra("cardWebsite", cardWebsite);
            intent.putExtra("cardAddress", cardAddress);
            intent.putExtra("cardOrganization", cardOrganization);

            startActivity(intent);
        } else if (view == deleteButton) {
            Intent intent = new Intent(CardDetailActivity.this, DeleteCardActivity.class);
            intent.putExtra("cardId", cardId);
            //startActivity(intent);

            LayoutInflater li = LayoutInflater.from(CardDetailActivity.this);
            View promptsView = li.inflate(R.layout.dialog_delete_card, null);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CardDetailActivity.this);
            alertDialogBuilder.setView(promptsView);

            //final EditText editTextTitle = (EditText) promptsView.findViewById(R.id.editTextTitle);
            //final EditText editTextMessage = (EditText) promptsView.findViewById(R.id.editTextMessage);

            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("Delete",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    deleteCard();
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
