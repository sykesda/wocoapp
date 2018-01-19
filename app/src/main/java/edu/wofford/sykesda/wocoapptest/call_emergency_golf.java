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
        exampleList.add(new ExampleItem(R.drawable.ic_golf_cart, "Transportation", "Click here to make a call to request a golf cart ride."));
        exampleList.add(new ExampleItem(R.drawable.ic_campus_safety, "Report a Crime", "Click here call Campus Safety to report a crime on campus."));
        exampleList.add(new ExampleItem(R.drawable.ic_medical_emergency, "9-1-1", "Click here to call 9-1-1 to report a medical emergency."));

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
                if (position == 2){
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                    dialIntent.setData(Uri.parse("tel://911"));
                    if (dialIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(dialIntent);
                    } else {
                        Log.e(TAG, "Can't resolve app for ACTION_DIAL Intent.");
                    }
                }
                if (position == 1) {
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                    dialIntent.setData(Uri.parse("tel: 8645974911"));
                    if (dialIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(dialIntent);
                    } else {
                        Log.e(TAG, "Can't resolve app for ACTION_DIAL Intent.");
                    }
                }
                if (position ==0){
                    makePhoneCall();
                }
            }
        });
    }
    private void makePhoneCall(){
        String number = "8645974350";
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


//        defineButtons(exampleList);
//
//
//    public void defineButtons(exampleList){
//        exampleList
//        findViewById(R.id.emergencyButton).setOnClickListener(buttonClickListener);
//        findViewById(R.id.rideButton).setOnClickListener(buttonClickListener);
//   }
//
//        private View.OnClickListener buttonClickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                switch (view.getId()) {
//                    case R.id.rideButton:
//                        makePhoneCall();
//                        break;
//                    case R.id.emergencyButton:
//                        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
//                        dialIntent.setData(Uri.parse("tel: 8645974911"));
//                        if (dialIntent.resolveActivity(getPackageManager()) != null) {
//                            startActivity(dialIntent);
//                        } else {
//                            Log.e(TAG, "Can't resolve app for ACTION_DIAL Intent.");
//                        }
//                }
//            }
//        };

//    private void makePhoneCall(){
//        //String number =  Integer.toString(R.string.golf_cart_ride_phonenumber);
//        String number = "8645974350";
//        if (ContextCompat.checkSelfPermission(call_emergency_golf.this,
//                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(call_emergency_golf.this,
//                    new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
//
//        } else {
//            String dial = "tel:" + number;
//            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if (requestCode == REQUEST_CALL) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                makePhoneCall();
//            }
//        }
//    }


}