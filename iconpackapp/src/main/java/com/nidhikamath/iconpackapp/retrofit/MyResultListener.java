package com.nidhikamath.iconpackapp.retrofit;

import com.nidhikamath.iconpackapp.model.Images;

import java.util.ArrayList;

public interface MyResultListener {
    void onSuccess(ArrayList<Images> images);

    void onFailed();
}
