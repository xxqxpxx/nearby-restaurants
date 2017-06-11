package com.example.ahmed.nearbyrestaurants.Model.foursquare;

import java.util.List;

/**
 * Created by ahmed on 6/8/17.
 */

public class FoursquareVenue {
    // The ID of the venue.
    public String id;

    // The name of the venue.
    public String name;

    // The rating of the venue, if available.
    public double rating;

    // A location object within the venue.
    public FoursquareLocation location;

    // Catagory
    public String cat;

    // phone number
    public String phone;

    // Object contact
    public FoursquareContact contact;

    public List<FoursquareCategory>  categories;

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }
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
        this.phone = "";
    //    this.location = new FoursquareLocation();
  //      this.contact = new FoursquareContact();
    }
}
