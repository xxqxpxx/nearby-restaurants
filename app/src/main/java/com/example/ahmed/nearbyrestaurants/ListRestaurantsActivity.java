package com.example.ahmed.nearbyrestaurants;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import static com.example.ahmed.nearbyrestaurants.MapsActivity.Places;
import static com.example.ahmed.nearbyrestaurants.MapsActivity.Venues;

public class ListRestaurantsActivity extends AppCompatActivity {

    // The RecyclerView and associated objects for displaying the nearby coffee spots
    private RecyclerView listRestaurant;
    private LinearLayoutManager listRestaurantManager;
    private RecyclerView.Adapter listRestaurantAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_restaurants);

        // The visible TextView and RecyclerView objects
        listRestaurant = (RecyclerView)findViewById(R.id.coffeeList);

        // Sets the dimensions, LayoutManager, and dividers for the RecyclerView
        listRestaurant.setHasFixedSize(true);
        listRestaurantManager = new LinearLayoutManager(this);
        listRestaurant.setLayoutManager(listRestaurantManager);
        listRestaurant.addItemDecoration(new DividerItemDecoration(listRestaurant.getContext(), listRestaurantManager.getOrientation()));

        addPlacesFromGooglePlacesToVenues();
        listRestaurantAdapter = new ListRestaurantsAdapter(getApplicationContext(), Venues);
        listRestaurant.setAdapter(listRestaurantAdapter);
    }


    public void addPlacesFromGooglePlacesToVenues() {

        Log.e("" , "Places " + Places.size());


        for (int i = 0; i < Places.size(); ++i) {
            FoursquareVenue item = new FoursquareVenue();
            FoursquareLocation loc = new FoursquareLocation();

            item.name = "";

            loc.address = Places.get(i).getVicinity();
            loc.lng =  Places.get(i).getGeometry().getLocation().getLng();
            loc.lat = Places.get(i).getGeometry().getLocation().getLat();

            item.name = Places.get(i).getName();
            item.location = loc;
            item.rating = Places.get(i).getRating();
            item.id = Places.get(i).getId();

            Log.e("" , "Places " + item );


            Venues.add(new FoursquareResults(item));

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Reconnects to the Google API
     }

    @Override
    protected void onPause() {
        super.onPause();

        // Disconnects from the Google API
     }


    public void switchtolist(View v )
    {
        Intent intent = new Intent(getBaseContext(), MapsActivity.class);
        startActivity(intent);
    }

}
