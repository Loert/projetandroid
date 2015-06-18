package com.example.user.entities;

/**
 * Created by USER on 18/06/2015.
 */
public class MenuRestaurant {
    int Id;
    String Titre;
    String Description;
    int IdRestaurant;
    float Prix;

    public MenuRestaurant(int id,String titre,String description,int idRestaurant,float prix){
        this.Id = id;
        this.Titre = titre;
        this.Description = description;
        this.IdRestaurant = idRestaurant;
        this.Prix = prix;
    }

    public int getId(){
        return Id;
    }

    public String getTitre(){
        return Titre;
    }

    public String getDescription(){
        return Description;
    }

    public int getIdRestaurant(){
        return IdRestaurant;
    }

    public float getPrix(){
        return Prix;
    }
}
