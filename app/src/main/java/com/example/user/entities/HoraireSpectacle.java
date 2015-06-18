package com.example.user.entities;

/**
 * Created by ipSo on 15/06/2015.
 */
public class HoraireSpectacle {
    private int Id;
    private String Horaire;
    private int IdSpectacle;

    public HoraireSpectacle(int id,String Horaire){
        this.Id = id;
        this.Horaire = Horaire;
    }

    public int getId(){
        return this.Id;
    }

    public String getHoraire(){return this.Horaire;}

    public int getIdSpectacle(){return this.IdSpectacle;}

    public void setIdSpectacle(int idsp){
        this.IdSpectacle = idsp;
    }
}
