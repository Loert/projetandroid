package com.example.user.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.user.entities.HoraireSpectacle;
import com.example.user.entities.Spectacle;
import com.example.user.entities.SpectacleSQLite;
import com.example.user.puydufoubatoto.R;
import com.example.user.puydufoubatoto.ajouterspectacle;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by ipSo on 18/06/2015.
 */
public class ArrayPlanningPerso extends ArrayAdapter {

    List<SpectacleSQLite> mPlanningPerso = null;
    LayoutInflater mInflater;
    TextView nomSpec = null;
    TextView horSpec = null;

    public ArrayPlanningPerso(Context context, List<SpectacleSQLite> lSpectaclePerso) {
        super(context, R.layout.activity_planningpersonnel, lSpectaclePerso);
        this.mPlanningPerso = lSpectaclePerso;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //creation de lobjet View a partir de la ressource Layout
        final View rowView = mInflater.inflate(R.layout.listespectacleplanningperso, null);
        nomSpec = (TextView) rowView.findViewById(R.id.SpectacleNomPlanningPerso);
        horSpec = (TextView) rowView.findViewById(R.id.HoraireSpectaclePlanningPerso);
        nomSpec.setText(mPlanningPerso.get(position).getNomSpect());
        horSpec.setText(mPlanningPerso.get(position).getHoraireSpect());
        //recuperation du document identifie par sa position dans la liste
        return rowView;
    }
}