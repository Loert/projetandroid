package com.example.user.puydufoubatoto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class SQLitePlanning extends SQLiteOpenHelper {

    private static final String TABLE_PLANNING = "table_planning";
    private static final String COL_ID = "ID_SPECT";
    private static final String COL_NOM = "NOM_SPECT";
    private static final String COL_DUREE = "DUREE_SPECT";
    private static final String COL_HORAIRE = "HORAIRE_SPECT";

    private static final String CREATE_BDD = "CREATE TABLE IF NOT EXISTS " + TABLE_PLANNING +" (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_NOM + " TEXT NOT NULL, "+
            COL_DUREE + " INTEGER NOT NULL, "+
            COL_HORAIRE + " TEXT NOT NULL);";

    public SQLitePlanning(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //on créé la table à partir de la requête écrite dans la variable CREATE_BDD
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //On peut fait ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
        //comme ça lorsque je change la version les id repartent de 0
        //db.execSQL("DROP TABLE " + TABLE_PLANNING + ";");
        onCreate(db);
    }
}
