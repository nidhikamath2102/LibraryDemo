package com.nidhikamath.iconpackapp.listeners;

import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.SlidingDrawer;

import com.nidhikamath.iconpackapp.MainActivity;
import com.nidhikamath.iconpackapp.utilities.AppSerializableData;
import com.nidhikamath.iconpackapp.utilities.HomeView;
import com.nidhikamath.iconpackapp.utilities.Pac;
import com.nidhikamath.iconpackapp.utilities.SerializationTools;

import java.util.ArrayList;

public class DrawerLongClickListener implements OnItemLongClickListener {

    Context mContext;
    SlidingDrawer drawerForAdapter;
    HomeView homeViewForAdapter;
    Pac[] pacsForListener;

    public DrawerLongClickListener(Context ctxt, SlidingDrawer slidingDrawer, HomeView homeView, Pac[] pacs) {
        mContext = ctxt;
        drawerForAdapter = slidingDrawer;
        homeViewForAdapter = homeView;
        pacsForListener = pacs;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> arg0, View item, int pos,
                                   long arg3) {
        MainActivity.appLaunchable = false;

        AppSerializableData objectData = SerializationTools.loadSerializedData();
        if (objectData == null)
            objectData = new AppSerializableData();

        if (objectData.apps == null)
            objectData.apps = new ArrayList<Pac>();

        Pac pacToAdd = pacsForListener[pos];
        pacToAdd.x = (int) item.getX();
        pacToAdd.y = (int) item.getY();
        pacToAdd.lanscape = MainActivity.activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        pacToAdd.cacheIcon();
        objectData.apps.add(pacToAdd);
        SerializationTools.serializeData(objectData);

        pacToAdd.addToHome(mContext, homeViewForAdapter);
        drawerForAdapter.animateClose();
        drawerForAdapter.bringToFront();
        return false;
    }

}
