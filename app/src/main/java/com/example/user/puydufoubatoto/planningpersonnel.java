package com.example.user.puydufoubatoto;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.adapter.ArrayPlanningOptimiseAdapter;
import com.example.user.adapter.ArrayPlanningPerso;
import com.example.user.adapter.ArraySpectaclesAdapter;
import com.example.user.entities.HoraireSpectacle;
import com.example.user.entities.Spectacle;
import com.example.user.entities.SpectacleSQLite;
import com.example.user.webservices.WebServicePlanning;
import com.example.user.webservices.WebServiceSpectacles;

import java.util.ArrayList;
import java.util.List;


public class planningpersonnel extends ActionBarActivity implements View.OnClickListener {
    ListView listPlanning = null;
    Button VidePlanning = null;
    Button planOpti = null;
    List<HoraireSpectacle> listeHoraires = new ArrayList<>();
    List<Spectacle> listeSpectacles = new ArrayList<>();
    HoraireSpectacle enCours = null;
    Spectacle speEnCours = null;
    int idSpeEncours = 0;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planningpersonnel);
        listPlanning = (ListView) findViewById(R.id.PlanningPerso);
        Button AjoutSpect  = (Button) findViewById(R.id.AjoutSpectacle);
        planOpti = (Button) findViewById(R.id.BoutonGenererPlanningOptimise);
        planOpti.setOnClickListener(this);
        AjoutSpect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(planningpersonnel.this, ajouterspectacle.class);
                startActivity(intent);
            }
        });
        VidePlanning = (Button) findViewById(R.id.BoutonViderPlanningPerso);
        VidePlanning.setOnClickListener(this);
        SpectacleBDD spectacleBdd = new SpectacleBDD(this);
        spectacleBdd.open();
        List<SpectacleSQLite> spectacleFromBdd = spectacleBdd.ListeToutSpectacleSQLite();
        spectacleBdd.close();
        if(spectacleFromBdd != null)
            listPlanning.setAdapter(new ArrayPlanningPerso(getApplicationContext(), spectacleFromBdd));
        AsyncCallSpectacle tasks = new AsyncCallSpectacle();
        tasks.execute();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.BoutonViderPlanningPerso){
            SpectacleBDD spectacleBdd = new SpectacleBDD(this);
            spectacleBdd.open();
            spectacleBdd.videPlanning();
            spectacleBdd.close();
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
        else if(view.getId() == R.id.BoutonGenererPlanningOptimise){
            new AlertDialog.Builder(this).setIcon(R.drawable.notification_template_icon_bg).setTitle("Generer un planning optimisé").setMessage("Voulez vous vraiment générer un planning optimisé à partir de cette " +
                  "heure-ci ? Ceci supprimera votre ancien planning").setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getApplicationContext(),"Generation en cours",Toast.LENGTH_LONG).show();
                    SpectacleBDD spectacleBdd = new SpectacleBDD(getApplicationContext());
                    spectacleBdd.open();
                    spectacleBdd.videPlanning();
                    spectacleBdd.close();
                    AsyncCallPlanningOpti task2 = new AsyncCallPlanningOpti();
                    task2.execute();
                }
            }).setNegativeButton("Non",null).show();
        }
    }

    private class AsyncCallPlanningOpti extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            //Invoke webservice
            listeHoraires = WebServicePlanning.invokePlannningOptimiseHoraireSpectacle("calculPlanningOptimise");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //Set response
            System.out.println("Nombre horaires : "+listeHoraires.size());
            for(int i = 0;i<listeHoraires.size();i++){
                System.out.println("Horaire boucle : "+listeHoraires.get(i).getHoraire());
                enCours = listeHoraires.get(i);
                for(int j = 0;j<listeSpectacles.size();j++){
                    speEnCours = listeSpectacles.get(j);
                    if(speEnCours.getId() == enCours.getIdSpectacle()){
                        SpectacleBDD spectacleBDD = new SpectacleBDD(getApplicationContext());
                        spectacleBDD.open();
                        SpectacleSQLite nouveauspectacle = new SpectacleSQLite();
                        nouveauspectacle.setNomSpect(speEnCours.getNom());
                        nouveauspectacle.setDureeSpect(speEnCours.getDuree());
                        nouveauspectacle.setHoraireSpect(enCours.getHoraire());
                        spectacleBDD.ajouterSpectacle(nouveauspectacle);
                        spectacleBDD.close();
                        System.out.println("Ajout d'un spectacle dans la base");
                        //j = listeSpectacles.size();
                        break;
                    }
                }
            }
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }

    private class AsyncCallSpectacle extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            //Invoke webservice
            listeSpectacles = WebServiceSpectacles.invokeListeSpectacle("getListeSpectacle");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //Set response

        }
    }
}
