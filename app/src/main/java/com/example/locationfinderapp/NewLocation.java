package com.example.locationfinderapp;
import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class NewLocation extends AppCompatActivity {
    Button backButton, saveButton, calculateButton;
    EditText address, enterLatitude, enterLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_location);

        // initialize variables from the views
        address = findViewById(R.id.address);
        enterLatitude = findViewById(R.id.latitude);
        enterLongitude = findViewById(R.id.longitude);



        // action buttons
        backButton = findViewById(R.id.backButton);
        saveButton = findViewById(R.id.saveButton);
        calculateButton = findViewById(R.id.uploadButton);



        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateCoordinates(address.getText().toString().trim());
            }
        });



        // for saving cities to the database
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String titleCheck = address.getText().toString().trim();
                if(titleCheck.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Error: No address", Toast.LENGTH_LONG).show();
                }

                else{
                    ModelLocation newLocation = new ModelLocation(
                            address.getText().toString(),
                            enterLatitude.getText().toString(),
                            enterLongitude.getText().toString()
                    );
                    LocationDatabase db = new LocationDatabase(NewLocation.this);
                    db.AddLocation(newLocation);
                    Intent intent = new Intent(NewLocation.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Location Added", Toast.LENGTH_SHORT).show();
                }

            }
        });




        // back button that returns to the main page
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewLocation.this, MainActivity.class);  // Replace NewActivity with the desired target activity
                startActivity(intent);
                finish();
            }
        });
    }






    public void calculateCoordinates(String address){

        // Check if the address is empty
        if (address.isEmpty()) {
            Toast.makeText(NewLocation.this, "Please enter an address.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Initialize Geocoder
        Geocoder geocoder = new Geocoder(NewLocation.this);
        try {
            // Get a list of addresses that match the entered address
            List<Address> addressList = geocoder.getFromLocationName(address, 1);  // Only get one result
            if (addressList != null && !addressList.isEmpty()) {
                // Get the first result
                Address location = addressList.get(0);

                // Extract latitude and longitude
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

                // Display latitude and longitude on the TextViews
                enterLatitude.setText(String.valueOf(latitude));
                enterLongitude.setText(String.valueOf(longitude));
            } else {
                Toast.makeText(NewLocation.this, "No location found for this address.", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            // Handle any IOExceptions (e.g., network issues)
            e.printStackTrace();
            Toast.makeText(NewLocation.this, "Geocoding failed, try again later.", Toast.LENGTH_SHORT).show();
        }
    }







}