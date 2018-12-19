package com.haticekocaosmanlar.machinemaintenance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class FeedActivity extends AppCompatActivity {

    Button machineInfo;
    Button maintenance;
    Button sbpInfo;
    Button ewo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        machineInfo = (Button)findViewById(R.id.machineInfo);
        maintenance = (Button)findViewById(R.id.maintenance);

        sbpInfo = (Button)findViewById(R.id.sbpInfo);
        ewo = (Button)findViewById(R.id.ewo);

    }

    public void machineInfo(View view){
        Intent intent = new Intent(getApplicationContext(),machine.class);
        startActivity(intent);
    }

    public void maintenance(View view){
        Intent intent = new Intent(getApplicationContext(),maintenance.class);
        startActivity(intent);
    }

    public void ewo(View view){
        Intent intent = new Intent(getApplicationContext(),Ewo.class);
        startActivity(intent);

    }

    public void sbpinfo(View view){
        Intent intent = new Intent(getApplicationContext(),SBP.class);
        startActivity(intent);
    }


}
