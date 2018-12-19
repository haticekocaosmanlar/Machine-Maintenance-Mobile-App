package com.haticekocaosmanlar.machinemaintenance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MachineDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_details);

        TextView itemN = (TextView) findViewById(R.id.machineItemN);
        TextView name = (TextView) findViewById(R.id.detailMachineName);
        ImageView imageView = (ImageView) findViewById(R.id.detailMachineImage);
        ImageView schemaView = (ImageView) findViewById(R.id.schemaImageView);

        Intent intent = getIntent();
        String item= intent.getExtras().getString("itn");
        String machineName=intent.getExtras().getString("mn");
        String URL=intent.getExtras().getString("url");
        String schemaURL = intent.getExtras().getString("surl");

        itemN.setText(item);
        name.setText(machineName);
        Picasso.get().load(URL).into(imageView);
        Picasso.get().load(schemaURL).into(schemaView);

    }
}
