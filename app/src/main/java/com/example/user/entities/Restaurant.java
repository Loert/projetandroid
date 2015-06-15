package com.example.user.entities;

/**
 * Created by USER on 15/06/2015.
 */
public class Restaurant {
    private int Id;
    private String Nom;
    private String Description;
    private double Latitude;
    private double Longitude;

    public Restaurant(int id,String nom,String description,double latitude,double longitude){
        this.Id = id;
        this.Nom = nom;
        this.Description = description;
        this.Latitude = latitude;
        this.Longitude = longitude;
    }

    public int getId(){
        return this.Id;
    }

    public String getNom(){
        return this.Nom;
    }

    public String getDescription(){
        return this.Description;
    }

    public double getLatitude(){
        return this.Latitude;
    }

    public double getLongitude(){
        return this.Longitude;
    }
}
