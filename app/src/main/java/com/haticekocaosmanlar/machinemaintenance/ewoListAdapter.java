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
 * Created by Hatice on 6.05.2018.
 */

public class ewoListAdapter extends ArrayAdapter<String> {
    public final ArrayList<String> isEmri;
    public final ArrayList<String> itemNo;
    private final Activity context;

    public ewoListAdapter(ArrayList<String> isEmri, ArrayList<String> itemNo, Activity context) {
        super(context,R.layout.ewo_list_view, isEmri);
        this.isEmri = isEmri;
        this.itemNo = itemNo;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View ewoListView = layoutInflater.inflate(R.layout.ewo_list_view,null,true);  //bundan sonra custom view içindeki her şeye ulaşabilirim.

        //GET VIEW
        TextView isEmriNo = (TextView) ewoListView.findViewById(R.id.isEmrNo);
        TextView machineItemNo =(TextView)  ewoListView.findViewById(R.id.machineItemN);



        //SET DATA ?

        isEmriNo.setText(isEmri.get(position));  //machineNames.get(position)
        machineItemNo.setText(itemNo.get(position));


        return ewoListView;
    }
}
