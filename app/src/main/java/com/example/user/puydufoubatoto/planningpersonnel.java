package com.example.user.puydufoubatoto;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.adapter.ArrayPlanningPerso;
import com.example.user.adapter.ArraySpectaclesAdapter;
import com.example.user.entities.SpectacleSQLite;

import java.util.List;


public class planningpersonnel extends ActionBarActivity {
    ListView listPlanning = null;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planningpersonnel);
        listPlanning = (ListView) findViewById(R.id.PlanningPerso);
        Button AjoutSpect  = (Button) findViewById(R.id.AjoutSpectacle);
        AjoutSpect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(planningpersonnel.this, ajouterspectacle.class);
                startActivity(intent);
            }
        });
        SpectacleBDD spectacleBdd = new SpectacleBDD(this);
        spectacleBdd.open();
        List<SpectacleSQLite> spectacleFromBdd = spectacleBdd.ListeToutSpectacleSQLite();
        spectacleBdd.close();
        listPlanning.setAdapter(new ArrayPlanningPerso(getApplicationContext(), spectacleFromBdd));
    }
}
