package com.nidhikamath.iconpackapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nidhikamath.iconpackapp.R;
import com.nidhikamath.iconpackapp.model.Images;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private final Context context;
    private ArrayList<Images> images = new ArrayList<>();

    public MainAdapter(Context context, ArrayList<Images> images) {
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {
        holder.title.setText(images.get(i).title);
        Log.d("image name ", images.get(i).title);
        Picasso.get().load(images.get(i).imageUrl).error(R.mipmap.ic_launcher_round).into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*context.getPackageManager().setComponentEnabledSetting(((MainActivity)context).getComponentName(),
                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);

                context.getPackageManager().setComponentEnabledSetting(
                        new ComponentName("com.nidhikamath.iconpackapp", "com.nidhikamath.iconpackapp.MainTwo"),
                        PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);*/


                //((MainActivity)context).setImageAsWallpaper(images.get(i).imageUrl);
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            image = itemView.findViewById(R.id.image);
        }
    }

}
