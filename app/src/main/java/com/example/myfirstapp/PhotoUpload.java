package com.example.myfirstapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class PhotoUpload extends AppCompatActivity implements View.OnClickListener {
    // TODO: handle exceptions and errors with photo uploading

    private static final int RESULT_LOAD_IMAGE = 1;

    // Initialize all the stuff
    ImageView imageToUpload;
    Button imageUploadButton, confirmUploadButton, closeButton;
    EditText imageUploadDescription;

    private StorageReference mStorageRef;

    Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_upload);

        // Initializing all of the layout components
        imageToUpload = findViewById(R.id.uploaded_img);
        imageUploadButton = findViewById(R.id.img_upload_button);
        imageUploadDescription = findViewById(R.id.upload_description);
        confirmUploadButton = findViewById(R.id.confirm_upload_button);
        closeButton = findViewById(R.id.close_button);

        // Initialize the on click listeners for the buttons
        imageToUpload.setOnClickListener(this);
        imageUploadButton.setOnClickListener(this);
        confirmUploadButton.setOnClickListener(this);
        closeButton.setOnClickListener(this);

        // Get the firebase storage reference
        mStorageRef = FirebaseStorage.getInstance().getReference();
    }

    // Set the intent to go to the gallery upon click
    @SuppressLint("ShowToast")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.uploaded_img || v.getId() == R.id.img_upload_button) {
            // This opens the gallery and gets the selected image for our app
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
        }
        if (v.getId() == R.id.confirm_upload_button) {
            if (selectedImageUri != null) {
                UploadToFirebase();
            }
            else {
                Toast toast = Toast.makeText(getApplicationContext(), "No photo selected", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        if (v.getId() == R.id.close_button) {
            openHomeActivity();
        }
    }

    // Method called when user selects a photo from the gallery
    // TODO: Maybe compress the images so that they always fit to the screen and also take up less space in firestore.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check if the gallery was called and if an image was selected properly
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            // Get address of selected image and display it in the imageView
            selectedImageUri = data.getData();
            imageToUpload.setImageURI(selectedImageUri);
        }
    }

    // This method will upload the photo which is currently displayed to firebase
    void UploadToFirebase() {
        // This gives the path name for the storage
        StorageReference photoRef = mStorageRef.child("images").child(selectedImageUri.getLastPathSegment());

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

    public void openHomeActivity() {
        Intent myIntent = new Intent(this, HomeScreen.class);
        startActivity(myIntent);
    }

}
