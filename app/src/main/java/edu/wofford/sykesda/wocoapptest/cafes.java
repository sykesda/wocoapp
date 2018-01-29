package edu.wofford.sykesda.wocoapptest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class cafes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafes);
    }

    public void acorn_cafe_menu (View view) {
        Intent acorn_menu_open = new Intent((Intent.ACTION_VIEW), Uri.parse("https://woffordoldgoldandblack.wordpress.com/tag/acorn-cafe/"));
        startActivity(acorn_menu_open);
    }

    public void terrier_ground_menu (View view) {
        Intent terrier_menu_open = new Intent((Intent.ACTION_VIEW), Uri.parse("http://www.wofford.edu/arttour/content.aspx?id=42942"));
        startActivity(terrier_menu_open);
    }

    public void starbucks_menu (View view) {
        Intent starbucks_open = new Intent((Intent.ACTION_VIEW), Uri.parse("https://www.starbucks.com/menu"));
        startActivity(starbucks_open);
    }

    public void little_river_menu (View view){
        Intent little_river_open = new Intent ((Intent.ACTION_VIEW), Uri.parse("https://www.littleriverroasting.com/"));
        startActivity(little_river_open);
    }

}

