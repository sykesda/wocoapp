package edu.wofford.sykesda.wocoapptest;

import android.content.Intent;
import android.net.Uri;
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

        // create buttons and add listeners to them
        final ImageButton openMap = (ImageButton) findViewById(R.id.mapActivityBttn);
        openMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });

        final ImageButton openEmergencyGolf = (ImageButton) findViewById(R.id.emerGolfActBtn);
        openEmergencyGolf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEmergencyGolf();
            }
        });

        final ImageButton openTVGuide = (ImageButton) findViewById(R.id.tvGuideBtn);
        openTVGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTVGuide();
            }
        });

        final ImageButton openDirectory = (ImageButton) findViewById(R.id.directoryBtn);
        openDirectory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDirectory();
            }
        });

        final ImageButton openSports = findViewById(R.id.sportsBtn);
        openSports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSports();
            }
        });

        final ImageButton openFoodMenu = (ImageButton) findViewById(R.id.foodMenuBtn);
        openFoodMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFoodMenu();
            }
        });

        final ImageButton openTerrierBucks = (ImageButton) findViewById(R.id.terrierBucksBtn);
        openTerrierBucks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTerrierBucks();
            }
        });



//        final ImageButton openSettings = (ImageButton) findViewById(R.id.settingsBtn);
//        openSettings.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openSettings();
//            }
//        });

        final ImageButton openLibrary = findViewById(R.id.laundryBtn);
        openLibrary.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openLaundry();
            }
        });

        final ImageButton openDailyAnnouncements = (ImageButton) findViewById(R.id.dailyAnnouncementsBtn);
        openDailyAnnouncements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDailyAnnouncements();
            }
        });

        final ImageButton openDonate = findViewById(R.id.donateBtn);
        openDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDonate();
            }
        });

    }

    protected void openMap() {
        Intent openMapsPlz = new Intent(this, MapsActivity.class);
        startActivity(openMapsPlz);
    }

    protected void openEmergencyGolf() {
        Intent callEmerGolf = new Intent(this, call_emergency_golf.class);
        startActivity(callEmerGolf);
    }

    protected void openTVGuide() {
        Intent tvGuide = new Intent(this, tv_guide.class);
        startActivity(tvGuide);
    }

    protected void openDirectory() {
        Intent directory = new Intent(this, directory.class);
        startActivity(directory);
    }


    protected void openFoodMenu() {
        Intent foodMenu = new Intent(this, food_menu.class);
        startActivity(foodMenu);
    }

    protected void openTerrierBucks() {
        Intent terrierBucks = new Intent(this, terrier_bucks.class);
        startActivity(terrierBucks);
    }

//    protected void openSettings() {
//        Intent settingsIntent = new Intent(this, settings.class);
//        startActivity(settingsIntent);
//    }

    protected void openDailyAnnouncements() {

        Intent dailyAnnounceIntent = new Intent(this, daily_announcements.class);
        startActivity(dailyAnnounceIntent);
    }

    protected void openSports(){
        String url = "http://athletics.wofford.edu/";
        Intent sportsIntent = new Intent(Intent.ACTION_VIEW);
        sportsIntent.setData(Uri.parse(url));
        startActivity(sportsIntent);
    }

    protected void openLaundry(){

        Intent laundryPage = new Intent(this, laundryRooms.class);
        startActivity(laundryPage);

    }

    protected void openDonate(){

    }


}
