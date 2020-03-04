package com.example.farkontaimako;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddFirstAidTip extends AppCompatActivity {

    DateFormat dateFormatWithTime = new SimpleDateFormat("dd-mm-yy HH:mm:ss");
    Date date = new Date();
    final int REQ_CODE = 101;

    FirebaseStorage fireStore = FirebaseStorage.getInstance();
    StorageReference stoRef = fireStore.getReference("restaurants"+dateFormatWithTime.format(date));
    FirebaseDatabase fireDB;
    FirebaseAuth fireAuth;

    Uri locLink;
    String locUri, strImgUrl;


    EditText tipTitle, tipSymptoms, tipPrevention, tipCure, expertsNumber;
    ImageView imgURL;

    Button btnImgUpload, btnPostUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_first_aid_tip);

        tipTitle = findViewById(R.id.edTipTitle);
        tipSymptoms = findViewById(R.id.edTipSymptoms);
        tipCure = findViewById(R.id.edTipCure);
        tipPrevention = findViewById(R.id.edTipPrevention);
        expertsNumber = findViewById(R.id.edTipExpertNumber);

        btnImgUpload = findViewById(R.id.btnImg);
        btnPostUpload = findViewById(R.id.btnPostTip);

        imgURL = findViewById(R.id.imgFirstAid);

        btnImgUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadFileIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                loadFileIntent.addCategory(Intent.CATEGORY_OPENABLE);
                loadFileIntent.setType("image/*");
                startActivityForResult(loadFileIntent, REQ_CODE);
            }
        });
        btnPostUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(locUri != null)
                {
                    /*stoRef.putFile(locLink).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            strImgUrl = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();

                        }});*/

                    Task<Uri> tContinuationResultTask = (Task<Uri>) stoRef.putFile(locLink).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if (!task.isSuccessful()) {
                                throw task.getException();
                            }

                            // Continue with the task to get the download URL
                            //strImgUrl = stoRef.getDownloadUrl().toString();
                            return stoRef.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(Task<Uri> task) {
                            if (task.isSuccessful()) {
                                Uri downloadUri = task.getResult();
                                strImgUrl = downloadUri.toString();
                                Log.i("downloadUri", downloadUri.toString());
                                addNewTipItem();
                            } else {
                                // Handle failures
                                // ...
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQ_CODE && resultCode == Activity.RESULT_OK)
        {
            Uri uri = null;
            if(data != null)
            {
                uri = data.getData();
                locLink = uri;
                locUri = uri.toString();

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), locLink);
                    imgURL.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void addNewTipItem()
    {
        String strTipTitle = tipTitle.getText().toString().trim();
        String strTipSymptoms = tipSymptoms.getText().toString().trim();
        String strTipPrevention = tipPrevention.getText().toString().trim();
        String strTipCure = tipCure.getText().toString().trim();
        String strExpertsNumber = expertsNumber.getText().toString().trim();

        String pushKey = fireDB.getReference().push().getKey();
        HealthTipObject healthTipObject = new HealthTipObject(
                strImgUrl,
                strTipTitle,
                strTipSymptoms,
                strTipPrevention,
                strTipCure,
                strExpertsNumber);

        fireDB.getReference("HealthTips").child(pushKey).setValue(healthTipObject);
    }
}
