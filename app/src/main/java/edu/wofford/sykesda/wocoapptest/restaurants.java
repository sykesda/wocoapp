package edu.wofford.sykesda.wocoapptest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class restaurants extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
    }

    public void papa_menu (View view) {
        Intent papa_menu_open = new Intent((Intent.ACTION_VIEW), Uri.parse("https://www.papajohns.com/locations/usa/sc/spartanburg/29307-2305/1948-e-main-st/1822"));
        startActivity(papa_menu_open);
    }

    public void cribbs_menu (View view) {
        Intent cribbs_menu_open = new Intent((Intent.ACTION_VIEW), Uri.parse("http://www.cribbscatering.com/our-menus"));
        startActivity(cribbs_menu_open);
    }

    public void monarchs_menu (View view) {
        Intent monarchs_menu_open = new Intent((Intent.ACTION_VIEW), Uri.parse("http://nebula.wsimg.com/cc060d5468c7937bd84f445e5c3abfcc?AccessKeyId=A34739A1B55789958D1F&disposition=0&alloworigin=1"));
        startActivity(monarchs_menu_open);
    }

    public void lime_leaf_menu (View view){
        Intent lime_leaf_open = new Intent ((Intent.ACTION_VIEW), Uri.parse("https://www.limeleafsc.com/order/main"));
        startActivity(lime_leaf_open);
    }

    public void papa_direction (View view){
        Intent papa_direction = new Intent((Intent.ACTION_VIEW), Uri.parse("https://www.google.com/maps/search/papa+john's+google+map/@34.9637849,-81.998399,12z/data=!3m1!4b1"));
        startActivity(papa_direction);
    }

    public void cribbs_direction (View view){
        Intent cribbs_direction = new Intent((Intent.ACTION_VIEW), Uri.parse("https://www.google.com/maps/place/Cribb's+Kitchen+On+Main/@34.9485456,-81.9356172,15z/data=!4m5!3m4!1s0x0:0x20bd4184d0371a72!8m2!3d34.9485456!4d-81.9356172"));
        startActivity(cribbs_direction);
    }

    public void monarchs_direction (View view){
        Intent monarchs_direction = new Intent((Intent.ACTION_VIEW), Uri.parse("https://www.google.com/maps/place/Monarch+Cafe+and+Fresh+Food+Store/@34.9562191,-81.9423598,15z/data=!4m2!3m1!1s0x0:0x69e86e9199789e44?sa=X&ved=0ahUKEwj2qvCSuOTYAhUKXKwKHcr9AV4Q_BIIfTAK"));
        startActivity(monarchs_direction);
    }

    public void lime_leaf_direction (View view){
        Intent lime_leaf_direction = new Intent((Intent.ACTION_VIEW), Uri.parse("https://www.google.com/maps/place/Lime+Leaf/@34.9494566,-81.9314377,15z/data=!4m2!3m1!1s0x0:0x67580c169d91082b?sa=X&ved=0ahUKEwjkmua5uOTYAhUMNKwKHSfKCicQ_BIIgwEwCg"));
        startActivity(lime_leaf_direction);
    }
}

