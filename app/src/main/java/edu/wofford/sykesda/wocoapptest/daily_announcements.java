package edu.wofford.sykesda.wocoapptest;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class daily_announcements extends AppCompatActivity {

    private String TAG = daily_announcements.class.getSimpleName();
    private ListView lv;

    ArrayList<HashMap<String, String>> announcementList;
    ArrayList<HashMap<String, String>> eventList;
    ArrayList<HashMap<String, String>> announcementAndEventList;

    Set<String> tagSet;
    Set<String> allTagsSet;
    ArrayList modifiedTagsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_announcements);

        announcementList = new ArrayList<>();
        eventList = new ArrayList<>();
        announcementAndEventList = new ArrayList<>();
        tagSet = new HashSet<>();
        allTagsSet = new HashSet<>();

        lv = (ListView) findViewById(R.id.list);

        new GetContacts().execute();


        //Start: Code added and modified from tutorial
        //https://www.raywenderlich.com/124438/android-listview-tutorial

        final Context context = this;
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap selectedAnnounce = announcementAndEventList.get(position);

                // Create intent for passing information to detail activity
                Intent announcementDetailIntent = new Intent(context, daily_announcements_details.class);

                // Hashmap contents:
                // title contact details email phone cost datetime location

                Bundle extras = new Bundle();
                extras.putSerializable("announcementMap",selectedAnnounce);
                announcementDetailIntent.putExtras(extras);

                startActivity(announcementDetailIntent);
            }

        });

        //End: Code added and modified from tutorial


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent announcementListIntent) {
        if (resultCode == RESULT_OK) {
            if (announcementListIntent.hasExtra("modifiedTagList")) {

                Bundle tagBundle = announcementListIntent.getExtras();
                if(tagBundle != null) {
                    modifiedTagsList = (ArrayList) tagBundle.getSerializable("modifiedTagList");
                }

                displayTheList(true);
            } else {
                Log.e(TAG, "Modified Tags List not returned from child intent");
            }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.title_bar_menu, menu);

        //MenuItem tagItem = menu.findItem(R.id.action_tags);

        // Configure the search info and add any event listeners...

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_tags:
                // User chose the "Tags" item, show the ...
                processTagFiltering();
                return true;


            case R.id.submit_announcement:
                submitNewAnnouncement();
                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }



    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //Toast.makeText(daily_announcements.this,"Json Data is downloading",Toast.LENGTH_LONG).show();

        }

        private String buildAnnouncementURL(){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = sdf.format(Calendar.getInstance().getTime());

            return "http://104.131.35.222:5000/announcements?date="+currentDate;
        }

        private String makeTagsStringAndTagsSetFromJSONArray(JSONArray jsonTags){
            ArrayList<String> tagsList = new ArrayList<String>();
            try {
                for (int k=0;k<jsonTags.length();k++){
                    tagsList.add(jsonTags.getString(k));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            // add tags to tagSet
            tagSet.addAll(tagsList);
            allTagsSet.addAll(tagsList);
            // turn the array list of strings into a single string
            String tags = "";
            for (int i = 0; i < tagsList.size(); i++){
                tags += tagsList.get(i);
                tags += " ";
            }
            return tags.trim();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            String url = buildAnnouncementURL();

            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting Announcement and Event date
                    String date = "";

                    String longdate = jsonObj.getString("date");
                    Date dateObj;
                    // process date from server into new format  Tue, 30 Jan 2018 00:00:00 GMT
                    DateFormat longFormatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                    SimpleDateFormat eventDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
                    try {
                        dateObj = (Date)longFormatter.parse(longdate);
                        date = dateFormat.format(dateObj);
                    } catch (java.text.ParseException e) {
                        e.printStackTrace();
                    }


                    // Getting JSON Array node for announcements
                    JSONArray announcements = jsonObj.getJSONArray("announcements");

                    // looping through All announcements
                    for (int i = 0; i < announcements.length(); i++) {
                        JSONObject c = announcements.getJSONObject(i);
                        String title = c.getString("title");
                        String contact = c.getString("contact");
                        String details = c.getString("details");
                        String email = c.getString("email");
                        String phone = c.getString("phone");
                        // Date for announcements
                        String datetime = date + " 12:00:00";
                        // Tags node is JSON Array
                        JSONArray jsonTags = c.getJSONArray("tags");
                        String tags = makeTagsStringAndTagsSetFromJSONArray(jsonTags);

                        // temp hash map for single announcement
                        HashMap<String, String> announcementMap = new HashMap<>();

                        // adding each child node to HashMap key => value
                        announcementMap.put("title", title);
                        announcementMap.put("contact", contact);
                        announcementMap.put("details", details);
                        announcementMap.put("email", email);
                        announcementMap.put("phone", phone);
                        announcementMap.put("datetime", datetime);
                        // tags is a string of tags
                        announcementMap.put("tags", tags);

                        // adding announcement to announcement list
                        announcementList.add(announcementMap);
                    }

                    // Getting JSON Array node for events
                    JSONArray events = jsonObj.getJSONArray("events");

                    // looping through All events
                    for (int i = 0; i < events.length(); i++) {
                        JSONObject c = events.getJSONObject(i);
                        String title = c.getString("title");
                        String contact = c.getString("contact");
                        String details = c.getString("details");
                        String email = c.getString("email");
                        String phone = c.getString("phone");
                        String cost = c.getString("cost");
                        String datetime = c.getString("datetime");
                        String location = c.getString("location");
                        JSONArray jsonTags = c.getJSONArray("tags");
                        String tags = makeTagsStringAndTagsSetFromJSONArray(jsonTags);

                        // process datetime into correct format
                        try {
                            dateObj = (Date)longFormatter.parse(datetime);
                            datetime = eventDateFormat.format(dateObj);
                        } catch (java.text.ParseException e) {
                            e.printStackTrace();
                        }

                        // temp hash map for single announcement
                        HashMap<String, String> eventMap = new HashMap<>();

                        // adding each child node to HashMap key => value
                        eventMap.put("title", title);
                        eventMap.put("contact", contact);
                        eventMap.put("details", details);
                        eventMap.put("email", email);
                        eventMap.put("phone", phone);
                        eventMap.put("cost", cost);
                        eventMap.put("datetime", datetime);
                        eventMap.put("location", location);
                        eventMap.put("tags", tags);

                        // adding eventMap to eventMap list
                        eventList.add(eventMap);

                    }

                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            super.onPostExecute(result);
            displayTheList(false);
        }
    }


    protected void submitNewAnnouncement () {
        Intent browserIntent = new Intent((Intent.ACTION_VIEW), Uri.parse("http://www.wofford.edu/dailyannouncements/"));
        startActivity(browserIntent);
    }

    protected void processTagFiltering(){
        // make new intent for tags
        Intent tagsProcessingIntent = new Intent(this, daily_announce_tags_filtering.class);

        // send tags over
        ArrayList tagsList = new ArrayList(tagSet);
        tagsProcessingIntent.putStringArrayListExtra("tagList", tagsList);
        ArrayList allTagsList = new ArrayList(allTagsSet);
        tagsProcessingIntent.putStringArrayListExtra("allTagsList", allTagsList);

        startActivityForResult(tagsProcessingIntent, 100);

    }

    protected void displayTheList(boolean filtered){

        if (!filtered) {
            // Combine the 2 lists into 1
            announcementAndEventList.clear();
            announcementAndEventList.addAll(announcementList);
            announcementAndEventList.addAll(eventList);
        } else {
            // results are filtered
            ArrayList<HashMap<String, String>> tempAnnouncementAndEventList = new ArrayList<>();
            tempAnnouncementAndEventList.addAll(announcementList);
            tempAnnouncementAndEventList.addAll(eventList);

            announcementAndEventList.clear();


            // update tagSet
            tagSet.clear();
            tagSet.addAll(modifiedTagsList);

            // add the announcements and events for the chosen tags
            for (HashMap<String, String> map : tempAnnouncementAndEventList) {
                for (String tag : tagSet) {
                    if (map.get("tags").contains(tag) && !announcementAndEventList.contains(map)) {
                        announcementAndEventList.add(map);
                    }
                }
            }

        }

        ListAdapter announcementAdapter = new SimpleAdapter(daily_announcements.this, announcementAndEventList,
                R.layout.list_item, new String[]{ "datetime","title"},
                new int[]{R.id.datetime, R.id.title});


        lv.setAdapter(announcementAdapter);
    }
}