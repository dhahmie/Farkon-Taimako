package com.example.farkontaimako;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class HealthDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_details);

        TextView tvTitle = findViewById(R.id.tvDTitle);
        TextView tvPhone = findViewById(R.id.tvDPhone);
        TextView tvCure = findViewById(R.id.tvDCure);
        TextView tvPrevention = findViewById(R.id.tvDPrevention);
        TextView tvSymptoms = findViewById(R.id.tvDSymptoms);
        ImageView imgAid = findViewById(R.id.imgAidIcon);

        tvTitle.setText(getIntent().getStringExtra("title"));
        Picasso.get().load(getIntent().getStringExtra("img_url")).into(imgAid);
        tvPhone.setText(getIntent().getStringExtra("phoneNumber"));
        tvCure.setText(getIntent().getStringExtra("cure"));
        tvPrevention.setText(getIntent().getStringExtra("prevention"));
        tvSymptoms.setText(getIntent().getStringExtra("symptoms"));
    }
}
