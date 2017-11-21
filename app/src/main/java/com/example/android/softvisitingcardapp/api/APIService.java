package com.example.android.softvisitingcardapp.api;

import com.example.android.softvisitingcardapp.models.Brands;
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
    @POST("login")
    Call<Result> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("addbrand")
    Call<Result> addBrandInfo(
            @Field("brand_name") String brandName,
            @Field("brand_details") String brandDetails
    );

    @FormUrlEncoded
    @POST("editbrand")
    Call<Result> editBrand(
            @Field("brand_id") int id,
            @Field("brand_name") String name,
            @Field("brand_details") String details
    );

    @FormUrlEncoded
    @POST("deletebrand")
    Call<Result> deleteBrand(
            @Field("brand_id") int id
    );

    @GET("users")
    Call<Users> getUsers();

    @GET("brands")
    Call<Brands> getBrands();

    @FormUrlEncoded
    @POST("update/{id}")
    Call<Result> updateUser(
            @Path("id") int id,
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password
    );
}
