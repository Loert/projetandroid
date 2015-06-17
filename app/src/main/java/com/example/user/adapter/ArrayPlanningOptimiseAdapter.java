package com.example.user.adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.user.entities.HoraireSpectacle;
import com.example.user.entities.Spectacle;
import com.example.user.puydufoubatoto.R;
import com.example.user.webservices.WebServiceSpectacles;

import java.util.List;

/**
 * Created by USER on 17/06/2015.
 */
public class ArrayPlanningOptimiseAdapter extends ArrayAdapter {
    List<HoraireSpectacle> mHoraires;
    LayoutInflater mInflater;
    Spectacle resultatSpectacle = null;
    int IdSpectacle = 0;
    TextView nameView = null;

    public ArrayPlanningOptimiseAdapter(Context context,List<HoraireSpectacle> lHoraires) {
        super(context, R.layout.activity_planning_optimise, lHoraires);
        this.mHoraires = lHoraires;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //creation de lobjet View a partir de la ressource Layout
        View rowView = mInflater.inflate(R.layout.listeplanningoptimise, null);

        //recuperation du document identifie par sa position dans la liste
        HoraireSpectacle horaireSpectacle = mHoraires.get(position);
        //Valorisation du Texte1 de la Vue avec le nom du document
        nameView = (TextView) rowView.findViewById(R.id.nomSpectaclePlanningOptimise);
        //nameView.setText(horaireSpectacle.getNom());
        IdSpectacle = horaireSpectacle.getIdSpectacle();
        AsyncCallSpectacle task2 = new AsyncCallSpectacle();
        task2.execute();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        nameView.setText(resultatSpectacle.getNom());

        TextView sizeView = (TextView) rowView.findViewById(R.id.horaireSpectaclePlanningOptimise);
        if(sizeView != null){
            sizeView.setText(horaireSpectacle.getHoraire());
        }
        return rowView;
    }

    private class AsyncCallSpectacle extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            //Invoke webservice
            resultatSpectacle = WebServiceSpectacles.invokeSpectacle(IdSpectacle, "getSpectacle");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //Set response

        }
    }
}
