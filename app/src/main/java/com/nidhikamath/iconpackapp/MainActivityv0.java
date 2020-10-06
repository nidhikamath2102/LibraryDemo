package com.nidhikamath.iconpackapp;


import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.ehom.iconpackapp.Icons;
import com.ehom.iconpackapp.model.Images;
import com.ehom.iconpackapp.retrofit.MyResultListener;
import com.nidhikamath.iconpackapp.adapter.MainAdapter;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivityv0 extends AppCompatActivity {

    private ImageView activity;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MainAdapter mainAdapter;
    private ArrayList<Images> imagesArr = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainv0);

        recyclerView = findViewById(R.id.recyclerView);
        activity = findViewById(R.id.activity);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);


        Icons.GetImages("http://www.json-generator.com/api/json/get/", new MyResultListener() {
            @Override
            public void onSuccess(ArrayList<Images> images) {
                Log.d("images ", images.size() + "");
                imagesArr = images;
                mainAdapter = new MainAdapter(MainActivityv0.this, imagesArr);
                recyclerView.setAdapter(mainAdapter);
                mainAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed() {

            }
        });

        activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivityv0.this, ActivityListActivity.class));
            }
        });

    }

    public void setImageAsWallpaper(final String imageUrl) {

        Picasso.get().load(imageUrl).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                try {

                    WallpaperManager wallpaperManager = WallpaperManager.getInstance(MainActivityv0.this);
                    wallpaperManager.setBitmap(bitmap);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        });
    }
}

/*
import android.os.Bundle;

import com.dm.material.dashboard.candybar.activities.CandyBarMainActivity;
import com.dm.material.dashboard.candybar.activities.configurations.ActivityConfiguration;
import com.nidhikamath.iconpackapp.licenses.License;

import androidx.annotation.Nullable;

public class MainActivity extends CandyBarMainActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainv0);
    }

    @Override
    public ActivityConfiguration onInit() {
        return new ActivityConfiguration()
                .setLicenseCheckerEnabled(License.isLicenseCheckerEnabled())
                .setLicenseKey(License.getLicenseKey())
                .setRandomString(License.getRandomString())
                .setDonationProductsId(License.getDonationProductsId())
                .setPremiumRequestProducts(License.getPremiumRequestProductsId(), License.getPremiumRequestProductsCount());
    }
}
*/