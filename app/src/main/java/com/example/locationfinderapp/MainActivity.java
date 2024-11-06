package com.example.locationfinderapp;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button addButton;
    RecyclerView recyclerView;
    Adapter adapter;
    List<ModelLocation> locationModelList;
    EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Now we're using a regular Button
        addButton = findViewById(R.id.add_button);  // Correct reference to a regular Button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewLocation.class);
                startActivity(intent);
            }
        });

        // Search function to filter locations by address
        searchEditText = findViewById(R.id.searchView);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Display the locations from the Database in the Main page's RecyclerView list
        recyclerView = findViewById(R.id.addRecyclerView);
        LocationDatabase noteDatabase = new LocationDatabase(this);
        locationModelList = noteDatabase.getLocation();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(this, locationModelList);
        recyclerView.setAdapter(adapter);
    }

    // Filter locations based on the query (search by location address)
    private void filter(String query) {
        List<ModelLocation> filteredList = new ArrayList<>();
        for (ModelLocation location : locationModelList) {
            if (location.getLocationAddress().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(location);
            }
        }
        adapter.filterList(filteredList);
    }





}
