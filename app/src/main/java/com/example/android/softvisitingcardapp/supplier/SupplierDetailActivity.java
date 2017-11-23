package com.example.android.softvisitingcardapp.supplier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.softvisitingcardapp.R;
import com.example.android.softvisitingcardapp.brand.BrandDetailActivity;
import com.example.android.softvisitingcardapp.brand.BrandEditActivity;

public class SupplierDetailActivity extends AppCompatActivity {
    private TextView supplierNameTextView, supplierAddressTextView;
    private TextView supplierPhoneNumberTextView, supplierCompanyNameTextView;

    private Button editSupplierButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_detail);

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

        setDataInViews(supplierName, supplierAddress, supplierPhoneNo, supplierCompanyName);

        editSupplierButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SupplierDetailActivity.this, SupplierEditActivity.class);
                intent.putExtra("supplierId", supplierId);
                intent.putExtra("supplierName", supplierName);
                intent.putExtra("supplierAddress", supplierAddress);
                intent.putExtra("supplierPhoneNo", supplierPhoneNo);
                intent.putExtra("supplierCompanyName", supplierCompanyName);
                startActivity(intent);
            }
        });
    }

    private void initializeViews() {
        editSupplierButton = findViewById(R.id.edit_button);

        supplierNameTextView = findViewById(R.id.supplier_name_text_view);
        supplierAddressTextView = findViewById(R.id.supplier_address_text_view);
        supplierPhoneNumberTextView = findViewById(R.id.supplier_phone_number_text_view);
        supplierCompanyNameTextView = findViewById(R.id.supplier_company_name_text_view);
    }


    private void setDataInViews(String supplierName, String supplierAddress,
                                String supplierPhoneNo, String supplierCompanyName) {

        supplierNameTextView.setText(supplierName);
        supplierAddressTextView.setText(supplierAddress);
        supplierPhoneNumberTextView.setText(supplierPhoneNo);
        supplierCompanyNameTextView.setText(supplierCompanyName);
    }
}
