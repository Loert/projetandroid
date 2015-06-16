package com.example.user.puydufoubatoto;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Home extends ActionBarActivity implements View.OnClickListener{
    private Button bPlan = null;
    private Button bListeSpectacles = null;
    private Button bListeServices = null;
    private Button bPlanning = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bPlan = (Button) findViewById(R.id.BoutonVoirPlan);
        bListeSpectacles = (Button) findViewById(R.id.BoutonListeSpectacles);
        bListeServices = (Button) findViewById(R.id.BoutonVoirServices);
        bPlan.setOnClickListener(this);
        bListeSpectacles.setOnClickListener(this);
        bListeServices.setOnClickListener(this);

        bPlanning = (Button) findViewById(R.id.BoutonPlanning);
        bPlanning.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
        if(view.getId() == R.id.BoutonVoirPlan){
            Intent i = new Intent(Home.this,Plan.class);
            startActivity(i);
        }
        else if(view.getId() == R.id.BoutonListeSpectacles){
            Intent i = new Intent(Home.this,ListeSpectacles.class);
            startActivity(i);
        }
        else if(view.getId() == R.id.BoutonPlanning){
            Intent i = new Intent(Home.this,planningpersonnel.class);
            startActivity(i);
        }
        else if(view.getId() == R.id.BoutonVoirServices){
            Intent i = new Intent(Home.this,ListeRestaurant.class);
            startActivity(i);
        }
    }
}
