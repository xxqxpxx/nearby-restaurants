package com.example.ahmed.nearbyrestaurants.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.ahmed.nearbyrestaurants.Model.foursquare.FoursquareVenue;
import com.example.ahmed.nearbyrestaurants.R;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;

import java.util.Locale;


public class DetailsActivity extends AppCompatActivity {

    FoursquareVenue item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        int itemPosition = intent.getExtras().getInt("ITEM");
        item = MapsActivity.Venues.get(itemPosition).venue;


        setTitle(item.name);
        TextView category = (TextView) findViewById(R.id.category);
        TextView rating = (TextView) findViewById(R.id.rating);

        category.setText( "Category : " + item.cat);
        rating.setText( "Rating : " + String.valueOf(item.rating));
      //  getGooglePhone(itemPosition);

    }


    public void setGooglePhone(String number )
    {
        item.phone = number;
        Log.v("ahmed" , "phoneNumber" + number);
    }

    public void getGooglePhone( int i)
    {
        Places.GeoDataApi.getPlaceById(MapsActivity.mGoogleApiClient, MapsActivity.Placess.get(i).getplaceid()).setResultCallback(new ResultCallback<PlaceBuffer>() {
            @Override
            public void onResult(PlaceBuffer places) {
                if (places.getStatus().isSuccess() ) {
                    Place myPlace = places.get(0);
                    setGooglePhone( (String) myPlace.getPhoneNumber());
                } else {
                    Log.e("", "Place not found");
                }
                places.release();
            }
        });
    }


    public void phoneCall(View v) {
        String number = item.phone;
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" +number));
        startActivity(intent);
    }


    public void mapDirection(View v)
    {
        String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f (%s)",   item.location.lat , item.location.lng, "Where the Restaurant is at");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
