package com.example.android.softvisitingcardapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static android.R.attr.name;
import static android.R.attr.password;
import static android.R.attr.type;

public class CreateActivity extends AppCompatActivity {
    EditText nameEditText, companyNameEditText, designationEditText, addressEditText,
            websiteEditText, emailEditText, mobileNumberEditText;
    String name, companyName, designation, address, website, email, mobileNumber;
    private ImageView logoImage;
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

        // get the logo image from layout
        logoImage = (ImageView) findViewById(R.id.logo_image);
        // open select dialog to select image from the options
        logoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
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
        companyNameEditText = (EditText) findViewById(R.id.company_name_edit_text);
        final TextView companyNameTextView = (TextView) findViewById(R.id.company_text_view);
        companyNameEditText.addTextChangedListener(new TextWatcher() {
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
        mobileNumberEditText = (EditText) findViewById(R.id.mobile_number_edit_text);
        final TextView mobileNumberTextView = (TextView) findViewById(R.id.mobile_number_text_view);
        mobileNumberEditText.addTextChangedListener(new TextWatcher() {
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


    public void onRegister(View view){
        name = nameEditText.getText().toString();
        email = emailEditText.getText().toString();
        companyName = companyNameEditText.getText().toString();
        designation = designationEditText.getText().toString();
        address = addressEditText.getText().toString();
        mobileNumber = mobileNumberEditText.getText().toString();
        website = websiteEditText.getText().toString();

        String type = "register";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, name, email, companyName,
                                designation, address, mobileNumber, website);
    }


    private void selectImage() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(CreateActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment
                            .getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    // pic = f;
                    startActivityForResult(intent, 1);
                } else if (options[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                // h=0;
                File f = new File(Environment.getExternalStorageDirectory()
                        .toString());

                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        File photo = new File(
                                Environment.getExternalStorageDirectory(),
                                "temp.jpg");
                        // pic = photo;
                        break;
                    }
                }
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),

                            bitmapOptions);
                    logoImage.setImageBitmap(bitmap);
                    String path = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "Phoenix" + File.separator + "default";
                    // p = path;
                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(path, String.valueOf(System
                            .currentTimeMillis()) + ".jpg");

                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        // pic=file;
                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 2) {
                Uri selectedImage = data.getData();
                logoImage.setImageURI(selectedImage);
                /*String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getContentResolver().query(selectedImage, filePath,
                        null, null, null);

                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = BitmapFactory.decodeFile(picturePath);
                Log.w("image path from gallery", picturePath + "");
                logoImage.setImageBitmap(thumbnail);*/
            }
        }

        /*
        save=(Button) findViewById(R.id.btnSave);
        save.setOnClickListener((View.OnClickListener) this);
        */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_create_card, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
