package com.haticekocaosmanlar.machinemaintenance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;


public class machine extends AppCompatActivity {

    ArrayList<String> itemNoFromFB;
    ArrayList<String> machineNameFromFB;
    ArrayList<String> machineImageFromFB;
    ArrayList<String> schemaImageFromFB;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;

    ListView listView;

    PostClass adapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){ //menu için

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addmachine,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){  //seçenek seçilince ne olacağı
        if(item.getItemId()==R.id.add_machine){

            Intent intent = new Intent(getApplicationContext(),UploadMachine.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine);

        itemNoFromFB = new ArrayList<String>();
        machineNameFromFB = new ArrayList<String>();
        machineImageFromFB = new ArrayList<String>();
        schemaImageFromFB = new ArrayList<String>();

        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();

        listView = findViewById(R.id.machineList);

        adapter = new PostClass(machineNameFromFB,this);  //adapterımızla post clasımız yani her şeyi birbirine bağladık

        listView.setAdapter(adapter);


        //CLİCK LİSTVİEW ?
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

                Intent intent = new Intent(getApplicationContext(), MachineDetails.class);

                intent.putExtra("itn",itemNoFromFB.get(pos));
                intent.putExtra("mn",machineNameFromFB.get(pos));
                intent.putExtra("url",machineImageFromFB.get(pos));
                intent.putExtra("surl",schemaImageFromFB.get(pos));

                startActivity(intent);
            }
        });

        getDataFromFirebase();
    }

        //VERI ÇEKME

    protected void getDataFromFirebase() {

        DatabaseReference newRef = firebaseDatabase.getReference("Machines");
        newRef.addValueEventListener(new ValueEventListener() {  //bir olay olduğunda ne olacak

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {  //database de değişiklik olursa ne yapsın
                //machineNameFromFB.clear(); //???????

                for (DataSnapshot ds : dataSnapshot.getChildren()) {  //datasnapsgotın tüm childrenlarını al

                    HashMap<String, String> hashMap = (HashMap<String, String>) ds.getValue();  //her bir datasnapshotın değerini verecek
                    itemNoFromFB.add(hashMap.get("itemNo"));
                    machineNameFromFB.add(hashMap.get("machineName"));  //snapshattan istenilen key li değerleri alıyoruz
                    machineImageFromFB.add(hashMap.get("downloadURL"));
                    schemaImageFromFB.add(hashMap.get("schemaURL"));
                    adapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}


