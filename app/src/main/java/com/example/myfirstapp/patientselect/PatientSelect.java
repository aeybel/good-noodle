package com.example.myfirstapp.patientselect;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import com.example.myfirstapp.R;
import com.example.myfirstapp.RegisterPatient;
import com.example.myfirstapp.patientselect.dummy.DummyContent;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

// INFO:
// - use List Fragment for patient list
// - use Modal Bottom Sheet for bottom "dialogue"

// 1. implement OnListFragmentInteractionListener for List fragment !

public class PatientSelect extends AppCompatActivity implements PatientFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_select);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.patient_select_menu, menu);
        SearchView searchBar = (SearchView) menu.findItem(R.id.search_bar).getActionView();
        searchBar.setIconifiedByDefault(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.add_patient_button:
                Intent myIntent = new Intent(this, RegisterPatient.class);
                startActivity(myIntent);
                return true;
            case R.id.search_bar:
                // TODO: set up search bar functionability
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof PatientFragment) {
            PatientFragment patientFragment = (PatientFragment) fragment;
            // setting up listener to be context of this activity (notice we define onListFragmentInteraction
            // in next function down)
            // this should probably be changed to use the ViewModel according to documentation
            patientFragment.setOnListFragmentInteractionListener(this);
        }
    }

    // function is called after the onListItemClick (part of recyclerviewAdapter) is called
    @Override
    public void onListFragmentInteraction(DummyContent.PatientItem item) {
        Log.i("list fragment", String.format("clicked on %s", item.name));
        SelectedPatientDialogFragment.newInstance(item.id).show(getSupportFragmentManager(), "dialog");
    }

}
