package com.example.android.softvisitingcardapp.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.softvisitingcardapp.R;
import com.example.android.softvisitingcardapp.api.APIService;
import com.example.android.softvisitingcardapp.api.APIUrl;
import com.example.android.softvisitingcardapp.models.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BrandFragment extends Fragment implements View.OnClickListener {

    private Button buttonAddBrand;
    private EditText editTextBrandName, editTextBrandDetails;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_brand, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Add Brand");

        buttonAddBrand = (Button) view.findViewById(R.id.add_brand_button);

        editTextBrandName = (EditText) view.findViewById(R.id.brand_name_edit_text);
        editTextBrandDetails = (EditText) view.findViewById(R.id.brand_details_edit_text);

        buttonAddBrand.setOnClickListener(this);
    }

    private void addBrandInfo() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
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
                Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                editTextBrandName.setText("");
                editTextBrandDetails.setText("");
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == buttonAddBrand) {
            addBrandInfo();
        }
    }
}
