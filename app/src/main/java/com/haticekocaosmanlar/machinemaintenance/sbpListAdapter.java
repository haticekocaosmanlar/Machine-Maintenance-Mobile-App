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
 * Created by Hatice on 14.05.2018.
 */

public class sbpListAdapter extends ArrayAdapter<String> {
    public final ArrayList<String> subjectlist;
    public final ArrayList<String> sbpitemNolist;
    private final Activity context;

    public sbpListAdapter(ArrayList<String> subjectlist, ArrayList<String> sbpitemNolist, Activity context) {
        super(context,R.layout.sbp_list_view, subjectlist);
        this.subjectlist = subjectlist;
        this.sbpitemNolist = sbpitemNolist;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View sbpListView = layoutInflater.inflate(R.layout.sbp_list_view,null,true);  //bundan sonra custom view içindeki her şeye ulaşabilirim.

        //GET VIEW
        TextView subjectInfo = (TextView) sbpListView.findViewById(R.id.subjectInfo);
        TextView itemNo =(TextView)  sbpListView.findViewById(R.id.itemNo);



        //SET DATA ?

        subjectInfo.setText(subjectlist.get(position));  //machineNames.get(position)
        itemNo.setText(sbpitemNolist.get(position));


        return sbpListView;
    }

}
