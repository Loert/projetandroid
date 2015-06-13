package com.example.user.entities;

/**
 * Created by USER on 13/06/2015.
 */
public class Spectacle {
    private int Id;
    private String Nom;
    private String Description;
    private int Duree;
    private String Date_creation;
    private int Nombre_acteurs;
    private double Latitude;
    private double Longitude;

    public Spectacle(){

    }

    public Spectacle(int id,String nom,String description, String date_creation,int duree,int nombre_acteurs,double latitude,double longitude){
        this.Id = id;
        this.Nom = nom;
        this.Description = description;
        this.Duree = duree;
        this.Date_creation = date_creation;
        this.Nombre_acteurs = nombre_acteurs;
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

    public int getDuree(){
        return this.Duree;
    }

    public String getDate_creation(){
        return this.Date_creation;
    }

    public int getNombre_acteurs(){
        return this.Nombre_acteurs;
    }

    public double getLatitude(){
        return this.Latitude;
    }

    public double getLongitude(){
        return this.Longitude;
    }
}
