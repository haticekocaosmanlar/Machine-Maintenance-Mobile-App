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

public class SBP extends AppCompatActivity {

    ArrayList<String> subjectFromFB;
    ArrayList<String> unitFromFB;
    ArrayList<String> sbpequipmentFromFB;
    ArrayList<String> sbpitemNoFromFB;
    ArrayList<String> sbpNoFromFB;
    ArrayList<String> preparerFromFB;
    ArrayList<String> sbpdateFromFB;
    ArrayList<String> maintenanceTimeFromFB;
    ArrayList<String> maintenanceTypeFromFB ;
    ArrayList<String> maintenanceFreqFromFB;
    ArrayList<String> stateFromFB;
    ArrayList<String> spareFromFB;
    ArrayList<String> handtoolFromFB;
    ArrayList<String> aimFromFB;
    ArrayList<String> operationsFromFB;



    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;

    ListView sbpList;

    sbpListAdapter adapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){ //menu için

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addsbp,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){  //seçenek seçilince ne olacağı
        if(item.getItemId()==R.id.add_sbp){

            Intent intent = new Intent(getApplicationContext(),UploadSBP.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sbp);

        subjectFromFB= new ArrayList<String>();
        unitFromFB= new ArrayList<String>();
        sbpequipmentFromFB= new ArrayList<String>();
        sbpitemNoFromFB= new ArrayList<String>();
        sbpNoFromFB= new ArrayList<String>();
        preparerFromFB= new ArrayList<String>();
        sbpdateFromFB= new ArrayList<String>();
        maintenanceTimeFromFB= new ArrayList<String>();
        maintenanceTypeFromFB = new ArrayList<String>();
        maintenanceFreqFromFB= new ArrayList<String>();
        stateFromFB= new ArrayList<String>();
        spareFromFB= new ArrayList<String>();
        handtoolFromFB= new ArrayList<String>();
        aimFromFB= new ArrayList<String>();
        operationsFromFB= new ArrayList<String>();

        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();

        sbpList = findViewById(R.id.sbp_list);

        adapter = new sbpListAdapter(subjectFromFB,sbpitemNoFromFB,this);  //adapterımızla post clasımız yani her şeyi birbirine bağladık

        sbpList.setAdapter(adapter);

        sbpList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

                Intent intent = new Intent(getApplicationContext(), sbpDetails.class);
                intent.putExtra("subject",subjectFromFB.get(pos));
                intent.putExtra("unit",unitFromFB.get(pos));
                intent.putExtra("sbpequipment",sbpequipmentFromFB.get(pos));
                intent.putExtra("sbpitemNo",sbpitemNoFromFB.get(pos));
                intent.putExtra("sbpNo",sbpNoFromFB.get(pos));
                intent.putExtra("preparer",preparerFromFB.get(pos));
                intent.putExtra("sbpdate",sbpdateFromFB.get(pos));
                intent.putExtra("maintenanceTime",maintenanceTimeFromFB.get(pos));
                intent.putExtra("maintenanceType",maintenanceTypeFromFB.get(pos));
                intent.putExtra("maintenanceFreq",maintenanceFreqFromFB.get(pos));
                intent.putExtra("state",stateFromFB.get(pos));
                intent.putExtra("spare",spareFromFB.get(pos));
                intent.putExtra("handtool",handtoolFromFB.get(pos));
                intent.putExtra("aim",aimFromFB.get(pos));
                intent.putExtra("operations",operationsFromFB.get(pos));
                startActivity(intent);
            }
        });
        getDataFromFirebase();
    }
    protected void getDataFromFirebase() {
        DatabaseReference newRef = firebaseDatabase.getReference("SBP");
        newRef.addValueEventListener(new ValueEventListener() {  //bir olay olduğunda ne olacak

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {  //database de değişiklik olursa ne yapsın
                //machineNameFromFB.clear(); //???????

                for (DataSnapshot ds : dataSnapshot.getChildren()) {  //datasnapsgotın tüm childrenlarını al

                    HashMap<String, String> hashMap = (HashMap<String, String>) ds.getValue();  //her bir datasnapshotın değerini verecek

                    //snapshattan istenilen key li değerleri alıyoruz

                    subjectFromFB.add(hashMap.get("subject"));
                    unitFromFB.add(hashMap.get("unit"));
                    sbpequipmentFromFB.add(hashMap.get("sbpequipment"));
                    sbpitemNoFromFB.add(hashMap.get("sbpitemNo"));
                    sbpNoFromFB.add(hashMap.get("sbpNo"));
                    preparerFromFB.add(hashMap.get("preparer"));
                    sbpdateFromFB.add(hashMap.get("sbpdate"));
                    maintenanceTimeFromFB.add(hashMap.get("maintenanceTime"));
                    maintenanceTypeFromFB.add(hashMap.get("maintenanceType"));
                    maintenanceFreqFromFB.add(hashMap.get("maintenanceFreq"));
                    stateFromFB.add(hashMap.get("state"));
                    spareFromFB.add(hashMap.get("spare"));
                    handtoolFromFB.add(hashMap.get("handtool"));
                    aimFromFB.add(hashMap.get("aim"));
                    operationsFromFB.add(hashMap.get("operations"));

                    adapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
}
