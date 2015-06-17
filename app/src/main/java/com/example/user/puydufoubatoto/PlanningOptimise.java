package com.example.user.puydufoubatoto;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.user.adapter.ArrayPlanningOptimiseAdapter;
import com.example.user.adapter.ArraySpectaclesAdapter;
import com.example.user.entities.HoraireSpectacle;
import com.example.user.webservices.WebServicePlanning;
import com.example.user.webservices.WebServiceSpectacles;

import java.util.ArrayList;
import java.util.List;


public class PlanningOptimise extends ActionBarActivity {
    List<HoraireSpectacle> listeHoraires = new ArrayList<>();
    ListView lPlanning = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning_optimise);
        lPlanning = (ListView) findViewById(R.id.listePlanningOptimise);
        AsyncCallPlanningOpti task2 = new AsyncCallPlanningOpti();
        task2.execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_planning_optimise, menu);
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
            lPlanning.setAdapter(new ArrayPlanningOptimiseAdapter(getApplicationContext(),listeHoraires));
        }
    }
}
