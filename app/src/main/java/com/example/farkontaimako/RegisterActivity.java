package com.example.farkontaimako;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {


    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseDatabase fbDB = FirebaseDatabase.getInstance();
    Button btnSignUp;
    EditText edFname, edLName, edPhone, edPass, edMail, edHealthFac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnSignUp = findViewById(R.id.btnSignUoAdmin);

        edFname = findViewById(R.id.edfirstname);
        edLName = findViewById(R.id.edlastname);
        edPhone = findViewById(R.id.edmobilenumber);
        edPass = findViewById(R.id.edpassword);
        edHealthFac = findViewById(R.id.edhealthfacility);
        edMail = findViewById(R.id.edMail);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.createUserWithEmailAndPassword(edMail.getText().toString(),edPass.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                AdminUser user = new AdminUser(
                                        edFname.getText().toString(),
                                        edLName.getText().toString(),
                                        edPhone.getText().toString(),
                                        edMail.getText().toString(),
                                        edHealthFac.getText().toString()
                                );
                                if(task.isSuccessful())
                                {
                                    fbDB.getReference("Users").push().setValue(user);
                                    Log.i("Registered", "Done");
                                }
                                else
                                {

                                }
                            }
                        });
            }
        });
    }
}
