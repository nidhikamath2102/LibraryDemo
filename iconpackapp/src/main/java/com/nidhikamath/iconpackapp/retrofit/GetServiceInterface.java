package com.nidhikamath.iconpackapp.retrofit;

import com.nidhikamath.iconpackapp.model.Images;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetServiceInterface {

    @GET("cgcRhuNBDS?indent=2")
    Call<ArrayList<Images>>
    getImages();

}
