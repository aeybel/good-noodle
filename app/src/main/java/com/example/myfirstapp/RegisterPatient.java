package com.example.myfirstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterPatient extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;
    private ImageButton pfpUpload;
    private Button registerSubmit;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_patient);

        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        registerSubmit = findViewById(R.id.register_submit);

        registerSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fn =  firstName.getText().toString();
                final String ln =  lastName.getText().toString();
                if(fn.isEmpty() || ln.isEmpty()) {
                    Toast.makeText(RegisterPatient.this, "Please fill the required fields in!", Toast.LENGTH_LONG).show();
                } else {
                    // upload to database
                    Log.i("register patient", "fields are ok good to go bye");
                    final Patient newPatient = new Patient(fn, ln);

                    db.collection("patients").add(newPatient)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d("db", "DocumentSnapshot written with ID: " + documentReference.getId()+ " name is " + fn + " " + ln);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("db", "error adding doc", e);
                                }
                            });
                }
            }
        });
    }




}
