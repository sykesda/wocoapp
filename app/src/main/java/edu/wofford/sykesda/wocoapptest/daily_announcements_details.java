package edu.wofford.sykesda.wocoapptest;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
    private TextView announcementDate;
    private TextView announcementTime;
    private TextView announcementLocation;

    String title;
    String contact;
    String details;
    String email;
    String phone;
    String cost;
    String datetime;
    String date;
    String time;
    String location;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_announcements_details);


        Bundle announcementBundle = this.getIntent().getExtras();
        if(announcementBundle != null) {
            announcementMap = (HashMap) announcementBundle.getSerializable("announcementMap");

            // pull out the different fields: title contact details email phone cost datetime location
            title = (String) announcementMap.get("title");
            contact = (String) announcementMap.get("contact");
            details = (String) announcementMap.get("details");
            email = (String) announcementMap.get("email");
            phone = (String) announcementMap.get("phone");
            datetime = (String) announcementMap.get("datetime");
            cost = (String) announcementMap.get("cost");
            location = (String) announcementMap.get("location");

            //Ex. datetime: 2018-01-12 10:00:00.000000

            //Set date in MM-DD-YYYY format
            date = datetime.substring(5, 10) + "-" + datetime.substring(0,4);

            //Set time to display both standard and military time
            if(Integer.parseInt(datetime.substring(11,13)) > 12){
                time = Integer.toString(Integer.parseInt(datetime.substring(11,13)) - 12) + datetime.substring(13,16) + " PM " + "(" + datetime.substring(11,16) + ")";
            } else if (Integer.parseInt(datetime.substring(11, 13)) == 12) {
                time = datetime.substring(11, 16) + " PM " + "(" + datetime.substring(11,16) + ")";
            } else {
                time = datetime.substring(11, 16) + " AM " + "(" + datetime.substring(11, 16) + ")";

            }
        }


        announcementTitle = findViewById(R.id.title);
        announcementContact = findViewById(R.id.contact);
        announcementDetails = findViewById(R.id.details);
        announcementEmail = findViewById(R.id.email);
        announcementPhone = findViewById(R.id.phone);
        announcementCost = findViewById(R.id.cost);
        announcementDate = findViewById(R.id.date);
        announcementTime = findViewById(R.id.time);
        announcementLocation = findViewById(R.id.location);


        // Set the values of TextViews in the UI and parse for HTML
        // If/Else checks the Android system version, using the appropriate Html.fromHtml() for pre-Nougat versions and for Nougat and later
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {

            announcementTitle.setText(Html.fromHtml(title, Html.FROM_HTML_MODE_LEGACY));
            announcementContact.setText(Html.fromHtml("<b>Contact: " + contact + "</b>", Html.FROM_HTML_MODE_LEGACY));
            announcementDetails.setText(Html.fromHtml(details, Html.FROM_HTML_MODE_LEGACY));
            announcementEmail.setText(Html.fromHtml(email, Html.FROM_HTML_MODE_LEGACY));
            announcementPhone.setText(Html.fromHtml(phone, Html.FROM_HTML_MODE_LEGACY));
            announcementDate.setText(Html.fromHtml("<b>" + date + "</b>", Html.FROM_HTML_MODE_LEGACY));
            announcementTime.setText(Html.fromHtml("<b>" + time + "</b>", Html.FROM_HTML_MODE_LEGACY));

            if (cost != null && !cost.isEmpty()){
                announcementCost.setText(Html.fromHtml("<b>Cost: </b>" + cost, Html.FROM_HTML_MODE_LEGACY));
            }else{
                announcementCost.setText(Html.fromHtml("", Html.FROM_HTML_MODE_LEGACY));
            }
            if (location != null && !location.isEmpty()) {
                announcementLocation.setText(Html.fromHtml("<b>" + location + "</b>", Html.FROM_HTML_MODE_LEGACY));
            }else{
                announcementLocation.setText(Html.fromHtml("", Html.FROM_HTML_MODE_LEGACY));
            }
        }

        else{

            announcementTitle.setText(Html.fromHtml(title));
            announcementContact.setText(Html.fromHtml("<b>Contact: " + contact + "</b>"));
            announcementDetails.setText(Html.fromHtml(details));
            announcementEmail.setText(Html.fromHtml(email));
            announcementPhone.setText(Html.fromHtml(phone));
            announcementDate.setText(Html.fromHtml("<b>" + date + "</b>"));
            announcementTime.setText(Html.fromHtml("<b>" + time + "</b>"));

            if (cost != null && !cost.isEmpty()){
                announcementCost.setText(Html.fromHtml("<b>Cost: </b>" + cost));
            }else{
                announcementCost.setText(Html.fromHtml("" ));
            }
            if (location != null && !location.isEmpty()){
                announcementLocation.setText(Html.fromHtml("<b>" + location + "</b>"));
            }else{
                announcementLocation.setText(Html.fromHtml("" ));
            }
        }

        // add to calendar button
        final ImageButton openAddToCalendar = findViewById(R.id.myFAB);
        openAddToCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCalendarEvent(v);
            }
        });

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
