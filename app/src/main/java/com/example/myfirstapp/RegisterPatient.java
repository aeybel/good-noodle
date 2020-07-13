package com.example.myfirstapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.myfirstapp.datainfo.Patient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class RegisterPatient extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE = 1;

    private EditText firstName;
    private EditText lastName;
    private ImageButton pfpUpload;
    private Button registerSubmit;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
    Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_patient);

        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        registerSubmit = findViewById(R.id.register_submit);
        pfpUpload = findViewById(R.id.upload_pfp);

        pfpUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This opens the gallery and gets the selected image for our app
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
            }
        });

        registerSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fn =  firstName.getText().toString();
                final String ln =  lastName.getText().toString();
                if(fn.isEmpty() || ln.isEmpty() || selectedImageUri == null) {
                    Toast.makeText(RegisterPatient.this, "Please fill the required fields in!", Toast.LENGTH_LONG).show();
                } else {
                    // upload to database
                    Log.i("register patient", "fields are ok good to go bye");

                    UploadImageToFirebase();
                    // TODO: Link the uploaded image to the profile in Firestore

                    final Patient newPatient = new Patient(fn, ln);

                    db.collection("patients").add(newPatient)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d("db", "DocumentSnapshot written with ID: " + documentReference.getId()+ " name is " + fn + " " + ln);
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("db", "error adding doc", e);
                                    Toast.makeText(RegisterPatient.this, "There was an error saving, please try again later.", Toast.LENGTH_LONG).show();
                                }
                            });
                }
            }
        });
    }

    // Method called when user selects a photo from the gallery
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check if the gallery was called and if an image was selected properly
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            // Get address of selected image and display it in the imageView
            selectedImageUri = data.getData();
            pfpUpload.setImageURI(selectedImageUri);
        }
    }

    // This method will upload the photo which is currently displayed to firebase
    void UploadImageToFirebase() {
        // This gives the path name for the storage
        StorageReference photoRef = mStorageRef.child("pfps").child(selectedImageUri.getLastPathSegment());

        // This uploads it into firebase storage
        // I have no idea what Toast does
        photoRef.putFile(selectedImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @SuppressLint("ShowToast")
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast toast = Toast.makeText(getApplicationContext(), "Uploaded", Toast.LENGTH_SHORT);
                toast.show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @SuppressLint("ShowToast")
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Upload failed", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
    }


}
