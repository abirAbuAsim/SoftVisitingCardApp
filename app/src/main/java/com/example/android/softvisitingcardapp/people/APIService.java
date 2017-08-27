package com.example.android.softvisitingcardapp.people;



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
            @Field("password") String password,
            @Field("gender") String gender);


    @FormUrlEncoded
    @POST("login")
    Call<Result> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );


    @GET("users")
    Call<Users> getUsers();

    // get all the app users except the user him/her self
    @GET("users/{email}")
    Call<Users> getOtherUsers(@Path("email") String email);

    // Gets the users who are linked with the user
    @GET("links/{email}")
    Call<Users> getLinks(@Path("email") String email);

    @FormUrlEncoded
    @POST("sendmessage")
    Call<MessageResponse> sendMessage(
            @Field("from") int from,
            @Field("to") int to,
            @Field("title") String title,
            @Field("message") String message);

    @FormUrlEncoded
    @POST("sendcard")
    Call<MessageResponse> sendCard(
            @Field("sender_id") String senderId,
            @Field("receiver_id") String receiverId,
            @Field("card_id") int cardId);

    @FormUrlEncoded
    @POST("update/{id}")
    Call<Result> updateUser(
            @Path("id") int id,
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("gender") String gender
    );

   /* //getting messages
    @GET("messages/{id}")
    Call<Messages> getMessages(@Path("id") int id);*/
}
