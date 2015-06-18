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

import com.example.user.adapter.ArrayPlanningOptimiseAdapter;
import com.example.user.entities.HoraireSpectacle;
import com.example.user.entities.Spectacle;
import com.example.user.webservices.WebServicePlanning;

import java.util.ArrayList;
import java.util.List;


public class SpectacleAVenir extends ActionBarActivity implements AdapterView.OnItemClickListener {
    ListView listeSpectacles = null;
    List<HoraireSpectacle> listeHoraires = new ArrayList<>();
    int idSpectacle = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spectacle_avenir);
        listeSpectacles = (ListView) findViewById(R.id.listeProchainsSpectacles);
        listeSpectacles.setOnItemClickListener(this);
        AsyncCallSpectacleAVenir task2 = new AsyncCallSpectacleAVenir();
        task2.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_spectacle_avenir, menu);
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
        Intent i = new Intent(SpectacleAVenir.this,FicheSpectacle.class);
        i.putExtra("idSpectacle",listeHoraires.get(position).getIdSpectacle());
        startActivity(i);
    }

    private class AsyncCallSpectacleAVenir extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            //Invoke webservice
            listeHoraires = WebServicePlanning.invokeSpectacleAVenir("getSpectaclesAVenir");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //Set response
            listeSpectacles.setAdapter(new ArrayPlanningOptimiseAdapter(getApplicationContext(),listeHoraires));
        }
    }
}
