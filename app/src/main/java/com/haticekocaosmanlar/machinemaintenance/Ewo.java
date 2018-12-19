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

public class Ewo extends AppCompatActivity {


    ArrayList<String> isEmriFromFB;
    ArrayList<String> itemNoFromFB;
    ArrayList<String> equipmentFromFB;
    ArrayList<String> componentFromFB;
    ArrayList<String> breakdownDateFromFB;
    ArrayList<String> breakdownTypeFromFB;
    ArrayList<String> maintainerIDFromFB;
    ArrayList<String> solutionDescriptionFromFB;
    ArrayList<String> sparePartsFromFB;
    ArrayList<String> whatFromFB;
    ArrayList<String> whenFromFB;
    ArrayList<String> whereFromFB;
    ArrayList<String> whoFromFB;
    ArrayList<String> howmuchFromFB;
    ArrayList<String> howFromFB;
    ArrayList<String> reasonsFromFB;
    ArrayList<String> actionsFromFB;
    ArrayList<String> continuityFromFB;
    ArrayList<String> rootCauseFromFB;
    ArrayList<String> mCountFromFB;
    ArrayList<String> analystFromFB;
    ArrayList<String> dateFromFB;
    ArrayList<String> oCountFromFB;
    ArrayList<String> screenerFromFB;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;

    ListView ewoList;

    ewoListAdapter adapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){ //menu için

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addewo,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){  //seçenek seçilince ne olacağı
        if(item.getItemId()==R.id.add_ewo){

            Intent intent = new Intent(getApplicationContext(),UploadEwos.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ewo);

        isEmriFromFB = new ArrayList<String>();
        itemNoFromFB = new ArrayList<String>();
        equipmentFromFB= new ArrayList<String>();
        componentFromFB= new ArrayList<String>();
        breakdownDateFromFB= new ArrayList<String>();
        breakdownTypeFromFB= new ArrayList<String>();
        maintainerIDFromFB= new ArrayList<String>();
        solutionDescriptionFromFB= new ArrayList<String>();
        sparePartsFromFB= new ArrayList<String>();
        whatFromFB= new ArrayList<String>();
        whenFromFB= new ArrayList<String>();
        whereFromFB= new ArrayList<String>();
        whoFromFB= new ArrayList<String>();
        howmuchFromFB= new ArrayList<String>();
        howFromFB= new ArrayList<String>();
        reasonsFromFB= new ArrayList<String>();
        actionsFromFB= new ArrayList<String>();
        continuityFromFB= new ArrayList<String>();
        rootCauseFromFB=new ArrayList<String>();
        mCountFromFB= new ArrayList<String>();
        analystFromFB= new ArrayList<String>();
        dateFromFB= new ArrayList<String>();
        oCountFromFB= new ArrayList<String>();
        screenerFromFB= new ArrayList<String>();




        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();

        ewoList = findViewById(R.id.ewoList);

        adapter = new ewoListAdapter(isEmriFromFB,itemNoFromFB,this);  //adapterımızla post clasımız yani her şeyi birbirine bağladık

        ewoList.setAdapter(adapter);

        //CLİCK LİSTVİEW ?
        ewoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

                Intent intent = new Intent(getApplicationContext(), EWODetails.class);
                intent.putExtra("isEmri",isEmriFromFB.get(pos));
                intent.putExtra("itemNo",itemNoFromFB.get(pos));
                intent.putExtra("equipment",equipmentFromFB.get(pos));
                intent.putExtra("component",componentFromFB.get(pos));
                intent.putExtra("breakdownDate",breakdownDateFromFB.get(pos));
                intent.putExtra("breakdownType",breakdownTypeFromFB.get(pos));
                intent.putExtra("maintainerID",maintainerIDFromFB.get(pos));
                intent.putExtra("solutionDescription",solutionDescriptionFromFB.get(pos));
                intent.putExtra("spareParts",sparePartsFromFB.get(pos));
                intent.putExtra("what",whatFromFB.get(pos));
                intent.putExtra("when",whenFromFB.get(pos));
                intent.putExtra("where",whereFromFB.get(pos));
                intent.putExtra("who",whoFromFB.get(pos));
                intent.putExtra("howmuch",howmuchFromFB.get(pos));
                intent.putExtra("how",howFromFB.get(pos));
                intent.putExtra("reasons",reasonsFromFB.get(pos));
                intent.putExtra("actions",actionsFromFB.get(pos));
                intent.putExtra("continuity",continuityFromFB.get(pos));
                intent.putExtra("rootCause",rootCauseFromFB.get(pos));
                intent.putExtra("mCount",mCountFromFB.get(pos));
                intent.putExtra("analyst",analystFromFB.get(pos));
                intent.putExtra("date",dateFromFB.get(pos));
                intent.putExtra("oCount",oCountFromFB.get(pos));
                intent.putExtra("screener",screenerFromFB.get(pos));
                startActivity(intent);
            }
        });

        getDataFromFirebase();
    }

    protected void getDataFromFirebase() {
        DatabaseReference newRef = firebaseDatabase.getReference("EWO");
        newRef.addValueEventListener(new ValueEventListener() {  //bir olay olduğunda ne olacak

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {  //database de değişiklik olursa ne yapsın
                //machineNameFromFB.clear(); //???????

                for (DataSnapshot ds : dataSnapshot.getChildren()) {  //datasnapsgotın tüm childrenlarını al

                    HashMap<String, String> hashMap = (HashMap<String, String>) ds.getValue();  //her bir datasnapshotın değerini verecek

                    //snapshattan istenilen key li değerleri alıyoruz

                    isEmriFromFB.add(hashMap.get("isEmri"));
                    itemNoFromFB.add(hashMap.get("itemNo"));
                    equipmentFromFB.add(hashMap.get("equipment"));
                    componentFromFB.add(hashMap.get("component"));
                    breakdownDateFromFB.add(hashMap.get("breakdownDate"));
                    breakdownTypeFromFB.add(hashMap.get("breakdownType"));
                    maintainerIDFromFB.add(hashMap.get("maintainerID"));
                    solutionDescriptionFromFB.add(hashMap.get("solutionDescription"));
                    sparePartsFromFB.add(hashMap.get("spareParts"));
                    whatFromFB.add(hashMap.get("what"));
                    whenFromFB.add(hashMap.get("when"));
                    whereFromFB.add(hashMap.get("where"));
                    whoFromFB.add(hashMap.get("who"));
                    howmuchFromFB.add(hashMap.get("howmuch"));
                    howFromFB.add(hashMap.get("how"));
                    reasonsFromFB.add(hashMap.get("reasons"));
                    actionsFromFB.add(hashMap.get("actions"));
                    continuityFromFB.add(hashMap.get("continuity"));
                    rootCauseFromFB.add(hashMap.get("rootCause"));
                    mCountFromFB.add(hashMap.get("mCount"));
                    analystFromFB.add(hashMap.get("analyst"));
                    dateFromFB.add(hashMap.get("date"));
                    oCountFromFB.add(hashMap.get("oCount"));
                    screenerFromFB.add(hashMap.get("screener"));









                    adapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
}
