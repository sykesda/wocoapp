package edu.wofford.sykesda.wocoapptest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WNumber_Manual extends AppCompatActivity {

    Button wNumManualSave;
    EditText wNumEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wnumber__manual);

        wNumManualSave = (Button) findViewById(R.id.wNumManualSave);
        wNumEntry = (EditText) findViewById(R.id.wNumEntry);

        //Wire button to save entered wNum and pass it back in an intent
        wNumManualSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                savewNumManualEntry(v);
            }
        });
    }

    public void savewNumManualEntry(View v){
        if(wNumEntry.getText().toString().length() != 8){
            Toast toast = Toast.makeText(getApplicationContext(), "Not a valid W#", Toast.LENGTH_LONG);
            toast.show();
        }
        else {
            Intent intent = new Intent();
            //Send value back to previous activity
            intent.putExtra("rawWnum", "9" + wNumEntry.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
