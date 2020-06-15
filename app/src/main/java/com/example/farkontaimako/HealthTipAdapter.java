package com.example.farkontaimako;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class HealthTipAdapter extends RecyclerView.Adapter<HealthTipAdapter.ViewHolder> {

    private Activity activity;
    private List<HealthTipObject> healthTips;
    private Context context;

    public HealthTipAdapter(Activity activity, List<HealthTipObject> healthTips, Context context) {
        this.activity = activity;
        this.healthTips = healthTips;
        this.context = context;
    }

    @NonNull
    @Override
    public HealthTipAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HealthTipAdapter.ViewHolder holder, int position) {
        final HealthTipObject healthTipObject = healthTips.get(position);

        Picasso.get().load(healthTipObject.getImgURL()).into(holder.imgView);
        holder.tvDate.setText(healthTipObject.getTipTitle());
        holder.tvTitle.setText(healthTipObject.getExpertsNumber());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent healthIntent = new Intent(activity, HealthDetails.class);

                healthIntent.putExtra("title",healthTipObject.tipTitle);
                healthIntent.putExtra("phoneNumber", healthTipObject.expertsNumber);
                healthIntent.putExtra("img_url", healthTipObject.imgURL);
                healthIntent.putExtra("cure", healthTipObject.tipCure);
                healthIntent.putExtra("prevention", healthTipObject.tipPrevention);
                healthIntent.putExtra("symptoms", healthTipObject.tipSymptoms);

                healthIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(healthIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return healthTips.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        RelativeLayout relativeLayout;
        TextView tvTitle, tvDate;
        ImageView imgView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            relativeLayout = itemView.findViewById(R.id.reLay);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            imgView = itemView.findViewById(R.id.imgFirstAid);
        }
    }
}
