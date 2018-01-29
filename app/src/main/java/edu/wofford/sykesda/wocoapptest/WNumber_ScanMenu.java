package edu.wofford.sykesda.wocoapptest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.vision.barcode.Barcode;



public class WNumber_ScanMenu extends AppCompatActivity {

    TextView barcodeResult;

    String rawWnum;

    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wnumber__scan_menu);
        barcodeResult = (TextView) findViewById(R.id.barcode_result);

        saveButton = (Button) findViewById(R.id.save_button);



        //Wiring up button to start Scanner activity
        Button mybutton = (Button) findViewById(R.id.scan_barcode);
        mybutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                scanBarcode(v);
            }
        });


        //Wiring up button to pass result back to WNumber activity
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onSaveButtonClick(v);
            }
        });

    }


    public void scanBarcode (View v){
        Intent intent = new Intent(this, WNumber_Scanner.class);
        startActivityForResult(intent, 0);
    }

    public void onSaveButtonClick (View v){
        Intent intent = new Intent();
        //Send value back to previous activity
        intent.putExtra("rawWnum", rawWnum);
        setResult(RESULT_OK, intent);
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 0){
            if (resultCode == RESULT_OK){{
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra("barcode");
                    rawWnum = (String) barcode.displayValue;

                    if(rawWnum.length() != 9){
                        barcodeResult.setText("Bad data read. Please rescan.");
                    }else {
                        barcodeResult.setText("Barcode value : W" + rawWnum.substring(1));
                        saveButton.setVisibility(View.VISIBLE);
                    }
                }
                else {
                    barcodeResult.setText("No barcode found");
                }
            }
            }else{

                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }
}