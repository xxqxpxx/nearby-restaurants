package com.example.ahmed.nearbyrestaurants.Presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ahmed.nearbyrestaurants.View.DetailsActivity;
import com.example.ahmed.nearbyrestaurants.Model.foursquare.FoursquareResults;
import com.example.ahmed.nearbyrestaurants.R;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by ahmed on 6/9/17.
 */

public class ListRestaurantsAdapter extends RecyclerView.Adapter<ListRestaurantsAdapter.ViewHolder> {

    // The application context for getting resources
    private Context context;

    // The list of results from the Foursquare API
    private List<FoursquareResults> results;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // The venue fields to display
        TextView name;
        TextView address;
        TextView rating;
        TextView distance;
        String id;
        TextView phone;
        double latitude;
        double longitude;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);

            // Gets the appropriate view for each venue detail
            name = (TextView)v.findViewById(R.id.placePickerItemName);
            address = (TextView)v.findViewById(R.id.placePickerItemAddress);
       //     rating = (TextView)v.findViewById(R.id.placePickerItemRating);
            distance = (TextView)v.findViewById(R.id.placePickerItemDistance);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext() , DetailsActivity.class);
            intent.putExtra("ITEM" ,  getAdapterPosition());
            Log.d(TAG, "onClick: " + getAdapterPosition());
            v.getContext().startActivity(intent);
        }
    }

    public ListRestaurantsAdapter(Context context, List<FoursquareResults> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public ListRestaurantsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place_picker, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        // Sets each view with the appropriate venue details
        holder.name.setText(results.get(position).venue.name);
        holder.address.setText(results.get(position).venue.location.address);
        holder.distance.setText(Integer.toString(results.get(position).venue.location.distance) + "m");

        // Stores additional venue details for the map view
        holder.id = results.get(position).venue.id;
        holder.latitude = results.get(position).venue.location.lat;
        holder.longitude = results.get(position).venue.location.lng;
    }

    @Override
    public int getItemCount() {
        return results.size();
    }
}