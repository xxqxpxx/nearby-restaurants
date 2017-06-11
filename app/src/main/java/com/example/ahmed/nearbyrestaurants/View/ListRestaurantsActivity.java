package com.example.ahmed.nearbyrestaurants.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.ahmed.nearbyrestaurants.Model.foursquare.FoursquareCategory;
import com.example.ahmed.nearbyrestaurants.Model.foursquare.FoursquareLocation;
import com.example.ahmed.nearbyrestaurants.Model.foursquare.FoursquareResults;
import com.example.ahmed.nearbyrestaurants.Model.foursquare.FoursquareVenue;
import com.example.ahmed.nearbyrestaurants.Presenter.ListRestaurantsAdapter;
import com.example.ahmed.nearbyrestaurants.R;

public class ListRestaurantsActivity extends AppCompatActivity {

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
        listRestaurantAdapter = new ListRestaurantsAdapter(getApplicationContext(), MapsActivity.Venues);
        listRestaurant.setAdapter(listRestaurantAdapter);
    }


    public void addPlacesFromGooglePlacesToVenues() {

        Log.e("" , "Placess " + MapsActivity.Placess.size());


        for (int i = 0; i < MapsActivity.Placess.size(); ++i) {
            FoursquareVenue item = new FoursquareVenue();
            FoursquareLocation loc = new FoursquareLocation();
            FoursquareCategory cat = new FoursquareCategory();

            item.name = "";

            loc.address = MapsActivity.Placess.get(i).getVicinity();
            loc.lng =  MapsActivity.Placess.get(i).getGeometry().getLocation().getLng();
            loc.lat = MapsActivity.Placess.get(i).getGeometry().getLocation().getLat();

            item.name = MapsActivity.Placess.get(i).getName();
            item.location = loc;
            item.rating = MapsActivity.Placess.get(i).getRating();
            item.id = MapsActivity.Placess.get(i).getplaceid();
            item.phone = MapsActivity.Placess.get(i).getformatted_phone_number();

            item.cat = MapsActivity.Placess.get(i).getCategory();
            Log.e("" , "Placess " + item );


            MapsActivity.Venues.add(new FoursquareResults(item));

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
     }

    @Override
    protected void onPause() {
        super.onPause();
     }

     @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void switchtolist(View v )
    {
        MapsActivity.Venues = null;
        MapsActivity.Placess = null;
        Intent intent = new Intent(getBaseContext(), MapsActivity.class);
        startActivity(intent);
    }

}
