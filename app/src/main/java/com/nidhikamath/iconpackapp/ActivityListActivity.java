package com.nidhikamath.iconpackapp;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import com.nidhikamath.iconpackapp.adapter.ActivityAdapter;
import com.nidhikamath.iconpackapp.model.ActivityDetail;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ActivityListActivity extends AppCompatActivity {

    private final int counter = 0;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ActivityAdapter activityAdapter;
    private List<ActivityDetail> activityDetails = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = findViewById(R.id.recyclerView);


        layoutManager = new GridLayoutManager(ActivityListActivity.this, 5);
        recyclerView.setLayoutManager(layoutManager);

        activityDetails = getInstalledApps1();
        activityAdapter = new ActivityAdapter(ActivityListActivity.this, activityDetails);
        recyclerView.setAdapter(activityAdapter);

    }

    private List<ActivityDetail> getInstalledApps() {
        List<ActivityDetail> apps = new ArrayList<ActivityDetail>();
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);

        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            if ((!isSystemPackage(p))) {
                String appName = p.applicationInfo.loadLabel(getPackageManager()).toString();
                Drawable icon = p.applicationInfo.loadIcon(getPackageManager());
                String packages = p.applicationInfo.packageName;
                apps.add(new ActivityDetail(appName, icon, packages));
            }
        }
        return apps;
    }

    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return (pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
    }

    public List<ActivityDetail> getInstalledApps1() {
        final PackageManager packageManager = getPackageManager();

        final List<PackageInfo> allInstalledPackages = packageManager.getInstalledPackages(PackageManager.GET_META_DATA);
        final List<ActivityDetail> filteredPackages = new ArrayList<>();

        Drawable defaultActivityIcon = packageManager.getDefaultActivityIcon();

        for (PackageInfo each : allInstalledPackages) {
            if (getPackageName().equals(each.packageName)) {
                continue;  // skip own app
            }

            try {
                // add only apps with application icon
                Intent intentOfStartActivity = packageManager.getLaunchIntentForPackage(each.packageName);
                if (intentOfStartActivity == null)
                    continue;

                Drawable applicationIcon = packageManager.getActivityIcon(intentOfStartActivity);
                if (applicationIcon != null && !defaultActivityIcon.equals(applicationIcon)) {
                    String appName = each.applicationInfo.loadLabel(getPackageManager()).toString();
                    Drawable icon = each.applicationInfo.loadIcon(getPackageManager());
                    String packages = each.applicationInfo.packageName;
                    filteredPackages.add(new ActivityDetail(appName, icon, packages));
                }
            } catch (PackageManager.NameNotFoundException e) {
                Log.i("MyTag", "Unknown package name " + each.packageName);
            }
        }

        return filteredPackages;
    }
}