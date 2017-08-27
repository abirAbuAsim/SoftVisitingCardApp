package com.example.android.softvisitingcardapp.authentication;



import com.example.android.softvisitingcardapp.models.ServerRequest;
import com.example.android.softvisitingcardapp.models.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RequestInterface {
    @POST("/SoftVisitingCardWeb/android/user_index.php")
    Call<ServerResponse> operation(@Body ServerRequest request);
}