package com.example.user.puydufoubatoto;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class planningpersonnel extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planningpersonnel);

        final Button AjoutSpect  = (Button) findViewById(R.id.AjoutSpectacle);
        AjoutSpect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(planningpersonnel.this, ajouterspectacle.class);
                startActivity(intent);
            }
        });

    }
}
