package com.si6a.wisataindonesia.api;

import com.si6a.wisataindonesia.Utilities.Utilities;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;

    public static APIService getInstance(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Utilities.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(APIService.class);
    }
}
