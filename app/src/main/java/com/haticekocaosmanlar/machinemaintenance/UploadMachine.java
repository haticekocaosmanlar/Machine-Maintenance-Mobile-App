package com.haticekocaosmanlar.machinemaintenance;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class UploadMachine extends AppCompatActivity {

    EditText itemNo;
    EditText machineName;
    ImageView imageView;
    ImageView semaView;

    private StorageReference mStorageRef;
    Uri selected;  //bu selecte a her yerden ulaşabilmek için
    Uri selectedSchema;

    private FirebaseAuth mAuth;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_machine);
        itemNo=(EditText) findViewById(R.id.machineItemN);
        machineName = (EditText) findViewById(R.id.machineName);
        imageView=(ImageView) findViewById(R.id.postImage);
        semaView=(ImageView) findViewById(R.id.semaImage);


        mStorageRef = FirebaseStorage.getInstance().getReference();  //storageRef kullanarak storage'ımıza resim koyacağız

        mAuth = FirebaseAuth.getInstance();  //KULLANICI için

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();  //sınıf adı vermek istiyorsan içine yazabilirsin


    }


    @SuppressLint("NewApi")
    public void chooseImage(View view){

        if(checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){  //izinleri kontrol ediyorum kullanıcının galerisine ulaşma)

            requestPermissions(new String[] {android.Manifest.permission.READ_EXTERNAL_STORAGE},1); //kod içinde burda yağtığımız requeste refer edelim diye geln bir requestkodu

        }else{

            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI); //kullanıcının fotolarını ulaşma
            startActivityForResult(intent, 2);  //elde edeceğim sonuç seçtiğim foto
        }

    }

    @SuppressLint("NewApi")
    public void chooseSchema(View view){ //şemayı çekmek için
        if(checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){  //izinleri kontrol ediyorum kullanıcının galerisine ulaşma)

            requestPermissions(new String[] {android.Manifest.permission.READ_EXTERNAL_STORAGE},3); //kod içinde burda yağtığımız requeste refer edelim diye geln bir requestkodu

        }else{

            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 4);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {  //verilen izin okey mi değil mi kontrolü
        if(requestCode==1){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI); //kullanıcının fotolarını ulaşma
                startActivityForResult(intent, 2);  //elde edeceğim sonuç seçtiğim foto
            }
        }
        if(requestCode==3){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI); //kullanıcının fotolarını ulaşma
                startActivityForResult(intent, 4);  //elde edeceğim sonuç seçtiğim foto
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  //seçtik ve image'imizi alıcaz

        if(requestCode==2 && resultCode==RESULT_OK && data!=null){  //data bizim image
            selected = data.getData(); //foto galerisindeki yere referans veriyor
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),selected);
                imageView.setImageBitmap(bitmap);  //bitmapi imageview e set etme
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        if(requestCode==4&& resultCode==RESULT_OK &&data!=null){
            selectedSchema = data.getData();
            try {
                Bitmap bmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedSchema);
                semaView.setImageBitmap(bmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    UUID uuid;
    String uuidString;
    public void upload(View view){


        UUID uuidImage = UUID.randomUUID(); //unique id verme
        String imageName = "machines/"+uuidImage+".jpg";

        UUID uuidSema = UUID.randomUUID();
        String semaImageName = "schemasDiagrams/"+uuidSema+".jpg";

        FirebaseUser user = mAuth.getCurrentUser();  //login yapmış kullanıcının bilgilerini veriyor
        String userEmail = user.getEmail().toString();

        String userItemNo=itemNo.getText().toString();
        String userMachineName = machineName.getText().toString();  //kullanıcının girdiği makina ismini alma


        uuid = UUID.randomUUID();  //unique id oluşturma
        uuidString = uuid.toString();

        //mRef ağacın kendisi. childler ile dal açıyoruz

        myRef.child("Machines").child(uuidString).child("userEmail").setValue(userEmail);
        myRef.child("Machines").child(uuidString).child("itemNo").setValue(userItemNo);
        myRef.child("Machines").child(uuidString).child("machineName").setValue(userMachineName);




        StorageReference storageReference = mStorageRef.child(imageName); //storage kullanarak upload etme //her seferinde farklı isimle kaydetme
        storageReference.putFile(selected).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {  //resim yükleme başarılı olursan ne yapılacak
                String downloadURL = taskSnapshot.getDownloadUrl().toString();  //veritabanında url leri kaydetme // taskSnapshot.getDownloadUrl() ile kaydettiğim resimlerin url sini bulabiliyorum. Tamamen URL kullanarak resim gösterme işlemi yapılacak
                myRef.child("Machines").child(uuidString).child("downloadURL").setValue(downloadURL);



                // myRef.child("Machines").child(uuidString).child("sparePart").setValue(spareList);


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {  //resmi koyma başarısız olursa ne yapılacak
                Toast.makeText(getApplicationContext(),e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
            }
        });

        StorageReference newStorageReference=mStorageRef.child(semaImageName);
        newStorageReference.putFile(selectedSchema).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String schemaURL = taskSnapshot.getDownloadUrl().toString();
                myRef.child("Machines").child(uuidString).child("schemaURL").setValue(schemaURL);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
            }
        });

        Toast.makeText(getApplicationContext(),"Machine added",Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getApplicationContext(),machine.class);
        startActivity(intent);

    }

    /*public void uploadSpare(View view){

        userSpareName=spareName.getText().toString();
        spareList.add(userSpareName);



    }*/
}
