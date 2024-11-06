package com.example.locationfinderapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Button;
import android.os.Bundle;
import android.widget.Toast;
import android.location.Address;
import android.location.Geocoder;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class UpdateLocation extends AppCompatActivity {
    TextView showLatitude, showLongitude;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        // the location's properties (address, latitude, longitude)
        EditText showAddress = findViewById(R.id.showAddress);
        showLatitude = findViewById(R.id.showLatitude);
        showLongitude = findViewById(R.id.showLongitude);


        // the different buttons (save, delete, cancel)
        Button calculateButton = findViewById(R.id.calculateButton);
        Button saveButton = findViewById(R.id.saveButton);
        Button deleteButton = findViewById(R.id.deleteButton);
        Button cancelButton = findViewById(R.id.cancelButton);


        LocationDatabase db = new LocationDatabase(this);
        Intent intent = getIntent();

        // Displays the selected note's title, description, color and picture based on the ID
        id = intent.getIntExtra("ID", 0);
        ModelLocation locationModel = db.getLocation(id);
        showAddress.setText(locationModel.getLocationAddress());
        showLatitude.setText(locationModel.getLocationLatitude());
        showLongitude.setText(locationModel.getLocationLongitude());

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateCoordinates(showAddress.getText().toString().trim());
            }
        });

        // for saving notes to the database
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String titleCheck = showAddress.getText().toString().trim();
                if(titleCheck.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Error: No Title", Toast.LENGTH_LONG).show();
                }

                else{
                    ModelLocation newLocation = new ModelLocation(
                            showAddress.getText().toString(),
                            showLatitude.getText().toString(),
                            showLongitude.getText().toString()
                    );
                    LocationDatabase db = new LocationDatabase(UpdateLocation.this);
                    db.AddLocation(newLocation);
                    Intent intent = new Intent(UpdateLocation.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Location is Updated", Toast.LENGTH_SHORT).show();
                }

            }
        });


        // deletes the note from the database
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                id = intent.getIntExtra("ID", 0);
                db.deleteLocation(id);
                Intent intent1 = new Intent(UpdateLocation.this, MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateLocation.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    // Get the address entered by the user using geocoding
    public void calculateCoordinates(String address){

        // Check if the address is empty
        if (address.isEmpty()) {
            Toast.makeText(UpdateLocation.this, "Please enter an address.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Initializes the Geocoder
        Geocoder geocoder = new Geocoder(UpdateLocation.this);
        try {
            // grabs a list of addresses that match the entered address
            List<Address> addressList = geocoder.getFromLocationName(address, 1);  // Only get one result
            if (addressList != null && !addressList.isEmpty()) {
                // Get the first result
                Address location = addressList.get(0);

                // Extract latitude and longitude
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

                // Display latitude and longitude on the TextViews
                showLatitude.setText(String.valueOf(latitude));
                showLongitude.setText(String.valueOf(longitude));
            } else {
                Toast.makeText(UpdateLocation.this, "No location found for this address.", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {

            e.printStackTrace();
            Toast.makeText(UpdateLocation.this, "Geocoding failed, try again later.", Toast.LENGTH_SHORT).show();
        }
    }

}

