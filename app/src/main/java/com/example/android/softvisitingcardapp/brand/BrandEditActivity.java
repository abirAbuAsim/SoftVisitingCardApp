package com.example.android.softvisitingcardapp.brand;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.softvisitingcardapp.R;
import com.example.android.softvisitingcardapp.activity.HomeActivity;
import com.example.android.softvisitingcardapp.api.APIService;
import com.example.android.softvisitingcardapp.api.APIUrl;
import com.example.android.softvisitingcardapp.models.Brand;
import com.example.android.softvisitingcardapp.models.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BrandEditActivity extends AppCompatActivity {
    private Button editBrandButton, returnButton;
    private EditText editTextBrandName, editTextBrandDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_edit);

        Bundle extras = getIntent().getExtras();
        if (extras == null)
        {
            return;
        }
        final int brandId = extras.getInt("brandId");
        final String brandName = extras.getString("brandName");
        final String brandDetails = extras.getString("brandDetails");

        editBrandButton = findViewById(R.id.edit_brand_button);

        editTextBrandName = (EditText) findViewById(R.id.brand_name_edit_text);
        editTextBrandName.setText(brandName);
        editTextBrandDetails = (EditText) findViewById(R.id.brand_details_edit_text);
        editTextBrandDetails.setText(brandDetails);

        editBrandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editListItem(brandId, brandName, brandDetails);
            }
        });
    }

    public void editListItem(int brandId, String brandName, String brandDetails){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        String updatedBrandName = editTextBrandName.getText().toString().trim();
        String updatedBrandDetails = editTextBrandDetails.getText().toString().trim();

        Call<Result> call = service.editBrand(brandId, updatedBrandName, updatedBrandDetails);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                // do something after editing is

                if (!response.body().getError()) {
                    Brand mBrand = response.body().getBrand();
                    editTextBrandName.setText(mBrand.getName());
                    editTextBrandDetails.setText(mBrand.getDetails());
                    Toast.makeText(BrandEditActivity.this, "Details Edit successful", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(BrandEditActivity.this, HomeActivity.class);
                    intent.putExtra("fragmentToTrigger", "BrandList");
                    startActivity(intent);
                } else {
                    Toast.makeText(BrandEditActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                // show some message after failure
                Toast.makeText(BrandEditActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }



}
