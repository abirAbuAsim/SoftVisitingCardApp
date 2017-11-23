package com.example.android.softvisitingcardapp.supplier;

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
import com.example.android.softvisitingcardapp.category.CategoryAddActivity;
import com.example.android.softvisitingcardapp.models.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SupplierAddActivity extends AppCompatActivity implements View.OnClickListener {
    private Button addSupplierButton;
    private EditText supplierNameEditText, supplierAddressEditText;
    private EditText supplierPhoneNumberEditText, supplierCompanyNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_add);

        this.setTitle("Add Supplier");

        addSupplierButton = findViewById(R.id.add_supplier_button);


        supplierNameEditText = findViewById(R.id.supplier_name_edit_text);
        supplierAddressEditText = findViewById(R.id.supplier_address_edit_text);
        supplierPhoneNumberEditText = findViewById(R.id.supplier_phone_number_edit_text);
        supplierCompanyNameEditText = findViewById(R.id.supplier_company_name_edit_text);

        addSupplierButton.setOnClickListener(this);
    }

    private void addSupplier() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Processing...");
        progressDialog.show();

        String supplierName = supplierNameEditText.getText().toString().trim();
        String supplierAddress = supplierAddressEditText.getText().toString().trim();
        String supplierPhoneNo = supplierPhoneNumberEditText.getText().toString().trim();
        String supplierCompanyName = supplierCompanyNameEditText.getText().toString().trim();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<Result> call = service.addSupplier(supplierName, supplierAddress, supplierPhoneNo,
                supplierCompanyName);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                Toast.makeText(SupplierAddActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                supplierNameEditText.setText("");
                supplierAddressEditText.setText("");
                supplierPhoneNumberEditText.setText("");
                supplierCompanyNameEditText.setText("");
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(SupplierAddActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == addSupplierButton) {
            addSupplier();
            Intent intent = new Intent(SupplierAddActivity.this, HomeActivity.class);
            intent.putExtra("fragmentToTrigger", "SupplierList");
            startActivity(intent);
        }
    }
}
