package com.example.locationfinderapp;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    LayoutInflater inflater;
    List<ModelLocation> locationModels;

    // The constructor to initialize the adapter with UI
    Adapter(Context context, List<ModelLocation> locationModels) {
        this.inflater = LayoutInflater.from(context);
        this.locationModels = locationModels;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.location_list_view, parent, false);
        return new ViewHolder(view);
    }

    public void filterList(List<ModelLocation> filteredList) {
        locationModels = filteredList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //pulls cities from the database
        String address = locationModels.get(position).getLocationAddress();
        String latitude = locationModels.get(position).getLocationLatitude();
        String longitude = locationModels.get(position).getLocationLongitude();



        //outputs the city info that user requested
        holder.showAddress.setText(address);
        holder.showCoordinates.setText("Lat: " + latitude + ", Lon: " + longitude);  //Outputs the words latitude and longitude for user

    }

    @Override
    public int getItemCount() {
        return locationModels.size();
    }

    // The ViewHolder class to hold the views for each item for user
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView showAddress, showCoordinates; // The textViews for address and latitude & longitude
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // gives the views
            showAddress = itemView.findViewById(R.id.showAddress);  //gives the address
            showCoordinates = itemView.findViewById(R.id.showCoordinates);  // gives the latitude and Longitude
            cardView = itemView.findViewById(R.id.cardView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), UpdateLocation.class);
                    intent.putExtra("ID", locationModels.get(getAdapterPosition()).getId());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
