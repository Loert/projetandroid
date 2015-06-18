package com.example.user.adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.entities.HoraireSpectacle;
import com.example.user.entities.Spectacle;
import com.example.user.entities.SpectacleSQLite;
import com.example.user.puydufoubatoto.R;
import com.example.user.puydufoubatoto.SpectacleBDD;
import com.example.user.puydufoubatoto.ajouterspectacle;
import com.example.user.webservices.WebServiceSpectacles;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ipSo on 16/06/2015.
 */
public class ArrayHoraireSpectacleAdapter extends ArrayAdapter {
    List<Spectacle> mSpectacles;
    LayoutInflater mInflater;

    public ArrayHoraireSpectacleAdapter(Context context, List<Spectacle> lSpectacles) {
        super(context, R.layout.activity_ajouterspectacle, lSpectacles);
        this.mSpectacles = lSpectacles;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View rowView = mInflater.inflate(R.layout.listesspectaclehoraire, null);
        Spectacle spectacle = mSpectacles.get(position);
        TextView titre = (TextView) rowView.findViewById(R.id.titreSpectacleMonPlannig);
        titre.setText(spectacle.getNom());
        TextView duree = (TextView) rowView.findViewById(R.id.dureeSpectacleMonPlanning);
        duree.setText(spectacle.getDuree() + " minutes");
        return rowView;
    }
}
