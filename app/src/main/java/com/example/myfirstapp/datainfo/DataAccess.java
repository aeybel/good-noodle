package com.example.myfirstapp.datainfo;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

// put all data access here ??
public class DataAccess {

    /*
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public List<Patient> getAllPatients() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final List<Patient>[] mDatabase = new List<Patient>[1];
        db.collection("patients")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(queryDocumentSnapshots.isEmpty()) {
                            // TODO: display message that no patients have been added
                        } else {
                            mDatabase[0] = queryDocumentSnapshots.toObjects(Patient.class);
                            Log.d("db", "received patients " + mDatabase[0]);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // TODO: display message that error receiving
                        Log.d("db", "could not load patients from db");
                    }
                });

    }*/

}
