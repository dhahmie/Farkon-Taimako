package com.example.farkontaimako;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewAidList extends AppCompatActivity {

    FirebaseDatabase fireDB = FirebaseDatabase.getInstance();
    DatabaseReference dbRef = fireDB.getReference("HealthTips");
    List<HealthTipObject> healthTipObjects;
    RecyclerView recyclerView;
    HealthTipAdapter healthTipAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_aid_list);

        //fireDB.setPersistenceEnabled(true);
        Button btnAddTip = findViewById(R.id.btnAddTip);

        if (getIntent().getStringExtra("loginStatus").equals("") || getIntent().getStringExtra("loginStatus") == null)
        {
            btnAddTip.setVisibility(View.GONE);
        }

        btnAddTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewAidList.this, AddFirstAidTip.class));
            }
        });

        healthTipObjects = new ArrayList<>();
        healthTipAdapter = new HealthTipAdapter(this, healthTipObjects, getApplicationContext());
        recyclerView = findViewById(R.id.recycle_health_tips);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnap : dataSnapshot.getChildren())
                {
                    HealthTipObject healthTipObject = postSnap.getValue(HealthTipObject.class);
                    if(healthTipObject !=  null)
                    {
                        healthTipObjects.add(healthTipObject);
                    }
                }
                recyclerView.setAdapter(healthTipAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
