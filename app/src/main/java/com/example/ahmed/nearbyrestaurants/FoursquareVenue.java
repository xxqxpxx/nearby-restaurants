package com.example.ahmed.nearbyrestaurants;

import java.util.List;

/**
 * Created by ahmed on 6/8/17.
 */

public class FoursquareVenue {
    // The ID of the venue.
    String id;

    // The name of the venue.
    String name;

    // The rating of the venue, if available.
    double rating;

    // A location object within the venue.
    FoursquareLocation location;

    String phone;

    String formattedPhone;

    FoursquareContact contact;

    List<FoursquareCategory>  categories;

    public void setId( String id)
    {
        this.id = id;
    }

    public void setName( String name)
    {
        this.name = name;
    }

    public void setRating( double rating)
    {
        this.rating = rating;
    }

    public void FoursquareVenue()
    {
        this.name = "";
        this .id = "";
        this . rating = -1;
    //    this.location = new FoursquareLocation();
  //      this.contact = new FoursquareContact();
    }
}
