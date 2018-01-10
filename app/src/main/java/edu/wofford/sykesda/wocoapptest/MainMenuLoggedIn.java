package edu.wofford.sykesda.wocoapptest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainMenuLoggedIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_logged_in);

        final ImageButton openMap = (ImageButton) findViewById(R.id.mapActivityBttn);

        openMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });

    }

    protected void openMap() {

        Intent openMapsPlz = new Intent(this, MapsActivity.class);
        startActivity(openMapsPlz);
    }
}
