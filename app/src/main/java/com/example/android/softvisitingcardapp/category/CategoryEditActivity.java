package com.example.android.softvisitingcardapp.category;

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
import com.example.android.softvisitingcardapp.brand.BrandEditActivity;
import com.example.android.softvisitingcardapp.models.Brand;
import com.example.android.softvisitingcardapp.models.Category;
import com.example.android.softvisitingcardapp.models.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryEditActivity extends AppCompatActivity {
    private Button editCategoryButton;
    private EditText categoryNameEditText, categoryDetailsEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_edit);

        Bundle extras = getIntent().getExtras();
        if (extras == null)
        {
            return;
        }
        final int categoryId = extras.getInt("categoryId");
        final String categoryName = extras.getString("categoryName");
        final String categoryDetails = extras.getString("categoryDetails");

        editCategoryButton = findViewById(R.id.edit_category_button);

        categoryNameEditText = (EditText) findViewById(R.id.category_name_edit_text);
        categoryNameEditText.setText(categoryName);
        categoryDetailsEditText = (EditText) findViewById(R.id.category_details_edit_text);
        categoryDetailsEditText.setText(categoryDetails);

        editCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editListItem(categoryId);
            }
        });
    }

    public void editListItem(int categoryId){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        String updatedCategoryName = categoryNameEditText.getText().toString().trim();
        String updatedCategoryDetails = categoryDetailsEditText.getText().toString().trim();

        Call<Result> call = service.editCategory(categoryId, updatedCategoryName, updatedCategoryDetails);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                // do something after editing is

                if (!response.body().getError()) {
                    Category category = response.body().getCategory();
                    categoryNameEditText.setText(category.getName());
                    categoryDetailsEditText.setText(category.getDetails());
                    Toast.makeText(CategoryEditActivity.this, "Details Edit successful", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(CategoryEditActivity.this, HomeActivity.class);
                    intent.putExtra("fragmentToTrigger", "CategoryList");
                    startActivity(intent);
                } else {
                    Toast.makeText(CategoryEditActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                // show some message after failure
                Toast.makeText(CategoryEditActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
