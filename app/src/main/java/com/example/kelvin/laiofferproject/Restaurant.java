package com.example.kelvin.laiofferproject;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by kelvin on 11/27/17.
 */

public class Restaurant {
    /**
     * All data
     */
    private String name;
    private String address;
    private String type;
    private double lat;
    private double lng;
    private Bitmap thumbnail;
    private Bitmap rating;
    private List<String> categories;
    private double stars;
    private String url;
    private boolean favorate;
    private String item_id;





    public Restaurant(){}
    public Restaurant(String name, String address, String type, double lat, double lng, Bitmap thumbnail, Bitmap Rating) {
        this.name = name;
        this.address = address;
        this.type = type;
        this.lat = lat;
        this.lng = lng;
        this.thumbnail = thumbnail;
        this.rating = rating;
    }


    //setters
    public void setItem_id(String item_id) { this.item_id = item_id;}

    public void setCategories(List<String> categories) { this.categories = categories; }

    public void setStars(double stars) { this.stars = stars; }

    public void setName(String name) {this.name = name;}

    public void setAddress(String address) {  this.address = address; }

    public void setType(String type) { this.type = type; }

    public void setLat(double lat) { this.lat = lat; }

    public void setLng(double lng) { this.lng = lng; }

    public void setThumbnail(Bitmap thumbnail) { this.thumbnail = thumbnail; }

    public void setRating(Bitmap rating) { this.rating = rating; }

    public void setUrl(String url) {
        this.url = url;
    }


    public boolean isFavorate() {
        return favorate;
    }

    public void setFavorate(boolean favorate) {
        this.favorate = favorate;
    }

    /* Constructor  */

    public Bitmap getRating() { return rating; }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getType() {
        return type;
    }

    public double getLat() { return lat; }

    public double getLng() { return lng; }

    public Bitmap getThumbnail() { return thumbnail; }

    public String getUrl() {
        return url;
    }

    public List<String> getCategories() { return categories; }

    public double getStars() { return stars; }

    public String getItem_id() { return item_id; }
}
