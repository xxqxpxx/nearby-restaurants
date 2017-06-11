package com.example.ahmed.nearbyrestaurants.Model.foursquare;

/**
 * Created by ahmed on 6/8/17.
 */

public class FoursquareResults  {

    // A venue object within the results.
    public FoursquareVenue venue;

    public FoursquareResults(FoursquareVenue item) {
        this.venue = item;
    }
}
