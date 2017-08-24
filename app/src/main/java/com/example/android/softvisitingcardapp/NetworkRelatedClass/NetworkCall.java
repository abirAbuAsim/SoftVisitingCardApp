package com.example.android.softvisitingcardapp.NetworkRelatedClass;

import android.support.annotation.NonNull;

import com.example.android.softvisitingcardapp.ModelClass.CardSent;
import com.example.android.softvisitingcardapp.ModelClass.EventModel;
import com.example.android.softvisitingcardapp.ModelClass.ResponseModel;
import com.google.gson.Gson;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;


import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkCall {

    public static void fileUpload(String filePath, CardSent card) {

        ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
        Logger.addLogAdapter(new AndroidLogAdapter());

        File file = new File(filePath);
        //create RequestBody instance from file
        RequestBody requestFile = RequestBody.create(MediaType.parse("image"), file);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        Gson gson = new Gson();
        String cardData = gson.toJson(card);

        RequestBody description = RequestBody.create(okhttp3.MultipartBody.FORM, cardData);

        // finally, execute the request
        Call<ResponseModel> call = apiInterface.fileUpload(description, body);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
                Logger.d("Response: " + response);

                ResponseModel responseModel = response.body();

                if(responseModel != null){
                    EventBus.getDefault().post(new EventModel("response", responseModel.getMessage()));
                    Logger.d("Response code " + response.code() +
                            " Response Message: " + responseModel.getMessage());
                } else
                    EventBus.getDefault().post(new EventModel("response", "ResponseModel is NULL"));
            }

            @Override
            public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable t) {
                Logger.d("Exception: " + t);
                EventBus.getDefault().post(new EventModel("response", t.getMessage()));
            }
        });
    }

    public static void displayInfo() {

        ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
        Logger.addLogAdapter(new AndroidLogAdapter());

        Call<List<CardSent>> listBooksCall = apiInterface.loadCards();
        listBooksCall.enqueue(new Callback<List<CardSent>>() {
            @Override
            public void onResponse(Call<List<CardSent>> call, Response<List<CardSent>> response) {
                Logger.d("Response: " + response);
                if (response.isSuccessful()) {
                    List<CardSent> books = response.body();
                    EventBus.getDefault().post(new EventModel("response", books.toArray().toString()));
                    Logger.d("Response code " + response.code() +
                            " Response Message: " + books.toArray().toString());
                } else {
                    EventBus.getDefault().post(new EventModel("response", "ResponseModel is NULL"));
                }
            }

            @Override
            public void onFailure(Call<List<CardSent>> call, Throwable t) {
                Logger.d("Exception: " + t);
                EventBus.getDefault().post(new EventModel("response", t.getMessage()));
            }
        });

    }

}
