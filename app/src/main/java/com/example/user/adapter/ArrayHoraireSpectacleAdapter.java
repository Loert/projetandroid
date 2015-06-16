package com.example.user.adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.user.entities.HoraireSpectacle;
import com.example.user.entities.Spectacle;
import com.example.user.puydufoubatoto.R;
import com.example.user.puydufoubatoto.ajouterspectacle;
import com.example.user.webservices.WebServiceSpectacles;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ipSo on 16/06/2015.
 */
public class ArrayHoraireSpectacleAdapter extends ArrayAdapter {
    List<HoraireSpectacle> mHoraire = null;
    List<Spectacle> mSpectacles;
    List<ajouterspectacle> ListeHoraire;
    LayoutInflater mInflater;
    int idSpectacle = 0;
    Spinner ComboBox;
    boolean elementTrouves = false;
    public ArrayHoraireSpectacleAdapter(Context context, List<Spectacle> lSpectacles) {
        super(context, R.layout.activity_ajouterspectacle, lSpectacles);
        this.mSpectacles = lSpectacles;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        elementTrouves = false;
        mHoraire = null;
        //creation de lobjet View a partir de la ressource Layout
        View rowView = mInflater.inflate(R.layout.listesspectaclehoraire, null);

        //recuperation du document identifie par sa position dans la liste
        Spectacle spectacle = mSpectacles.get(position);
        TextView titre = (TextView) rowView.findViewById(R.id.titreSpectacleMonPlannig);
        titre.setText(spectacle.getNom());
        TextView duree = (TextView) rowView.findViewById(R.id.dureeSpectacleMonPlanning);
        duree.setText(spectacle.getDuree() + " minutes");
        idSpectacle = spectacle.getId();
        ComboBox  = (Spinner) rowView.findViewById(R.id.comboHoraires);
        AsyncCallHorairesSpectacle task2 = new AsyncCallHorairesSpectacle();
        task2.execute();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> lsHoraires = new ArrayList<String>();
        for(int i = 0; i < mHoraire.size();i++){
            lsHoraires.add(mHoraire.get(i).getHoraire());
        }
        ArrayAdapter<HoraireSpectacle> adaptateur = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, lsHoraires);
        adaptateur.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ComboBox.setAdapter(adaptateur);
        return rowView;
    }



    private class AsyncCallHorairesSpectacle extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            //Invoke webservice
            mHoraire = WebServiceSpectacles.HoraireSpectacle(idSpectacle,"getListeHoraireFromSpectacleId");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

        }
    }
}
