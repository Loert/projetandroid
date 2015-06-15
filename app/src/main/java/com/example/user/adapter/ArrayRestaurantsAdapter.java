package com.example.user.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.user.entities.Restaurant;
import com.example.user.entities.Spectacle;
import com.example.user.puydufoubatoto.R;

import java.util.List;

/**
 * Created by USER on 15/06/2015.
 */
public class ArrayRestaurantsAdapter extends ArrayAdapter{
    List<Restaurant> mRestaurants;
    LayoutInflater mInflater;
    public ArrayRestaurantsAdapter(Context context, List<Restaurant> lRestaurant) {
        super(context, R.layout.listerestaurant, lRestaurant);
        this.mRestaurants = lRestaurant;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //creation de lobjet View a partir de la ressource Layout
        View rowView = mInflater.inflate(R.layout.listerestaurant, null);

        //recuperation du document identifie par sa position dans la liste
        Restaurant restaurant = mRestaurants.get(position);

        //Valorisation du Texte1 de la Vue avec le nom du document
        TextView nameView = (TextView) rowView.findViewById(R.id.nomResto);
        nameView.setText(restaurant.getNom());
        return rowView;
    }
}
