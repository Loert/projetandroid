package com.example.user.puydufoubatoto;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.adapter.ArrayRestaurantsAdapter;
import com.example.user.adapter.ArraySpectaclesAdapter;
import com.example.user.entities.Restaurant;
import com.example.user.entities.Spectacle;
import com.example.user.webservices.WebServiceRestaurants;
import com.example.user.webservices.WebServiceSpectacles;

import java.util.ArrayList;
import java.util.List;


public class ListeRestaurant extends ActionBarActivity implements AdapterView.OnItemClickListener {
    ListView list = null;
    List<Restaurant> listeRestaurants = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_restaurant);
        list = (ListView) findViewById(R.id.listeRestaurants);

        AsyncCallListeRestaurants task2 = new AsyncCallListeRestaurants();
        task2.execute();
        list.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_liste_restaurant, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Restaurant restaurant = (Restaurant)list.getAdapter().getItem(position);
        Intent i = new Intent(ListeRestaurant.this,FicheRestaurant.class);
        i.putExtra("idRestaurant",restaurant.getId());
        startActivity(i);
    }

    private class AsyncCallListeRestaurants extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            //Invoke webservice
            listeRestaurants = WebServiceRestaurants.invokeListeRestaurant("getListeRestaurant");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //Set response
            List<String> listeTitres = new ArrayList<String>();
            for(int i = 0;i < listeRestaurants.size();i++){
                listeTitres.add(listeRestaurants.get(i).getNom());
            }
            list.setAdapter(new ArrayRestaurantsAdapter(getApplicationContext(),listeRestaurants));
        }
    }
}
