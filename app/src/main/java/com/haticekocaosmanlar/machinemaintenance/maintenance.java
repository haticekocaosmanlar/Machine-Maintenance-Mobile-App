package com.haticekocaosmanlar.machinemaintenance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class maintenance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance);


    }

    public void haftalik(View view){
        Intent intent = new Intent(getApplicationContext(),weekly.class);
        startActivity(intent);
    }
    public void aylik(View view){
        Intent intent = new Intent(getApplicationContext(),monthly.class);
        startActivity(intent);
    }
    public void ucaylik(View view){
        Intent intent = new Intent(getApplicationContext(),threemonthly.class);
        startActivity(intent);
    }
    public void altiaylik(View view){
        //Intent intent = new Intent(getApplicationContext(),sixmonthly.class);
        //startActivity(intent);
    }
    public void yillik(View view){
        //Intent intent = new Intent(getApplicationContext(),yearly.class);
        //startActivity(intent);
    }
    public void ucyillik(View view){
        //Intent intent = new Intent(getApplicationContext(),threeyearly.class);
        //startActivity(intent);
    }


}
