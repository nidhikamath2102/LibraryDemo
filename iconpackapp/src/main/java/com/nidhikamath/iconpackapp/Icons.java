package com.nidhikamath.iconpackapp;

import android.util.Log;

import com.nidhikamath.iconpackapp.model.Images;
import com.nidhikamath.iconpackapp.retrofit.GetServiceInterface;
import com.nidhikamath.iconpackapp.retrofit.MyResultListener;
import com.nidhikamath.iconpackapp.retrofit.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Icons {
    private static ArrayList<Images> images = new ArrayList<>();

    public static void GetImages(String url, final MyResultListener listener) {
        Retrofit retrofit = RetrofitClient.getRetrofit(url);
        GetServiceInterface getServiceInterface = retrofit.create(GetServiceInterface.class);
        Call<ArrayList<Images>> call = getServiceInterface.getImages();

        call.enqueue(new Callback<ArrayList<Images>>() {
            @Override
            public void onResponse(Call<ArrayList<Images>> call, Response<ArrayList<Images>> response) {
                //if (response.isSuccessful()) {
                images = response.body();
                listener.onSuccess(images);
                Log.d("images icons ", images.size() + "");

                //}
            }

            @Override
            public void onFailure(Call<ArrayList<Images>> call, Throwable t) {
                listener.onFailed();
            }
        });


    }

}
