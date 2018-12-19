package com.haticekocaosmanlar.machinemaintenance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class weekly extends AppCompatActivity {

    ArrayList<String> weekpicfromFB;

    ImageView weeklypic;

    private StorageReference mStorageRef;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //menu için

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addweekly, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {  //seçenek seçilince ne olacağı
        if (item.getItemId() == R.id.add_weekly) {

            Intent intent = new Intent(getApplicationContext(), UploadWeekly.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly);

        weekpicfromFB = new ArrayList<String>();
        weeklypic = (ImageView) findViewById(R.id.weeklyView);

        mStorageRef = FirebaseStorage.getInstance().getReference();  //storageRef kullanarak storage'ımıza resim koyacağız

        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();
        //mAuth = FirebaseAuth.getInstance();  //KULLANICI için


        getDataFromFB();
    }

    protected void getDataFromFB() {

        DatabaseReference newRef = firebaseDatabase.getReference("Weekly");
        newRef.addValueEventListener(new ValueEventListener() {  //bir olay olduğunda ne olacak

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {  //database de değişiklik olursa ne yapsın
                //machineNameFromFB.clear(); //???????

                for (DataSnapshot ds : dataSnapshot.getChildren()) {  //datasnapsgotın tüm childrenlarını al

                    HashMap<String, String> hashMap = (HashMap<String, String>) ds.getValue();  //her bir datasnapshotın değerini verecek
                    String URL = hashMap.get("downloadURL");
                    Picasso.get().load(URL).into(weeklypic);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
