package com.example.ahmed.nearbyrestaurants.Model.Google;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed on 6/8/17.
 */

public class Result {

    private String icon;
    private String id;
    private String name;
    private String place_id;
    private Double rating;
    private String reference;
    private String scope;
    private List<String> types = new ArrayList<String>();
    private String vicinity;
    private Integer priceLevel;
    private Geometry geometry;
    private String formatted_phone_number;
    private String category;

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getCategory()
    {
        return category;
    }

    public String getformatted_phone_number()
    {
        return formatted_phone_number;
    }

    public void setformatted_phone_number( String formatted_phone_number)
    {
        this.formatted_phone_number = formatted_phone_number;
    }


    /**
     *
     * @return
     * The icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     *
     * @return
     * The geometry
     */
    public Geometry getGeometry() {
        return geometry;
    }



    /**
     *
     * @param geometry
     * The geometry
     */

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    /**
     *
     * @param icon
     * The icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     *
     * @return
     * The placeId
     */
    public String getplaceid() {
        return place_id;
    }

    /**
     *
     * @param placeId
     * The place_id
     */
    public void setplaceid(String placeid) {
        this.place_id = placeid;
    }

    /**
     *
     * @return
     * The rating
     */
    public Double getRating() {
        return rating;
    }

    /**
     *
     * @param rating
     * The rating
     */
    public void setRating(Double rating) {
        this.rating = rating;
    }

    /**
     *
     * @return
     * The reference
     */
    public String getReference() {
        return reference;
    }

    /**
     *
     * @param reference
     * The reference
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     *
     * @return
     * The scope
     */
    public String getScope() {
        return scope;
    }

    /**
     *
     * @param scope
     * The scope
     */
    public void setScope(String scope) {
        this.scope = scope;
    }

    /**
     *
     * @return
     * The types
     */
    public List<String> getTypes() {
        return types;
    }

    /**
     *
     * @param types
     * The types
     */
    public void setTypes(List<String> types) {
        this.types = types;
    }

    /**
     *
     * @return
     * The vicinity
     */
    public String getVicinity() {
        return vicinity;
    }

    /**
     *
     * @param vicinity
     * The vicinity
     */
    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    /**
     *
     * @return
     * The priceLevel
     */
    public Integer getPriceLevel() {
        return priceLevel;
    }

    /**
     *
     * @param priceLevel
     * The price_level
     */
    public void setPriceLevel(Integer priceLevel) {
        this.priceLevel = priceLevel;
    }

}
