package com.si6a.wisataindonesia.api;

import com.si6a.wisataindonesia.model.ResponseData;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIService {

    @POST("/api/register")
    Call<ResponseData> register(@Body RequestBody requestBody);

    @POST("/api/login")
    Call<ResponseData> login(@Body RequestBody requestBody);

    /**
     * Wisata API
     */

    @GET("/api/travel")
    Call<ResponseData> fetchAllTravels();

    @POST("/api/travel")
    Call<ResponseData> storeTravel(@Body RequestBody requestBody);

    @PUT("/api/travel/{id}")
    Call<ResponseData> updateTravel(@Path("id") String id, @Body RequestBody requestBody);

    @DELETE("/api/travel/{id}")
    Call<ResponseData> deleteTravel(@Path("id") String id);

}
