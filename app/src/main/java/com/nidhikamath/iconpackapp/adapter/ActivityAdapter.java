package com.nidhikamath.iconpackapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nidhikamath.iconpackapp.R;
import com.nidhikamath.iconpackapp.model.ActivityDetail;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ViewHolder> {

    private final Context context;
    private List<ActivityDetail> activityDetails = new ArrayList<>();

    public ActivityAdapter(Context context, List<ActivityDetail> activityDetails) {
        this.context = context;
        this.activityDetails = activityDetails;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {
        holder.title.setText(activityDetails.get(i).getName());
        holder.image.setImageDrawable(activityDetails.get(i).getIcon());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*CandyBarApplication.Configuration configuration = new CandyBarApplication.Configuration();
                CandyBarApplication.OtherApp[] otherApps = new CandyBarApplication.OtherApp[] {
                        new CandyBarApplication.OtherApp(
                                //You can use png file (without extension) inside drawable-nodpi folder or url
                                "https://avatars1.githubusercontent.com/u/23138905?v=3&amp;s=300",
                                activityDetails.get(i).getName(),
                                "",
                                "https://play.google.com/store/apps/details?id="+activityDetails.get(i).getPackages())
                };
                configuration.setOtherApps(otherApps);*/
                Log.d("package name ", "https://play.google.com/store/apps/details?id=" + activityDetails.get(i).getPackages());
            }
        });
    }

    @Override
    public int getItemCount() {
        return activityDetails.size();
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
