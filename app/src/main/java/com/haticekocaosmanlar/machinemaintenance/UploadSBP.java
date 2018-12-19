package com.haticekocaosmanlar.machinemaintenance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class UploadSBP extends AppCompatActivity {

    EditText subject;
    EditText unit;
    EditText sbpequipmant;
    EditText sbpitemNo;
    EditText sbpNo;
    EditText preparer;
    EditText sbpdate;
    EditText maintenanceTime;
    EditText state;
    EditText spare;
    EditText handtool;
    EditText aim;
    EditText operations;

    private FirebaseAuth mAuth;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_sbp);

        subject = (EditText) findViewById(R.id.subject);
        unit= (EditText) findViewById(R.id.unit);
        sbpequipmant = (EditText) findViewById(R.id.sbpequipmant);
        sbpitemNo= (EditText) findViewById(R.id.sbpitemNo);
        sbpNo= (EditText) findViewById(R.id.sbpNo);
        preparer= (EditText) findViewById(R.id.preparer);
        sbpdate= (EditText) findViewById(R.id.sbpdate);
        maintenanceTime= (EditText) findViewById(R.id.maintenanceTime);
        state= (EditText) findViewById(R.id.state);
        spare= (EditText) findViewById(R.id.spare);
        handtool= (EditText) findViewById(R.id.handtool);
        aim= (EditText) findViewById(R.id.aim);
        operations= (EditText) findViewById(R.id.operations);


        mAuth = FirebaseAuth.getInstance();  //KULLANICI için

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();  //sınıf adı vermek istiyorsan içine yazabilirsin
    }

    String maintenanceType;
    public void maintenanceType(View view){

        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.bt1:
                if (checked) {
                    maintenanceType = "Arızi Onarım";
                } else {
                    maintenanceType = "";
                }
            case R.id.bt2:
                if (checked) {
                    maintenanceType = "Periyodik Kontrol";
                } else {
                    maintenanceType = "";
                }
            case R.id.bt3:
                if (checked) {
                    maintenanceType = "Periyodik Değişim";
                } else {
                    maintenanceType = "";
                }
        }

    }

    String maintenanceFreq;
    public void maintenancefrequence(View view){

        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.bf1:
                if (checked) {
                    maintenanceFreq = "Haftalık";
                } else {
                    maintenanceFreq = "";
                }
            case R.id.bf2:
                if (checked) {
                    maintenanceFreq = "Aylık";
                } else {
                    maintenanceFreq = "";
                }
            case R.id.bf3:
                if (checked) {
                    maintenanceFreq = "3 Aylık";
                } else {
                    maintenanceFreq = "";
                }
            case R.id.bf4:
                if (checked) {
                    maintenanceFreq = "6 Aylık";
                } else {
                    maintenanceFreq = "";
                }
            case R.id.bf5:
                if (checked) {
                    maintenanceFreq = "1 Yıllık";
                } else {
                    maintenanceFreq = "";
                }
            case R.id.bf6:
                if (checked) {
                    maintenanceFreq = "3 Yıllık";
                } else {
                    maintenanceFreq = "";
                }
        }
    }

    UUID uuid;
    String uuidString;

    public void saveSBP(View view){

        FirebaseUser user = mAuth.getCurrentUser();  //login yapmış kullanıcının bilgilerini veriyor
        String userEmail = user.getEmail().toString();



        String usersubject = subject.getText().toString();
        String userunit = unit.getText().toString();
        String usersbpequipment= sbpequipmant.getText().toString();
        String usersbpitemNo = sbpitemNo.getText().toString();
        String usersbpNo=sbpNo.getText().toString();
        String userpreparer = preparer.getText().toString();
        String usersbpdate = sbpdate.getText().toString();
        String usermaintenanceTime = maintenanceTime.getText().toString();
        String userstate = state.getText().toString();
        String userspare = spare.getText().toString();
        String userhandtool = handtool.getText().toString();
        String useraim = aim.getText().toString();
        String useroperations = operations.getText().toString();


        uuid = UUID.randomUUID();  //unique id oluşturma
        uuidString = uuid.toString();

        myRef.child("SBP").child(uuidString).child("userEmail").setValue(userEmail);
        myRef.child("SBP").child(uuidString).child("subject").setValue(usersubject);
        myRef.child("SBP").child(uuidString).child("unit").setValue(userunit);
        myRef.child("SBP").child(uuidString).child("sbpequipment").setValue(usersbpequipment);
        myRef.child("SBP").child(uuidString).child("sbpitemNo").setValue(usersbpitemNo);
        myRef.child("SBP").child(uuidString).child("sbpNo").setValue(usersbpNo);
        myRef.child("SBP").child(uuidString).child("preparer").setValue(userpreparer);
        myRef.child("SBP").child(uuidString).child("sbpdate").setValue(usersbpdate);
        myRef.child("SBP").child(uuidString).child("maintenanceTime").setValue(usermaintenanceTime);
        myRef.child("SBP").child(uuidString).child("maintenanceType").setValue(maintenanceType);
        myRef.child("SBP").child(uuidString).child("maintenanceFreq").setValue(maintenanceFreq);
        myRef.child("SBP").child(uuidString).child("state").setValue(userstate);
        myRef.child("SBP").child(uuidString).child("spare").setValue(userspare);
        myRef.child("SBP").child(uuidString).child("handtool").setValue(userhandtool);
        myRef.child("SBP").child(uuidString).child("aim").setValue(useraim);
        myRef.child("SBP").child(uuidString).child("operations").setValue(useroperations);



        Intent intent = new Intent(getApplicationContext(),SBP.class);
        startActivity(intent);


    }
}
