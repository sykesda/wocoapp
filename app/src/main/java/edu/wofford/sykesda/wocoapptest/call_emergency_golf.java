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
import android.util.Log;
import android.view.View;



public class call_emergency_golf extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;
    private static final String TAG = call_emergency_golf.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_emergency_golf);
        defineButtons();
    }

    public void defineButtons(){
        findViewById(R.id.emergencyButton).setOnClickListener(buttonClickListener);
        findViewById(R.id.rideButton).setOnClickListener(buttonClickListener);
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.rideButton:
                    makePhoneCall();
                    break;
                case R.id.emergencyButton:
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                    dialIntent.setData(Uri.parse("tel: 8645974911"));
                    if (dialIntent.resolveActivity(getPackageManager()) != null){
                        startActivity(dialIntent);
                    } else {
                        Log.e(TAG, "Can't resolve app for ACTION_DIAL Intent.");
                    }
            }
        }
    };

    private void makePhoneCall() {
        //String number =  Integer.toString(R.string.golf_cart_ride_phonenumber);
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
}