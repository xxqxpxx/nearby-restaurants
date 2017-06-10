package com.example.ahmed.nearbyrestaurants;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import static com.example.ahmed.nearbyrestaurants.MapsActivity.Venues;


public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        int itemPosition = intent.getExtras().getInt("ITEM");
        FoursquareVenue item = Venues.get(itemPosition).venue;


        setTitle(item.name);
        TextView category = (TextView) findViewById(R.id.category);
        TextView rating = (TextView) findViewById(R.id.rating);
        TextView telephone = (TextView) findViewById(R.id.telephone);
        TextView address = (TextView) findViewById(R.id.address);

        category.setText("Food");
        rating.setText(  String.valueOf(item.rating));
        telephone.setText("01147674181");
        address.setText(item.location.address);


    }
}
