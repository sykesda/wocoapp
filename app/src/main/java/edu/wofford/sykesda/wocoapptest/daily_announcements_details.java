package edu.wofford.sykesda.wocoapptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.HashMap;

public class daily_announcements_details extends AppCompatActivity {

    private HashMap announcementMap;
    private TextView announcementTitle;
    private TextView announcementContact;
    private TextView announcementDetails;
    private TextView announcementEmail;
    private TextView announcementPhone;
    private TextView announcementCost;
    private TextView announcementDatetime;
    private TextView announcementLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_announcements_details);

        //Start: Code copied and edited from tutorial
        // 1
        Bundle announcementBundle = this.getIntent().getExtras();
        if(announcementBundle != null) {
            announcementMap = (HashMap) announcementBundle.getSerializable("announcementMap");
        }

        // pull out the different fields: title contact details email phone cost datetime location
        String title = (String) announcementMap.get("title");
        String contact = (String) announcementMap.get("contact");
        String details = (String) announcementMap.get("details");
        String email = (String) announcementMap.get("email");
        String phone = (String) announcementMap.get("phone");
        String cost = (String) announcementMap.get("cost");
        String datetime = (String) announcementMap.get("datetime");
        String location = (String) announcementMap.get("location");


        // 2
        announcementTitle = findViewById(R.id.title);
        announcementTitle.setText(title);

        announcementContact = findViewById(R.id.contact);
        announcementContact.setText(contact);

        announcementDetails = findViewById(R.id.details);
        announcementDetails.setText(details);

        announcementEmail = findViewById(R.id.email);
        announcementEmail.setText(email);

        announcementPhone = findViewById(R.id.phone);
        announcementPhone.setText(phone);

        announcementCost = findViewById(R.id.cost);
        announcementCost.setText(cost);

        announcementDatetime = findViewById(R.id.datetime);
        announcementDatetime.setText(datetime);

        announcementLocation = findViewById(R.id.location);
        announcementLocation.setText(location);



        // 3
        //mWebView = (WebView) findViewById(R.id.detail_web_view);

        // 4
        //mWebView.loadUrl(url);
        //End: Code copied and edited from tutorial
    }
}
