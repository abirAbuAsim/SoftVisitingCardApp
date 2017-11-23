package com.example.android.softvisitingcardapp.api;

import com.example.android.softvisitingcardapp.models.Brands;
import com.example.android.softvisitingcardapp.models.Categories;
import com.example.android.softvisitingcardapp.models.Features;
import com.example.android.softvisitingcardapp.models.MessageResponse;
import com.example.android.softvisitingcardapp.models.Messages;
import com.example.android.softvisitingcardapp.models.Result;
import com.example.android.softvisitingcardapp.models.Suppliers;
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

    /*
        --> Start: brand
     */
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

    @GET("brands")
    Call<Brands> getBrands();

    /*
        --> Start: supplier
    */
    @FormUrlEncoded
    @POST("addsupplier")
    Call<Result> addSupplier(
            @Field("supplier_name") String supplierName,
            @Field("supplier_address") String supplierAddress,
            @Field("supplier_phone_no") String supplierPhoneNo,
            @Field("supplier_company_name") String supplierCompanyName
    );

    @FormUrlEncoded
    @POST("editsupplier")
    Call<Result> editSupplier(
            @Field("supplier_id") int supplierId,
            @Field("supplier_name") String supplierName,
            @Field("supplier_address") String supplierAddress,
            @Field("supplier_phone_no") String supplierPhoneNo,
            @Field("supplier_company_name") String supplierCompanyName
    );

    @FormUrlEncoded
    @POST("suppliers")
    Call<Result> deleteSupplier(
            @Field("supplier_id") int supplierId
    );

    @GET("suppliers")
    Call<Suppliers> getAllSuppliers();

    /*
        End: supplier <--
    */

    /*
        End: brand <--
    */

    /*
        --> Start: category
    */
    @FormUrlEncoded
    @POST("addcategory")
    Call<Result> addCategoryInfo(
            @Field("category_name") String categoryName,
            @Field("category_details") String categoryDetails
    );

    @FormUrlEncoded
    @POST("editcategory")
    Call<Result> editCategory(
            @Field("category_id") int categoryId,
            @Field("category_name") String categoryName,
            @Field("category_details") String categoryDetails
    );

    @FormUrlEncoded
    @POST("deletecategory")
    Call<Result> deleteCategory(
            @Field("category_id") int categoryId
    );

    @GET("categories")
    Call<Categories> getAllCategories();

    /*
        End: category <--
    */

    /*
       --> Start: feature
   */
    @FormUrlEncoded
    @POST("addfeature")
    Call<Result> addFeatureInfo(
            @Field("feature_name") String featureName,
            @Field("feature_ram") String featureRam,
            @Field("feature_rom") String featureRom,
            @Field("feature_processor") String featureProcessor,
            @Field("feature_os") String featureOs,
            @Field("feature_front_camera") String featureFrontCamera,
            @Field("feature_back_camera") String featureBackCamera,
            @Field("feature_battery") String featureBattery,
            @Field("feature_cpu") String featureCpu,
            @Field("feature_gpu") String featureGpu,
            @Field("feature_sim") String featureSim,
            @Field("feature_screen") String featureScreen,
            @Field("feature_warranty") String featureWarranty
    );

    @FormUrlEncoded
    @POST("editfeature")
    Call<Result> editFeature(
            @Field("feature_id") int featureId,
            @Field("feature_name") String featureName,
            @Field("feature_ram") String featureRam,
            @Field("feature_rom") String featureRom,
            @Field("feature_processor") String featureProcessor,
            @Field("feature_os") String featureOs,
            @Field("feature_front_camera") String featureFrontCamera,
            @Field("feature_back_camera") String featureBackCamera,
            @Field("feature_battery") String featureBattery,
            @Field("feature_cpu") String featureCpu,
            @Field("feature_gpu") String featureGpu,
            @Field("feature_sim") String featureSim,
            @Field("feature_screen") String featureScreen,
            @Field("feature_warranty") String featureWarranty
    );

    @FormUrlEncoded
    @POST("deletefeature")
    Call<Result> deleteFeature(
            @Field("feature_id") int featureId
    );

    @GET("features")
    Call<Features> getAllFeatures();

    /*
        End: feature <--
    */

    @GET("users")
    Call<Users> getUsers();


    @FormUrlEncoded
    @POST("update/{id}")
    Call<Result> updateUser(
            @Path("id") int id,
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password
    );
}
