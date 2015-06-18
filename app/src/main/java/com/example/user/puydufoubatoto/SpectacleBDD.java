package com.example.user.puydufoubatoto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.example.user.entities.SpectacleSQLite;

import java.util.ArrayList;
import java.util.List;


public class SpectacleBDD extends ActionBarActivity {
    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "PUYDUFOU.db";

    private static final String TABLE_PLANNING = "table_planning";
    private static final String COL_ID = "ID_SPECT";
    private static final int NUM_COL_ID = 0;
    private static final String COL_NOM = "NOM_SPECT";
    private static final int NUM_COL_NOM = 1;
    private static final String COL_DUREE = "DUREE_SPECT";
    private static final int NUM_COL_DUREE = 2;
    private static final String COL_HORAIRE = "HORAIRE_SPECT";
    private static final int NUM_COL_HORAIRE = 3;

    private SQLiteDatabase bdd;

    private SQLitePlanning maBaseSQLite;

    public SpectacleBDD(Context context){
        //On créer la BDD et sa table
        maBaseSQLite = new SQLitePlanning(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = maBaseSQLite.getWritableDatabase();
    }
    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long ajouterSpectacle(SpectacleSQLite Spectacle){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        if(bdd == null)
            System.out.println("Bite");
        values.put(COL_NOM, Spectacle.getNomSpect());
        values.put(COL_DUREE, Spectacle.getDureeSpect());
        values.put(COL_HORAIRE, Spectacle.getHoraireSpect());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_PLANNING, null, values);
    }

    public int modifierSpectacle(int id, SpectacleSQLite Spectacle){
        //La mise à jour d'un livre dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simple préciser quelle livre on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_NOM, Spectacle.getNomSpect());
        values.put(COL_DUREE, Spectacle.getDureeSpect());
        values.put(COL_HORAIRE, Spectacle.getHoraireSpect());
        return bdd.update(TABLE_PLANNING, values, COL_ID + " = " + id, null);
    }

    public int supprimerSpectacleAvecId(int id){
        //Suppression d'un livre de la BDD grâce à l'ID
        return bdd.delete(TABLE_PLANNING, COL_ID + " = " + id, null);
    }

    public void videPlanning(){
        System.out.println("Vidage du planning");
        bdd.delete(TABLE_PLANNING,null,null);
    }

    /*public SpectacleSQLite getSpectacleWithTitre(String titre){
        //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
        Cursor c = bdd.query(TABLE_PLANNING, new String[] {COL_ID, COL_NOM, COL_DUREE,COL_HORAIRE}, COL_NOM + " LIKE \"" + titre +"\"", null, null, null, null);
        //return cursorToSpectacle(c);
    }*/

    public List<SpectacleSQLite> ListeToutSpectacleSQLite(){
        // Recuperer la liste des spectacles de la base SQLite
        Cursor c = bdd.rawQuery("SELECT * FROM table_planning ORDER BY ID_SPECT",null);
        return cursorToSpectacle(c);

    }

    //Cette méthode permet de convertir un cursor en un spectacle
    private List<SpectacleSQLite> cursorToSpectacle(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;
        List<SpectacleSQLite> lspectacle = new ArrayList<>();
        //Sinon on se place sur le premier élément
        for(int i = 0; i < c.getCount();i++){
            c.move(1);
            //On créé un livre
            SpectacleSQLite Spectacle = new SpectacleSQLite();
            //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
            Spectacle.setIdSpect(c.getInt(NUM_COL_ID));
            Spectacle.setNomSpect(c.getString(NUM_COL_NOM));
            Spectacle.setDureeSpect(c.getInt(NUM_COL_DUREE));
            Spectacle.setHoraireSpect(c.getString(NUM_COL_HORAIRE));
            lspectacle.add(Spectacle);
        }

        //On ferme le cursor
        c.close();
        //On retourne le spectacle
        return lspectacle;
    }
}
