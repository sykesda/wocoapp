package edu.wofford.sykesda.wocoapptest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class call_emergency_golf extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;
    private static final String TAG = call_emergency_golf.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_emergency_golf);
        final ArrayList<ExampleItem> exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem(R.drawable.ic_campus_safety, "Campus Safety", "Click here to contact campus safety for a ride, report a crime, or if you're having car trouble."));
        exampleList.add(new ExampleItem(R.drawable.ic_anonymous_tip, "Report a Crime", "Click here to submit an anonymous tip."));
        exampleList.add(new ExampleItem(R.drawable.ic_resident_assistants, "Resident Assistant", "Click here to call the RA on duty for your building."));
        exampleList.add(new ExampleItem(R.drawable.ic_maintenance, "Maintenance", "Call here to get to the maintenance request form."));

        final ExampleItem golf_cart = exampleList.get(0);
        ExampleItem campus_safety = exampleList.get(1);
        ExampleItem medical_emergency = exampleList.get(2);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(exampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ExampleItem thing = exampleList.get(position);
                if(position == 3){
                    String url = "http://fixit.wofford.edu/";
                    Intent maintenance = new Intent(Intent.ACTION_VIEW);
                    maintenance.setData(Uri.parse(url));
                    startActivity(maintenance);
                }
                if (position == 2){
                    Intent resident_assistant = new Intent(call_emergency_golf.this, ResidentAssistant.class);
                    startActivity(resident_assistant);

                }
                if (position == 1) {
                    String url = "https://www.wofford.edu/campusSafety/form.aspx?ekfrm=4204";
                    Intent anonymous_tip = new Intent(Intent.ACTION_VIEW);
                    anonymous_tip.setData(Uri.parse(url));
                    startActivity(anonymous_tip);
                }
                if (position ==0){
                    makePhoneCall();
                }
            }
        });
    }
    private void makePhoneCall(){
        String number = "8645974911";
        if (ContextCompat.checkSelfPermission(call_emergency_golf.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(call_emergency_golf.this,
                    new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);

        } else {
            String dial = "tel:" + number;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            }
        }
    }



}