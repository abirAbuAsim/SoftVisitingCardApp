package com.example.android.softvisitingcardapp.gallery;



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


/*

    @GET("users")
    Call<Users> getUsers();
*/

    @GET("cards")
    Call<Cards> getCards();

    @FormUrlEncoded
    @POST("sendmessage")
    Call<MessageResponse> sendMessage(
            @Field("from") int from,
            @Field("to") int to,
            @Field("title") String title,
            @Field("message") String message);


/*
    //getting messages
    @GET("messages/{id}")
    Call<Messages> getMessages(@Path("id") int id);*/
}
