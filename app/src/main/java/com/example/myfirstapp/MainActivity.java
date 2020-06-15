package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    // vars to connect recycler view later
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    // change stuff on creation of activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // recyclerview set up
        recyclerView = (RecyclerView) findViewById(R.id.camera_recycle); // id matches one set on xml
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a grid layout manager - layout manager sets up the layout of the page/object
        layoutManager = new GridLayoutManager(this, 2); //spancount is number of columns
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        // adapter makes each cell/puts the thing in them
        mAdapter = new MyAdapter(new String[]{"item 1", "item 2", "item 3"}); // using temp dataset
        recyclerView.setAdapter(mAdapter);
    }


}
