package com.example.ahmed.nearbyrestaurants.Model.foursquare;

/**
 * Created by ahmed on 6/8/17.
 */

public class FoursquareLocation {
    // The address of the location.
    public String address;

    // The latitude of the location.
    public double lat;

    // The longitude of the location.
    public double lng;

    // The distance of the location, calculated from the specified location.
    public int distance;

   public void FoursquareLocation()
   {
       this.address = "";
       this.lat = 0;
       this.lng= 0;
       this.distance = 0;
   }
}
