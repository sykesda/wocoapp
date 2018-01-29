package edu.wofford.sykesda.wocoapptest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


public class WNumber extends AppCompatActivity {

    Button scanIDButton;
    Button deleteWNumButton;
    Button manualEntryButton;
    TextView wNumText;
    String rawWnum;
    ConstraintLayout constraintLayout;
    PopupWindow popupWindow;
    LayoutInflater layoutInflater;
    SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wnumber);

        scanIDButton = (Button) findViewById(R.id.scanIDButton);
        deleteWNumButton = (Button) findViewById(R.id.clearWNumButton);
        manualEntryButton = (Button) findViewById(R.id.manualEntryButton);

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

        //Wire button to create popup for deleting saved W#
        deleteWNumButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startDeleteConfirmPopup(v);
            }
        });
    }

    public void startScanMenu(View v){
        Intent intent = new Intent(this, WNumber_ScanMenu.class);
        startActivityForResult(intent, 0);
    }

    public void startDeleteConfirmPopup(View v){
        constraintLayout = (ConstraintLayout) findViewById(R.id.wNumConstraint);

        //Get screen metrics to center popup
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        //Get layoutInflater for creating popup
        layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.popup_wnumber_delete,null);

        popupWindow = new PopupWindow(container, (int) (width*0.8), (int) (height*0.8), true);
        popupWindow.showAtLocation(constraintLayout, Gravity.NO_GRAVITY, ((width/2) - (popupWindow.getWidth()/2)), ((height/2) - (popupWindow.getHeight()/2)));


        Button deleteYes = (Button) popupWindow.getContentView().findViewById(R.id.delete_yes);
        Button deleteNo = (Button) popupWindow.getContentView().findViewById(R.id.delete_no);

        //Wire button to delete the saved WNumber
        deleteYes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)  {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.remove("Wnum");
                editor.commit();
                wNumText.setText("No Saved W#");
                popupWindow.dismiss();
                Toast toast = Toast.makeText(getApplicationContext(), "W# deleted", Toast.LENGTH_LONG);
                toast.show();
            }
        });
        //Wire button to close popup
        deleteNo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)  {
                popupWindow.dismiss();
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 0){
            if (resultCode == RESULT_OK){{
                if (data != null) {
                    rawWnum = data.getStringExtra("rawWnum");
                    wNumText.setText("W" + rawWnum.substring(1));
                    //save the Wnumber
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("Wnum", "W" + rawWnum.substring(1));
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
