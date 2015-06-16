package com.example.user.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.user.entities.HoraireSpectacle;
import com.example.user.entities.Spectacle;
import com.example.user.puydufoubatoto.R;

import java.util.List;

/**
 * Created by USER on 16/06/2015.
 */
public class ArrayProchainsHorairesAdapter extends ArrayAdapter {
    List<HoraireSpectacle> mHorairesSpectacle;
    LayoutInflater mInflater;

    public ArrayProchainsHorairesAdapter(Context context,List<HoraireSpectacle> lHorairesSpectacles) {
        super(context, R.layout.listespectacle, lHorairesSpectacles);
        this.mHorairesSpectacle = lHorairesSpectacles;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //creation de lobjet View a partir de la ressource Layout
        View rowView = mInflater.inflate(R.layout.listeprochainshoraires, null);

        //recuperation du document identifie par sa position dans la liste
        HoraireSpectacle horaireS = mHorairesSpectacle.get(position);

        //Valorisation du Texte1 de la Vue avec le nom du document
        TextView nameView = (TextView) rowView.findViewById(R.id.horaireSpectacle);
        nameView.setText(horaireS.getHoraire());

        //Valorisation du Texte2 avec la taille du document
        return rowView;
    }


}
