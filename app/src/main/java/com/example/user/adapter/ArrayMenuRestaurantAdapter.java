package com.example.user.adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.user.entities.MenuRestaurant;
import com.example.user.entities.Restaurant;
import com.example.user.puydufoubatoto.R;
import com.example.user.webservices.WebServiceSpectacles;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by USER on 18/06/2015.
 */
public class ArrayMenuRestaurantAdapter extends ArrayAdapter {
    List<MenuRestaurant> mMenuRestaurants;
    LayoutInflater mInflater;
    MenuRestaurant menurestaurant = null;
    public ArrayMenuRestaurantAdapter(Context context, List<MenuRestaurant> lMenuRestaurant) {
        super(context, R.layout.listemenu, lMenuRestaurant);
        this.mMenuRestaurants = lMenuRestaurant;
        mInflater = LayoutInflater.from(context);
        System.out.println("Je passe dans l'adapter");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //creation de lobjet View a partir de la ressource Layout
        View rowView = mInflater.inflate(R.layout.listemenu, null);

        //recuperation du document identifie par sa position dans la liste
        menurestaurant = mMenuRestaurants.get(position);

        TextView nameView = (TextView) rowView.findViewById(R.id.titreMenu);
        nameView.setText(menurestaurant.getTitre());
        TextView descriptionView = (TextView) rowView.findViewById(R.id.descriptionMenu);
        descriptionView.setText(menurestaurant.getDescription());
        TextView prixView = (TextView) rowView.findViewById(R.id.prixMenu);
        prixView.setText(menurestaurant.getPrix() + " €");
        return rowView;
    }

}
