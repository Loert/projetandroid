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

import com.example.user.adapter.ArraySpectaclesAdapter;
import com.example.user.entities.Spectacle;
import com.example.user.webservices.WebServiceSpectacles;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class FicheSpectacle extends ActionBarActivity implements View.OnClickListener {
    TextView titre = null;
    TextView description = null;
    TextView nombreacteurs = null;
    TextView datecreation = null;
    Spectacle resultatSpectacle = null;
    int IdSpectacle = 0;
    Button bNotation = null;
    RatingBar barrenote = null;
    boolean dejaNote = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_spectacle);
        titre = (TextView) findViewById(R.id.titreSpectacle);
        description = (TextView) findViewById(R.id.descriptionSpectacle);
        nombreacteurs = (TextView) findViewById(R.id.nbacteursSpectacle);
        datecreation = (TextView) findViewById(R.id.datecreationSpectacle);
        barrenote = (RatingBar) findViewById(R.id.notationBar);
        Intent retourIntent = getIntent();
        IdSpectacle = retourIntent.getIntExtra("idSpectacle", 0);
        bNotation = (Button) findViewById(R.id.BoutonEnregistreNoteSpectacle);
        bNotation.setOnClickListener(this);
        AsyncCallSpectacle task = new AsyncCallSpectacle();
        task.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fiche_spectacle, menu);
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
        if(view.getId() == R.id.BoutonEnregistreNoteSpectacle){
            if(!this.dejaNote){
                AsyncCallNoteSpectacle taskNoteSpectacle = new AsyncCallNoteSpectacle();
                taskNoteSpectacle.execute();
            }
        }
    }

    private class AsyncCallSpectacle extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            //Invoke webservice
            resultatSpectacle = WebServiceSpectacles.invokeSpectacle(IdSpectacle,"getSpectacle");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //Set response
            titre.setText(resultatSpectacle.getNom());
            description.setText(resultatSpectacle.getDescription());
            nombreacteurs.setText(resultatSpectacle.getNombre_acteurs() + " acteurs");
            datecreation.setText("Date de création : "+ resultatSpectacle.getDate_creation());
        }
    }

    private class AsyncCallNoteSpectacle extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            //Invoke webservice
            WebServiceSpectacles.invokeEnregistreNoteSpectacle(IdSpectacle,(int)barrenote.getRating(), "noteSpectacle");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //Set response
            dejaNote = true;
            bNotation.setEnabled(false);
        }
    }
}
