package com.example.user.puydufoubatoto;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.adapter.ArrayProchainsHorairesAdapter;
import com.example.user.entities.HoraireSpectacle;
import com.example.user.entities.Spectacle;
import com.example.user.entities.SpectacleSQLite;
import com.example.user.webservices.WebServiceSpectacles;

import java.util.ArrayList;
import java.util.List;


public class SelectionneHorairePlanningPerso extends ActionBarActivity implements AdapterView.OnItemClickListener {
    List<HoraireSpectacle> mHoraire = null;
    ListView listHoraire = null;
    Spectacle mSpectacles = null;
    int idSpectacle = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectionne_horaire_planning_perso);
        Intent i = getIntent();
        idSpectacle = i.getIntExtra("idSpectacle", 0);
        listHoraire = (ListView) findViewById(R.id.listeHoraireSpectacle);
        AsyncCallHorairesSpectacle task2 = new AsyncCallHorairesSpectacle();
        task2.execute();
        listHoraire.setOnItemClickListener(this);
        AsyncCallSpectacle task = new AsyncCallSpectacle();
        task.execute();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_selectionne_horaire_planning_perso, menu);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Création d'une instance de ma classe SpectaclesBDD
        SpectacleBDD spectacleBDD = new SpectacleBDD(this);
        spectacleBDD.open();
        SpectacleSQLite nouveauspectacle = new SpectacleSQLite();
        nouveauspectacle.setNomSpect(mSpectacles.getNom());
        nouveauspectacle.setDureeSpect(mSpectacles.getDuree());
        nouveauspectacle.setHoraireSpect(mHoraire.get(position).getHoraire());
        spectacleBDD.ajouterSpectacle(nouveauspectacle);
        Toast.makeText(this, "Le spectacle a bien été ajouté a votre planning", Toast.LENGTH_SHORT).show();
        this.finish();
    }

    private class AsyncCallHorairesSpectacle extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            //Invoke webservice
            mHoraire = WebServiceSpectacles.HoraireSpectacle(idSpectacle, "getListeHoraireFromSpectacleId");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            List<String> lsHoraires = new ArrayList<String>();
            for(int i = 0; i < mHoraire.size();i++){
                lsHoraires.add(mHoraire.get(i).getHoraire());
            }
            listHoraire.setAdapter(new ArrayProchainsHorairesAdapter(getApplicationContext(),mHoraire));
        }
    }

    private class AsyncCallSpectacle extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            //Invoke webservice
            mSpectacles = WebServiceSpectacles.invokeSpectacle(idSpectacle, "getSpectacle");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //Set response
        }
    }
}
