package com.nidhikamath.iconpackapp.model;

import android.graphics.drawable.Drawable;

public class ActivityDetail {
    private final String name;
    private final String packages;
    Drawable icon;

    public ActivityDetail(String name, Drawable icon, String packages) {
        this.name = name;
        this.icon = icon;
        this.packages = packages;
    }

    public String getName() {
        return name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getPackages() {
        return packages;
    }

}
