package edu.wofford.sykesda.wocoapptest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class campus_food_menu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_food_menu);
    }

    public void burwell_menu (View view) {
        Intent burwell_menu_open = new Intent((Intent.ACTION_VIEW), Uri.parse("https://www.aviserves.com/woffordl/active_menus/burwell_menu_active.htm"));
        startActivity(burwell_menu_open);
    }

    public void phaseV_menu (View view) {
        Intent phaseV_menu_open = new Intent((Intent.ACTION_VIEW), Uri.parse("http://www.wofford.edu/supportwofford/PhaseV/"));
        startActivity(phaseV_menu_open);
    }

    public void zachs_menu (View view) {
        Intent zachs_menu_open = new Intent((Intent.ACTION_VIEW), Uri.parse("https://www.aviserves.com/wofford/dining-hours.html"));
        startActivity(zachs_menu_open);
    }

    public void players_corner_menu (View view){
        Intent players_menu_open = new Intent ((Intent.ACTION_VIEW), Uri.parse("https://woffordcollege.smugmug.com/keyword/players'%20corner/"));
        startActivity(players_menu_open);
    }

}

