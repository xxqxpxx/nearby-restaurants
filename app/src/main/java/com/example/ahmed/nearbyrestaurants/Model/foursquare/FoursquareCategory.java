package com.example.ahmed.nearbyrestaurants.Model.foursquare;

/**
 * Created by ahmed on 6/10/17.
 */

public class FoursquareCategory {
    String name;
    String id;
    public String shortName;

    public FoursquareCategory()
    {
        shortName = "";
    }

    public String getShortName()
    {
        return  shortName;
    }

    public  void setShortName(String shortName)
    {
        this.shortName = shortName;
    }
}
