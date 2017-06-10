package com.example.ahmed.nearbyrestaurants;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

import static com.example.ahmed.nearbyrestaurants.MapsActivity.Venues;


public class DetailsActivity extends AppCompatActivity {

    FoursquareVenue item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        int itemPosition = intent.getExtras().getInt("ITEM");
        item = Venues.get(itemPosition).venue;


        setTitle(item.name);
        TextView category = (TextView) findViewById(R.id.category);
        TextView rating = (TextView) findViewById(R.id.rating);
     //   TextView telephone = (TextView) findViewById(R.id.telephone);
     //   TextView address = (TextView) findViewById(R.id.address);

        category.setText( "Category : " +  item.categories.get(0).shortName);
        rating.setText( "Rating : " + String.valueOf(item.rating));
        //telephone.setText("01147674181");
        //address.setText(item.location.address);
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
}
