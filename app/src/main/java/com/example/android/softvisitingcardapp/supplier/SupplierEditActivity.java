package com.example.android.softvisitingcardapp.supplier;

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
import com.example.android.softvisitingcardapp.feature.FeatureEditActivity;
import com.example.android.softvisitingcardapp.models.Feature;
import com.example.android.softvisitingcardapp.models.Result;
import com.example.android.softvisitingcardapp.models.Supplier;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SupplierEditActivity extends AppCompatActivity {
    private Button editSupplierButton;
    private EditText supplierNameEditText, supplierAddressEditText;
    private EditText supplierPhoneNumberEditText, supplierCompanyNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_edit);

        Bundle extras = getIntent().getExtras();
        if (extras == null)
        {
            return;
        }
        final int supplierId = extras.getInt("supplierId");
        final String supplierName = extras.getString("supplierName");
        final String supplierAddress = extras.getString("supplierAddress");
        final String supplierPhoneNo = extras.getString("supplierPhoneNo");
        final String supplierCompanyName = extras.getString("supplierCompanyName");

        initializeViews();

        supplierNameEditText.setText(supplierName);
        supplierAddressEditText.setText(supplierAddress);
        supplierPhoneNumberEditText.setText(supplierPhoneNo);
        supplierCompanyNameEditText.setText(supplierCompanyName);

        editSupplierButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editListItem(supplierId);
            }
        });
    }

    private void initializeViews() {
        editSupplierButton = findViewById(R.id.edit_supplier_button);

        supplierNameEditText = findViewById(R.id.supplier_name_edit_text);
        supplierAddressEditText = findViewById(R.id.supplier_address_edit_text);
        supplierPhoneNumberEditText = findViewById(R.id.supplier_phone_number_edit_text);
        supplierCompanyNameEditText = findViewById(R.id.supplier_company_name_edit_text);
    }

    public void editListItem(int supplierId){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        String updatedSupplierName = supplierNameEditText.getText().toString().trim();
        String updatedSupplierAddress = supplierAddressEditText.getText().toString().trim();
        String updatedSupplierPhoneNo = supplierPhoneNumberEditText.getText().toString().trim();
        String updatedSupplierCompanyName = supplierCompanyNameEditText.getText().toString().trim();


        Call<Result> call = service.editSupplier(supplierId, updatedSupplierName,
                            updatedSupplierAddress, updatedSupplierPhoneNo,
                            updatedSupplierCompanyName);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                // do something after editing is

                if (!response.body().getError()) {
                    Supplier supplier = response.body().getSupplier();
                    supplierNameEditText.setText(supplier.getName());
                    supplierAddressEditText.setText(supplier.getAddress());
                    supplierPhoneNumberEditText.setText(supplier.getPhoneNumber());
                    supplierCompanyNameEditText.setText(supplier.getCompanyName());

                    Toast.makeText(SupplierEditActivity.this, "Details Edit successful", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(SupplierEditActivity.this, HomeActivity.class);
                    intent.putExtra("fragmentToTrigger", "SupplierList");
                    startActivity(intent);
                } else {
                    Toast.makeText(SupplierEditActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                // show some message after failure
                Toast.makeText(SupplierEditActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
