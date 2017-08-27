package com.example.android.softvisitingcardapp.api;



import com.example.android.softvisitingcardapp.models.MessageResponse;
import com.example.android.softvisitingcardapp.models.Messages;
import com.example.android.softvisitingcardapp.models.Result;
import com.example.android.softvisitingcardapp.models.Users;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Belal on 14/04/17.
 */

public interface APIService {

    @FormUrlEncoded
    @POST("register")
    Call<Result> createUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password);


    @FormUrlEncoded
    @POST("login")
    Call<Result> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );


    @GET("users")
    Call<Users> getUsers();

    @FormUrlEncoded
    @POST("sendmessage")
    Call<MessageResponse> sendMessage(
            @Field("from") int from,
            @Field("to") int to,
            @Field("title") String title,
            @Field("message") String message);

    @FormUrlEncoded
    @POST("update/{id}")
    Call<Result> updateUser(
            @Path("id") int id,
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password
    );

    //getting messages
    @GET("messages/{id}")
    Call<Messages> getMessages(@Path("id") int id);
}
