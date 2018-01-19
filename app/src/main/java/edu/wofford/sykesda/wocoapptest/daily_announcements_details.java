package edu.wofford.sykesda.wocoapptest;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
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

    String title;
    String contact;
    String details;
    String email;
    String phone;
    String cost;
    String datetime;
    String location;




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
        title = (String) announcementMap.get("title");
        contact = (String) announcementMap.get("contact");
        details = (String) announcementMap.get("details");
        email = (String) announcementMap.get("email");
        phone = (String) announcementMap.get("phone");
        cost = (String) announcementMap.get("cost");
        datetime = (String) announcementMap.get("datetime");
        location = (String) announcementMap.get("location");


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



        // add to calendar button
        final Button openAddToCalendar = (Button) findViewById(R.id.addToCalendar);
        openAddToCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCalendarEvent(v);
            }
        });


        // 3
        //mWebView = (WebView) findViewById(R.id.detail_web_view);

        // 4
        //mWebView.loadUrl(url);
        //End: Code copied and edited from tutorial
    }

    public void addCalendarEvent(View view){

        // 2018-01-12 10:00:00.000000

        Calendar beginTime = Calendar.getInstance();
        // extract year-month-day-time
        beginTime.set(Integer.parseInt(datetime.substring(0,4)), Integer.parseInt(datetime.substring(5,7))-1, Integer.parseInt(datetime.substring(8,10)), Integer.parseInt(datetime.substring(11,13)), Integer.parseInt(datetime.substring(14,16)));
//        Calendar endTime = Calendar.getInstance();
//        endTime.set(2012, 0, 19, 8, 30);
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
//                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                .putExtra(CalendarContract.Events.TITLE, title)
                .putExtra(CalendarContract.Events.DESCRIPTION, details)
                .putExtra(CalendarContract.Events.EVENT_LOCATION, location)
//                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
                .putExtra(Intent.EXTRA_EMAIL, email);
        startActivity(intent);
    }
}
