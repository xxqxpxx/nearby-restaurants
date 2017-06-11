package com.example.ahmed.nearbyrestaurants.Model.foursquare;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed on 6/8/17.
 */

public class FoursquareResponse {
    // A group object within the response.
    public FoursquareGroup group;
    List<FoursquareVenue> venues = new ArrayList<>();

}
