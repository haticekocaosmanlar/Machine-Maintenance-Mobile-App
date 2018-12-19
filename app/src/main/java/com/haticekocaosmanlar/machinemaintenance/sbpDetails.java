package com.haticekocaosmanlar.machinemaintenance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class sbpDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sbp_details);

        TextView subject = (TextView) findViewById(R.id.subjecttxt);
        TextView unit = (TextView) findViewById(R.id.unittxt);
        TextView sbpequ = (TextView) findViewById(R.id.sbpequipmenttxt);
        TextView sbpitemno = (TextView) findViewById(R.id.sbpitemNotxt);
        TextView sbpno = (TextView) findViewById(R.id.sbpitemNotxt);
        TextView preparer = (TextView) findViewById(R.id.preparertxt);
        TextView sbpdate = (TextView) findViewById(R.id.sbpdatetxt);
        TextView mtype = (TextView) findViewById(R.id.maintenaceTypetxt);
        TextView mfreq = (TextView) findViewById(R.id.maintenanceFreqtxt);
        TextView mtime = (TextView) findViewById(R.id.maintenanceTimetxt);
        TextView state = (TextView) findViewById(R.id.statetxt);
        TextView spare = (TextView) findViewById(R.id.sparetxt);
        TextView handtools = (TextView) findViewById(R.id.handtooltxt);
        TextView aim = (TextView) findViewById(R.id.aimtxt);
        TextView operations = (TextView) findViewById(R.id.operationstxt);

        Intent intent = getIntent();
        String getsubject = intent.getExtras().getString("subject");
        String getunit = intent.getExtras().getString("unit");
        String getsbpequipment = intent.getExtras().getString("sbpequipment");
        String getsbpitemNo = intent.getExtras().getString("sbpitemNo");
        String getsbpNo = intent.getExtras().getString("sbpNo");
        String getpreparer = intent.getExtras().getString("preparer");
        String getsbpdate = intent.getExtras().getString("sbpdate");
        String getmaintenanceTime = intent.getExtras().getString("maintenanceTime");
        String getmaintenanceType = intent.getExtras().getString("maintenanceType");
        String getmaintenanceFreq = intent.getExtras().getString("maintenanceFreq");
        String getstate = intent.getExtras().getString("state");
        String getspare = intent.getExtras().getString("spare");
        String gethandtool = intent.getExtras().getString("handtool");
        String getaim = intent.getExtras().getString("aim");
        String getoperations = intent.getExtras().getString("operations");

        subject.setText(getsubject);
        unit.setText(getunit);
        sbpequ.setText(getsbpequipment);
        sbpitemno.setText(getsbpitemNo);
        sbpno.setText(getsbpNo);
        preparer.setText(getpreparer);
        sbpdate.setText(getsbpdate);
        mtype.setText(getmaintenanceType);
        mfreq.setText(getmaintenanceFreq);
        mtime.setText(getmaintenanceTime);
        state.setText(getstate);
        spare.setText(getspare);
        handtools.setText(gethandtool);
        aim.setText(getaim);
        operations.setText(getoperations);

    }
}
