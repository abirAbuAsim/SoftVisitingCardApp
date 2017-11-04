package com.example.android.softvisitingcardapp.api;



import com.example.android.softvisitingcardapp.ModelClass.CardSent;
import com.example.android.softvisitingcardapp.ModelClass.ResponseModel;
import com.example.android.softvisitingcardapp.gallery.Cards;
import com.example.android.softvisitingcardapp.models.MessageResponse;
import com.example.android.softvisitingcardapp.models.Messages;
import com.example.android.softvisitingcardapp.models.Result;
import com.example.android.softvisitingcardapp.models.Users;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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
    @POST("cardcreate")
    Call<Result> createCard(
            @Field("name") String name,
            @Field("email") String email,
            @Field("designation") String designation,
            @Field("contact") String contact,
            @Field("website") String website,
            @Field("address") String address,
            @Field("organization") String organization,
            @Field("background_image") String backgroundImage,
            @Field("logo_image") String logoImage,
            @Field("card_maker_email") String cardMakerEmail
    );



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

    @FormUrlEncoded
    @POST("updatecard/{id}")
    Call<Result> updateCard(
            @Path("id") int id,
            @Field("name") String name,
            @Field("email") String email,
            @Field("designation") String designation,
            @Field("contact") String contact,
            @Field("website") String website,
            @Field("address") String address,
            @Field("organization") String organization
    );

    @FormUrlEncoded
    @POST("deletecard")
    Call<Result> deleteCard(
            @Field("id") int id
    );

    @FormUrlEncoded
    @POST("removelinkeduser")
    Call<Result> removeLinkedUser(
            @Field("sender_id") String senderEmail,
            @Field("receiver_id") String receiverEmail
    );

    @FormUrlEncoded
    @POST("removesharedcard/{id}")
    Call<Result> removeSharedCard(
            @Path("id") int id,
            @Field("sender_id") String senderEmail,
            @Field("receiver_id") String receiverEmail
    );

    //getting messages
    @GET("messages/{id}")
    Call<Messages> getMessages(@Path("id") int id);

    // Gets the users who are linked with the user
    @GET("links/{email}")
    Call<Users> getLinks(@Path("email") String email);

    // get all the app users except the user him/her self
    @GET("users/{email}")
    Call<Users> getOtherUsers(@Path("email") String email);

    @GET("cards")
    Call<Cards> getCards();

    // Gets the cards which the user has created
    @GET("cards/{email}")
    Call<Cards> getAllMyCards(@Path("email") String email);

    // Gets the cards which the user has created
    @GET("sharedcards/{email}")
    Call<Cards> getSharedCards(@Path("email") String email);

    @FormUrlEncoded
    @POST("sendcard")
    Call<MessageResponse> sendCard(
            @Field("sender_id") String senderId,
            @Field("receiver_id") String receiverId,
            @Field("card_id") int cardId);

    @Multipart
    @POST("file_upload_api/card_create.php")
    Call<ResponseModel> fileUpload(
            @Part("sender_information") RequestBody description,
            @Part MultipartBody.Part file);

    @GET("/file_upload_api/display_card_data.php")
    Call<List<CardSent>> loadCards();
}
