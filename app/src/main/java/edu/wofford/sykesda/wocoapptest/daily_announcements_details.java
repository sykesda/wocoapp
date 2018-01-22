package edu.wofford.sykesda.wocoapptest;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
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
        title = "<b>Event Title: </b>" + (String) announcementMap.get("title");
        contact = "<b>Contact: </b>" + (String) announcementMap.get("contact");
        details = "<b>Details: </b>" + (String) announcementMap.get("details");
        email = "<b>Email: </b>" + (String) announcementMap.get("email");
        phone = "<b>Phone: </b>" + (String) announcementMap.get("phone");
        datetime = "<b>Date & Time: </b>" + (String) announcementMap.get("datetime");

        if (announcementMap.get("cost") == null){
            cost = "<b>Cost: </b>N/A";
        }else{
            cost = "<b>Cost: </b>" + (String) announcementMap.get("cost");}

        if (announcementMap.get("location") == null){
            location = "<b>Location: </b>N/A";
        }else{
            location = "<b>Location: </b>" + (String) announcementMap.get("location");}


        // Set the values of TextViews in the UI and parse for HTML
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            announcementTitle = findViewById(R.id.title);
            announcementTitle.setText(Html.fromHtml(title, Html.FROM_HTML_MODE_LEGACY));}
        else{
            announcementTitle = findViewById(R.id.title);
            announcementTitle.setText(Html.fromHtml(title));
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            announcementContact = findViewById(R.id.contact);
            announcementContact.setText(Html.fromHtml(contact, Html.FROM_HTML_MODE_LEGACY));}
        else{
            announcementContact = findViewById(R.id.contact);
            announcementContact.setText(Html.fromHtml(contact));
        }

//        announcementContact = findViewById(R.id.contact);
//        announcementContact.setText(contact);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            announcementDetails = findViewById(R.id.details);
            announcementDetails.setText(Html.fromHtml(details, Html.FROM_HTML_MODE_LEGACY));}
        else{
            announcementDetails = findViewById(R.id.details);
            announcementDetails.setText(Html.fromHtml(details));
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            announcementEmail = findViewById(R.id.email);
            announcementEmail.setText(Html.fromHtml(email, Html.FROM_HTML_MODE_LEGACY));}
        else{
            announcementEmail = findViewById(R.id.email);
            announcementEmail.setText(Html.fromHtml(email));
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            announcementPhone = findViewById(R.id.phone);
            announcementPhone.setText(Html.fromHtml(phone, Html.FROM_HTML_MODE_LEGACY));}
        else{
            announcementPhone = findViewById(R.id.phone);
            announcementPhone.setText(Html.fromHtml(phone));
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            announcementCost = findViewById(R.id.cost);
            announcementCost.setText(Html.fromHtml(cost, Html.FROM_HTML_MODE_LEGACY));}
        else{
            announcementCost = findViewById(R.id.cost);
            announcementCost.setText(Html.fromHtml(cost));
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            announcementDatetime = findViewById(R.id.datetime);
            announcementDatetime.setText(Html.fromHtml(datetime, Html.FROM_HTML_MODE_LEGACY));}
        else{
            announcementDatetime = findViewById(R.id.datetime);
            announcementDatetime.setText(Html.fromHtml(datetime));
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            announcementLocation = findViewById(R.id.location);
            announcementLocation.setText(Html.fromHtml(location, Html.FROM_HTML_MODE_LEGACY));}
        else{
            announcementLocation = findViewById(R.id.location);
            announcementLocation.setText(Html.fromHtml(location));
        }


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
