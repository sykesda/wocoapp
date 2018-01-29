package edu.wofford.sykesda.wocoapptest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class WNumber extends AppCompatActivity {

    Button scanIDButton;
    TextView wNumText;
    String rawWnum;


    SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wnumber);

        scanIDButton = (Button) findViewById(R.id.scanIDButton);
        wNumText = (TextView) findViewById(R.id.wNumText);
        sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        //Load the saved W# if one is saved
        wNumText.setText(sharedPref.getString("Wnum", "No Saved W#"));

        //Wire button to start Scan Menu activity
        scanIDButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startScanMenu(v);
            }
        });
    }

    public void startScanMenu(View v){
        Intent intent = new Intent(this, WNumber_ScanMenu.class);
        startActivityForResult(intent, 0);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 0){
            if (resultCode == RESULT_OK){{
                if (data != null) {
                    rawWnum = data.getStringExtra("rawWnum");
                    wNumText.setText("W" + rawWnum.substring(1));
                    //save the Wnumber
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("Wnum", rawWnum);
                    editor.commit();
                }
            }
            }else{
                Toast toast = Toast.makeText(getApplicationContext(), "No W# Received", Toast.LENGTH_LONG);
                toast.show();
            }
        }else{

            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
