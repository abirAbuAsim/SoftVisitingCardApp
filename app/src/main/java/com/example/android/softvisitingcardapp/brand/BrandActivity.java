package com.example.android.softvisitingcardapp.brand;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.example.android.softvisitingcardapp.models.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BrandActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonAddBrand, returnButton;
    private EditText editTextBrandName, editTextBrandDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand);

        this.setTitle("Add Brand");

        buttonAddBrand = findViewById(R.id.add_brand_button);


        editTextBrandName = findViewById(R.id.brand_name_edit_text);
        editTextBrandDetails = (EditText) findViewById(R.id.brand_details_edit_text);

        buttonAddBrand.setOnClickListener(this);
    }

    private void addBrandInfo() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Processing...");
        progressDialog.show();

        String brandName = editTextBrandName.getText().toString().trim();
        String brandDetails = editTextBrandDetails.getText().toString().trim();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<Result> call = service.addBrandInfo(brandName, brandDetails);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                Toast.makeText(BrandActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                editTextBrandName.setText("");
                editTextBrandDetails.setText("");
                //Intent intent = new Intent(BrandActivity.this, BrandListFragment.class);
                //startActivity(intent);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(BrandActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == buttonAddBrand) {
            addBrandInfo();
            Intent intent = new Intent(BrandActivity.this, HomeActivity.class);
            intent.putExtra("fragmentToTrigger", "BrandList");
            startActivity(intent);
        }
    }
}
