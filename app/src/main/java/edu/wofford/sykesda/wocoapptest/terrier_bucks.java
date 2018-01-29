package edu.wofford.sykesda.wocoapptest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class terrier_bucks extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terrier_bucks);

        Button Wnum = (Button) findViewById(R.id.Wnumber_button);


        //Wiring up button to start WNumber activity
        Wnum.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                viewWnum(v);
            }
        });
    }

    public void viewWnum (View v){
        Intent intent = new Intent(this, WNumber.class);
        startActivity(intent);
    }
}
