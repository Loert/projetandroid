package com.example.user.entities;

/**
 * Created by ipSo on 17/06/2015.
 */
public class SpectacleSQLite {

    private int IdSpect;
    private String NomSpect;
    private int DureeSpect;
    private String HoraireSpect;

    public SpectacleSQLite(){
    }

    public int getIdSpect() {return this.IdSpect;}
    public String getNomSpect() {return this.NomSpect;}
    public int getDureeSpect() {return this.DureeSpect;}
    public String getHoraireSpect() {return this.HoraireSpect;}

    public void setIdSpect(int idSpect){this.IdSpect = idSpect;}
    public void setNomSpect(String nomSpect) {NomSpect = nomSpect;}
    public void setDureeSpect(int dureeSpect) {DureeSpect = dureeSpect;}
    public void setHoraireSpect(String horaireSpect) {HoraireSpect = horaireSpect;}

    public String toString(){
        return "ID : "+IdSpect+"\nNOM : "+NomSpect+"\nDUREE : "+DureeSpect+"\nHORAIRE : "+HoraireSpect;
    }
}
