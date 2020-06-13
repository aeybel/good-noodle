package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        final Button button1 = findViewById(R.id.camera_roll_button);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openCameraActivity();
            }
        });

        final Button button2 = findViewById(R.id.album_button);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openCameraActivity();
            }
        });

        final Button button3 = findViewById(R.id.people_button);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openCameraActivity();
            }
        });

        final Button button4 = findViewById(R.id.flashcard_button);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openCameraActivity();
            }
        });
    }

    public void openCameraActivity() {
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }
}
