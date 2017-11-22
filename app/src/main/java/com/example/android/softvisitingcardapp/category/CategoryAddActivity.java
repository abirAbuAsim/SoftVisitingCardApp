package com.example.android.softvisitingcardapp.category;

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

public class CategoryAddActivity extends AppCompatActivity implements View.OnClickListener {
    private Button addCategoryButton;
    private EditText categoryNameEditText, categoryDetailsEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_add);

        this.setTitle("Add Category");

        addCategoryButton = findViewById(R.id.add_category_button);


        categoryNameEditText = findViewById(R.id.category_name_edit_text);
        categoryDetailsEditText = (EditText) findViewById(R.id.category_details_edit_text);

        addCategoryButton.setOnClickListener(this);
    }

    private void addCategory() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Processing...");
        progressDialog.show();

        String categoryName = categoryNameEditText.getText().toString().trim();
        String categoryDetails = categoryDetailsEditText.getText().toString().trim();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<Result> call = service.addCategoryInfo(categoryName, categoryDetails);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                Toast.makeText(CategoryAddActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                categoryNameEditText.setText("");
                categoryDetailsEditText.setText("");
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CategoryAddActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == addCategoryButton) {
            addCategory();
            Intent intent = new Intent(CategoryAddActivity.this, HomeActivity.class);
            intent.putExtra("fragmentToTrigger", "CategoryList");
            startActivity(intent);
        }
    }
}
