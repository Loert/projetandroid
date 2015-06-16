package com.example.user.puydufoubatoto;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.user.entities.Restaurant;
import com.example.user.webservices.WebServiceRestaurants;
import com.example.user.webservices.WebServiceSpectacles;


public class FicheRestaurant extends ActionBarActivity implements View.OnClickListener {
    TextView nom = null;
    TextView description = null;
    Restaurant resultatRestaurant = null;
    RatingBar barreNote = null;
    Button enregistreNote = null;
    int idRestaurant = 0;
    boolean dejaNote = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_restaurant);
        nom = (TextView) findViewById(R.id.nomRestaurant);
        description = (TextView) findViewById(R.id.descriptionRestaurant);
        barreNote = (RatingBar) findViewById(R.id.BarreNoteRestaurant);
        enregistreNote = (Button) findViewById(R.id.BoutonEnregistreNoteRestaurant);
        enregistreNote.setOnClickListener(this);
        Intent retourIntent = getIntent();
        idRestaurant = retourIntent.getIntExtra("idRestaurant", 0);
        //System.out.println(idRestaurant);
        AsyncCallRestaurant task = new AsyncCallRestaurant();
        task.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fiche_restaurant, menu);
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
    public void onClick(View view) {
        if(view.getId() == R.id.BoutonEnregistreNoteRestaurant){
            AsyncCallNoteRestaurant taskNoteSpectacle = new AsyncCallNoteRestaurant();
            taskNoteSpectacle.execute();
        }
    }

    private class AsyncCallRestaurant extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            //Invoke webservice
            resultatRestaurant = WebServiceRestaurants.invokeRestaurant(idRestaurant, "getRestaurant");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //Set response
            nom.setText(resultatRestaurant.getNom());
            description.setText(resultatRestaurant.getDescription());
        }
    }

    private class AsyncCallNoteRestaurant extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            //Invoke webservice
            WebServiceRestaurants.invokeEnregistreNoteRestaurant(idRestaurant,(int)barreNote.getRating(), "noteRestaurant");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //Set response
            dejaNote = true;
            enregistreNote.setEnabled(false);
        }
    }
}
