package com.example.user.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.user.entities.Spectacle;
import com.example.user.puydufoubatoto.R;

import java.util.List;

/**
 * Created by USER on 14/06/2015.
 */
public class ArraySpectaclesAdapter extends ArrayAdapter {
    List<Spectacle> mSpectacles;
    LayoutInflater mInflater;
    public ArraySpectaclesAdapter(Context context,List<Spectacle> lSpectacles) {
        super(context, R.layout.listespectacle, lSpectacles);
        this.mSpectacles = lSpectacles;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //creation de lobjet View a partir de la ressource Layout
        View rowView = mInflater.inflate(R.layout.listespectacle, null);

        //recuperation du document identifie par sa position dans la liste
        Spectacle spectacle = mSpectacles.get(position);

        //Valorisation du Texte1 de la Vue avec le nom du document
        TextView nameView = (TextView) rowView.findViewById(R.id.name);
        nameView.setText(spectacle.getNom());

        //Valorisation du Texte2 avec la taille du document
        TextView sizeView = (TextView) rowView.findViewById(R.id.size);
        sizeView.setText(String.valueOf(spectacle.getDuree()) + " minutes");
        return rowView;
    }
}
