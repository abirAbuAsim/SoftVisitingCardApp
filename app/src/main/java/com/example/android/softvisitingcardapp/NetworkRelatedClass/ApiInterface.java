package com.example.android.softvisitingcardapp.NetworkRelatedClass;



import com.example.android.softvisitingcardapp.ModelClass.CardSent;
import com.example.android.softvisitingcardapp.ModelClass.ResponseModel;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface ApiInterface {

    @Multipart
    @POST("file_upload_api/upload.php")
    Call<ResponseModel> fileUpload(
            @Part("sender_information") RequestBody description,
            @Part MultipartBody.Part file);

    @GET("file_upload_api/display_card_data.php")
    Call<List<CardSent>> loadCards();



}
