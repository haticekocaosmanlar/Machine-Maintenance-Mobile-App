package com.haticekocaosmanlar.machinemaintenance;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Hatice on 26.04.2018.
 */
//listviewi adaptor ve custom view ı birbirine bağlayacak
public class PostClass extends ArrayAdapter<String> {
    public final ArrayList<String> machineNames;
    private final Activity context;

    public PostClass(ArrayList<String> machineNames,Activity context) {
        super(context,R.layout.custom_view, machineNames);  //kullanacağımız activiteye contexi tanımlıyoruz. hagni custom viewla bağlıcaz onu söyle
        this.machineNames = machineNames;
        this.context = context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View customView = layoutInflater.inflate(R.layout.custom_view,null,true);  //bundan sonra custom view içindeki her şeye ulaşabilirim.

        //GET VIEW
        TextView machineNamesText = (TextView) customView.findViewById(R.id.machineName);



        //SET DATA ?

        machineNamesText.setText(machineNames.get(position));  //machineNames.get(position)


        return customView;

    }

}
