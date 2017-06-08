package com.example.ahmed.nearbyrestaurants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ahmed on 6/8/17.
 */

public interface RetrofitMaps {
    @GET("api/place/nearbysearch/json?sensor=true&key=AIzaSyA41mt9qbS6G5Sc6r7XPS23ZgZs0zYdO5E")
    Call<Example> getNearbyPlaces(@Query("type") String type, @Query("location") String location, @Query("radius") int radius);
}
