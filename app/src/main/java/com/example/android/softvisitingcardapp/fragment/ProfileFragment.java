package com.example.android.softvisitingcardapp.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.example.android.softvisitingcardapp.R;
import com.example.android.softvisitingcardapp.api.APIService;
import com.example.android.softvisitingcardapp.api.APIUrl;
import com.example.android.softvisitingcardapp.helper.SharedPrefManager;
import com.example.android.softvisitingcardapp.models.Result;
import com.example.android.softvisitingcardapp.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Belal on 14/04/17.
 */

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private Button buttonUpdate;
    private EditText editTextName, editTextEmail, editTextPassword;
    private RadioGroup radioGender;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Profile");

        buttonUpdate = (Button) view.findViewById(R.id.buttonUpdate);

        editTextName = (EditText) view.findViewById(R.id.editTextName);
        editTextEmail = (EditText) view.findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) view.findViewById(R.id.editTextPassword);



        buttonUpdate.setOnClickListener(this);

        User user = SharedPrefManager.getInstance(getActivity()).getUser();

        editTextName.setText(user.getName());
        editTextEmail.setText(user.getEmail());
        editTextPassword.setText("0000");



    }

    private void updateUser() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Updating...");
        progressDialog.show();


        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        User user = new User(SharedPrefManager.getInstance(getActivity()).getUser().getId(), name, email, password);

        Call<Result> call = service.updateUser(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword()
        );

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                if (!response.body().getError()) {
                    SharedPrefManager.getInstance(getActivity()).userLogin(response.body().getUser());
                }
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
        if (view == buttonUpdate) {
            updateUser();
        }
    }
}
