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

public class UploadEwos extends AppCompatActivity {

    EditText isEmri;
    EditText itemNo;
    EditText equipment;
    EditText component;
    EditText breakdownDate;
    EditText breakdownType;
    EditText maintainerID;
    EditText solutionDescription;
    EditText spareParts;
    EditText what;
    EditText when;
    EditText where;
    EditText who;
    EditText howmuch;
    EditText how;
    EditText reasons;
    EditText actions;
    EditText continuity;
    EditText mCount;
    EditText analyst;
    EditText date;
    EditText oCount;
    EditText screener;


    private FirebaseAuth mAuth;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_ewos);

        isEmri = (EditText) findViewById(R.id.isEmri);
        itemNo= (EditText) findViewById(R.id.machineItemN);
        equipment = (EditText) findViewById(R.id.equipment);
        component= (EditText) findViewById(R.id.component);
        breakdownDate= (EditText) findViewById(R.id.breakdownDate);
        breakdownType= (EditText) findViewById(R.id.breakdownType);
        maintainerID= (EditText) findViewById(R.id.manitainerID);
        solutionDescription= (EditText) findViewById(R.id.solutionDescription);
        spareParts= (EditText) findViewById(R.id.spareParts);
        what= (EditText) findViewById(R.id.what);
        when= (EditText) findViewById(R.id.when);
        where= (EditText) findViewById(R.id.where);
        who= (EditText) findViewById(R.id.who);
        howmuch= (EditText) findViewById(R.id.howmuch);
        how= (EditText) findViewById(R.id.how);
        reasons= (EditText) findViewById(R.id.reasons);
        actions= (EditText) findViewById(R.id.actions);
        continuity= (EditText) findViewById(R.id.continuity);
        mCount= (EditText) findViewById(R.id.mCount);
        analyst= (EditText) findViewById(R.id.analyst);
        date= (EditText) findViewById(R.id.date);
        oCount= (EditText) findViewById(R.id.oCount);
        screener= (EditText) findViewById(R.id.screener);

        mAuth = FirebaseAuth.getInstance();  //KULLANICI için

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();  //sınıf adı vermek istiyorsan içine yazabilirsin

    }

    String rootCauses;
    public void rootCause(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.radioButton3:
                if(checked){
                    rootCauses="Dış etkenler";
                }else{
                    rootCauses="";
                }
            case R.id.radioButton4:
                if(checked){
                    rootCauses="Bakımcının Eksik Becerisi";
                }else{
                    rootCauses="";
                }
            case R.id.radioButton5:
                if(checked){
                    rootCauses="Üretici Firma Kaynaklı Tasarım Hatası";
                }else{
                    rootCauses="";
                }
            case R.id.radioButton6:
                if(checked){
                    rootCauses="Profesyonel Bakım Eksikliği";
                }else{
                    rootCauses="";
                }
            case R.id.radioButton7:
                if(checked){
                    rootCauses="Uygun Olmayan Operasyon";
                }else{
                    rootCauses="";
                }
            case R.id.radioButton8:
                if(checked){
                    rootCauses="Temel Şartların Korunmaması(Temizlik, Yağlama vs.)";
                }else{
                    rootCauses="";
                }

        }

    }

    UUID uuid;
    String uuidString;
    public void saveEWO(View view){

        FirebaseUser user = mAuth.getCurrentUser();  //login yapmış kullanıcının bilgilerini veriyor
        String userEmail = user.getEmail().toString();

        String userisEmri = isEmri.getText().toString();
        String useritemNo = itemNo.getText().toString();
        String userequipment= equipment.getText().toString();
        String usercomponent = component.getText().toString();
        String userbreakdownDate=breakdownDate.getText().toString();
        String userbreakdownType = breakdownType.getText().toString();
        String usermaintainerID = maintainerID.getText().toString();
        String usersolutionDescription = solutionDescription.getText().toString();
        String userspareParts = spareParts.getText().toString();
        String userwhat = what.getText().toString();
        String userwhen = when.getText().toString();
        String userwhere = where.getText().toString();
        String userwho = who.getText().toString();
        String userhowmuch = howmuch.getText().toString();
        String userhow=how.getText().toString();
        String userreasons=reasons.getText().toString();
        String useractions=actions.getText().toString();
        String usercontinuity= continuity.getText().toString();
        String usermCount =mCount.getText().toString();
        String useranalyst= analyst.getText().toString();
        String userdate = date.getText().toString();
        String useroCount = oCount.getText().toString();
        String userscreener=screener.getText().toString();


        uuid = UUID.randomUUID();  //unique id oluşturma
        uuidString = uuid.toString();

        myRef.child("EWO").child(uuidString).child("userEmail").setValue(userEmail);
        myRef.child("EWO").child(uuidString).child("isEmri").setValue(userisEmri);
        myRef.child("EWO").child(uuidString).child("itemNo").setValue(useritemNo);
        myRef.child("EWO").child(uuidString).child("equipment").setValue(userequipment);
        myRef.child("EWO").child(uuidString).child("component").setValue(usercomponent);
        myRef.child("EWO").child(uuidString).child("breakdownDate").setValue(userbreakdownDate);
        myRef.child("EWO").child(uuidString).child("breakdownType").setValue(userbreakdownType);
        myRef.child("EWO").child(uuidString).child("maintainerID").setValue(usermaintainerID);
        myRef.child("EWO").child(uuidString).child("solutionDescription").setValue(usersolutionDescription);
        myRef.child("EWO").child(uuidString).child("spareParts").setValue(userspareParts);
        myRef.child("EWO").child(uuidString).child("what").setValue(userwhat);
        myRef.child("EWO").child(uuidString).child("when").setValue(userwhen);
        myRef.child("EWO").child(uuidString).child("where").setValue(userwhere);
        myRef.child("EWO").child(uuidString).child("who").setValue(userwho);
        myRef.child("EWO").child(uuidString).child("howmuch").setValue(userhowmuch);
        myRef.child("EWO").child(uuidString).child("how").setValue(userhow);
        myRef.child("EWO").child(uuidString).child("reasons").setValue(userreasons);
        myRef.child("EWO").child(uuidString).child("actions").setValue(useractions);
        myRef.child("EWO").child(uuidString).child("continuity").setValue(usercontinuity);
        myRef.child("EWO").child(uuidString).child("rootCause").setValue(rootCauses);
        myRef.child("EWO").child(uuidString).child("mCount").setValue(usermCount);
        myRef.child("EWO").child(uuidString).child("analyst").setValue(useranalyst);
        myRef.child("EWO").child(uuidString).child("date").setValue(userdate);
        myRef.child("EWO").child(uuidString).child("oCount").setValue(useroCount);
        myRef.child("EWO").child(uuidString).child("screener").setValue(userscreener);

        Intent intent = new Intent(getApplicationContext(),Ewo.class);
        startActivity(intent);


    }
}
