package com.example.ahmed.nearbyrestaurants.View;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.ahmed.nearbyrestaurants.Model.Google.Example;
import com.example.ahmed.nearbyrestaurants.Model.Google.Result;
import com.example.ahmed.nearbyrestaurants.Model.Google.RetrofitMaps;
import com.example.ahmed.nearbyrestaurants.Model.foursquare.FoursquareGroup;
import com.example.ahmed.nearbyrestaurants.Model.foursquare.FoursquareJSON;
import com.example.ahmed.nearbyrestaurants.Model.foursquare.FoursquareResponse;
import com.example.ahmed.nearbyrestaurants.Model.foursquare.FoursquareResults;
import com.example.ahmed.nearbyrestaurants.Model.foursquare.FoursquareService;
import com.example.ahmed.nearbyrestaurants.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.ahmed.nearbyrestaurants.R.id.map;

public class MapsActivity extends AppCompatActivity
        implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    GoogleMap mGoogleMap;
    SupportMapFragment mapFrag;
    LocationRequest mLocationRequest;
    public static GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    private String venueID;
    private String venueName;
    private double venueLatitude;
    private double venueLongitude;
    public static  int Index;

    private int PROXIMITY_RADIUS = 1000;
    public static List<Result> Placess;
    public static List<FoursquareResults> Venues;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

     //   getSupportActionBar().setTitle("Map Location Activity");
  //      Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(map);
        mapFrag.getMapAsync(this);

    }

    @Override
    public void onPause() {
        super.onPause();

        //stop location updates when Activity is no longer active
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mGoogleMap=googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            buildGoogleApiClient();
            mGoogleMap.setMyLocationEnabled(true);

        mGoogleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener()
        {
            @Override
            public void onInfoWindowClick(Marker arg0) {

            }

        });
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
         LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {}

    @Override
    public void onLocationChanged(Location location) {

        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Place current location marker
        LatLng latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);

        //move map camera
//        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));

        build_retrofit_and_get_response("restaurant");
        build_retrofit_and_get_response_Foursquare();

    }

    public void setGooglePhone(String number )
    {
        Placess.get(Index).setformatted_phone_number(number);
        Log.v("ahmed" , "phoneNumber" + number);
    }

    public void getGooglePhone( int i)
    {
        Places.GeoDataApi.getPlaceById(mGoogleApiClient, Placess.get(i).getplaceid()).setResultCallback(new ResultCallback<PlaceBuffer>() {
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

    private void build_retrofit_and_get_response(String type) {
        String url = "https://maps.googleapis.com/maps/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitMaps service = retrofit.create(RetrofitMaps.class);

        Call<Example> call = service.getNearbyPlaces(type,  mLastLocation.getLatitude() + "," + mLastLocation.getLongitude(), PROXIMITY_RADIUS );

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                Log.v("" , "onResponse: " +  response.body().getResults().size() );
                Log.v("" , "onResponse: " +  call.isExecuted() );

                try {

                    Log.v("" , "onResponse: " +  response.body().getResults().size() );
                    // This loop will go through all the results and add marker on each location.
                    Placess = response.body().getResults();
                    for (int i = 0; i < response.body().getResults().size(); i++) {
                        Double lat = response.body().getResults().get(i).getGeometry().getLocation().getLat();
                        Double lng = response.body().getResults().get(i).getGeometry().getLocation().getLng();
                        String placeName = response.body().getResults().get(i).getName();
                        String vicinity = response.body().getResults().get(i).getVicinity();
                    //    Placess.get(i).setformatted_phone_number("-1");
                        Placess.get(i).setCategory("Food");
                        Index = i;
              //          getGooglePhone(i);

                        MarkerOptions markerOptions = new MarkerOptions();
                        LatLng latLng = new LatLng(lat, lng);

                        // Position of Marker on Map
                        markerOptions.position(latLng);
                        // Adding Title to the Marker
                        markerOptions.title(placeName + " : " + vicinity);
                        // Adding colour to the marker
                        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

                        //MarkerOptions marker = new MarkerOptions().position(new LatLng(lat, lng)).title("Hello Maps ");


                        mGoogleMap.addMarker(markerOptions);

                        // move map camera
               //       mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
           //             mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(16));
                    }
                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Google Api is down", Toast.LENGTH_LONG).show();
            }


        });
    }

    public void build_retrofit_and_get_response_Foursquare()
    {
         String foursquareBaseURL = "https://api.foursquare.com/v2/";
         String foursquareClientID = "1XT0GI5RIVKD4OS2XRNU01HKBZ0XVCYOQAKCBAHAC2UZQWDW";
         String foursquareClientSecret = "WSD2IKADKAMDGAJIBRLVY20VDGKXZS2WSMOKK2AA4GZUYA1L";
        // The user's current latitude, longitude, and location accuracy
        String userLL = mLastLocation.getLatitude() + "," +  mLastLocation.getLongitude();
        double userLLAcc = mLastLocation.getAccuracy();

        // Builds Retrofit and FoursquareService objects for calling the Foursquare API and parsing with GSON
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(foursquareBaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FoursquareService foursquare = retrofit.create(FoursquareService.class);

        // Calls the Foursquare API to explore nearby coffee spots
        Call<FoursquareJSON> coffeeCall = foursquare.searchCoffee(
                foursquareClientID,
                foursquareClientSecret,
                userLL,
                userLLAcc);
        coffeeCall.enqueue(new Callback<FoursquareJSON>() {
            @Override
            public void onResponse(Call<FoursquareJSON> call, Response<FoursquareJSON> response) {

                // Gets the venue object from the JSON response
                FoursquareJSON fjson = response.body();
                FoursquareResponse fr = fjson.response;
                FoursquareGroup fg = fr.group;
                List<FoursquareResults> frs = fg.results;
             Venues = frs;

              for (int i = 0 ; i < frs.size() ; ++i)
              {
                 String venueID = frs.get(i).venue.id;
                  String   venueName = frs.get(i).venue.name;
                  double    venueLatitude = frs.get(i).venue.location.lat;
                   double   venueLongitude = frs.get(i).venue.location.lng;
                  Venues.get(i).venue.cat = frs.get(i).venue.categories.get(0).shortName;
                  Venues.get(i).venue.phone = frs.get(i).venue.contact.phone;;

                  MarkerOptions markerOptions = new MarkerOptions();
                  LatLng latLng = new LatLng(venueLatitude, venueLongitude);

                  // Position of Marker on Map
                  markerOptions.position(latLng);
                  // Adding Title to the Marker
                  markerOptions.title(venueName );
                  // Adding colour to the marker
                  markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

                  mGoogleMap.addMarker(markerOptions);
              }

            }

            @Override
            public void onFailure(Call<FoursquareJSON> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Foursquare Api is down ", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        LatLng zoomin = new LatLng(mLastLocation.getLatitude() , mLastLocation.getLongitude());
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(zoomin, 14));
    }

    public void switchtolist(View v )
    {
        Intent intent = new Intent(getBaseContext(), ListRestaurantsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}