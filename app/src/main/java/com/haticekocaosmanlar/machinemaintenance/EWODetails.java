package com.haticekocaosmanlar.machinemaintenance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class EWODetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ewodetails);

        TextView isEmri = (TextView) findViewById(R.id.isEmritxt);
        TextView itemNo= (TextView) findViewById(R.id.itemNotxt);
        TextView equipment = (TextView) findViewById(R.id.equipmenttxt);
        TextView component= (TextView) findViewById(R.id.componenttxt);
        TextView breakdownDate= (TextView) findViewById(R.id.breakdownDatetxt);
        TextView breakdownType= (TextView) findViewById(R.id.breakdownTypetxt);
        TextView maintainerID= (TextView) findViewById(R.id.manitainerIDtxt);
        TextView solutionDescription= (TextView) findViewById(R.id.solutionDescriptiontxt);
        TextView spareParts= (TextView) findViewById(R.id.sparePartstxt);
        TextView what= (TextView) findViewById(R.id.whattxt);
        TextView when= (TextView) findViewById(R.id.whentxt);
        TextView where= (TextView) findViewById(R.id.wheretxt);
        TextView who= (TextView) findViewById(R.id.whotxt);
        TextView howmuch= (TextView) findViewById(R.id.howmuchtxt);
        TextView how= (TextView) findViewById(R.id.howtxt);
        TextView reasons= (TextView) findViewById(R.id.reasonstxt);
        TextView actions= (TextView) findViewById(R.id.actionstxt);
        TextView continuity= (TextView) findViewById(R.id.continuitytxt);
        TextView rootCause=(TextView) findViewById(R.id.rootCausetxt);
        TextView mCount= (TextView) findViewById(R.id.mCounttxt);
        TextView analyst= (TextView) findViewById(R.id.analysttxt);
        TextView date= (TextView) findViewById(R.id.datetxt);
        TextView oCount= (TextView) findViewById(R.id.oCounttxt);
        TextView screener= (TextView) findViewById(R.id.screenertxt);

        Intent intent = getIntent();
        String getisEmri = intent.getExtras().getString("isEmri");
        String getitemno = intent.getExtras().getString("itemNo");
        String getequipment= intent.getExtras().getString("equipment");
        String getcomponent= intent.getExtras().getString("component");
        String getbreakdownDate= intent.getExtras().getString("breakdownDate");
        String getbreakdownType= intent.getExtras().getString("breakdownType");
        String getmaintainerID= intent.getExtras().getString("maintainerID");
        String getsolutionDescription= intent.getExtras().getString("solutionDescription");
        String getspareParts= intent.getExtras().getString("spareParts");
        String getwhat= intent.getExtras().getString("what");
        String getwhen= intent.getExtras().getString("when");
        String getwhere= intent.getExtras().getString("where");
        String getwho= intent.getExtras().getString("who");
        String gethowmuch= intent.getExtras().getString("howmuch");
        String gethow= intent.getExtras().getString("how");
        String getreasons= intent.getExtras().getString("reasons");
        String getactions= intent.getExtras().getString("actions");
        String getcontinuity= intent.getExtras().getString("continuity");
        String getrootCause= intent.getExtras().getString("rootCause");
        String getmCount= intent.getExtras().getString("mCount");
        String getanalyst= intent.getExtras().getString("analyst");
        String getdate= intent.getExtras().getString("date");
        String getoCount= intent.getExtras().getString("oCount");
        String getscreener= intent.getExtras().getString("screener");

        isEmri.setText(getisEmri);
        itemNo.setText(getitemno);
        equipment.setText(getequipment);
        component.setText(getcomponent);
        breakdownDate.setText(getbreakdownDate);
        breakdownType.setText(getbreakdownType);
        maintainerID.setText(getmaintainerID);
        solutionDescription.setText(getsolutionDescription);
        spareParts.setText(getspareParts);
        what.setText(getwhat);
        when.setText(getwhen);
        where.setText(getwhere);
        who.setText(getwho);
        howmuch.setText(gethowmuch);
        how.setText(gethow);
        reasons.setText(getreasons);
        actions.setText(getactions);
        continuity.setText(getcontinuity);
        rootCause.setText(getrootCause);
        mCount.setText(getmCount);
        analyst.setText(getanalyst);
        date.setText(getdate);
        oCount.setText(getoCount);
        screener.setText(getscreener);

    }
}
