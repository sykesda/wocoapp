package edu.wofford.sykesda.wocoapptest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class daily_announcements extends AppCompatActivity {

    private String TAG = daily_announcements.class.getSimpleName();
    private ListView lv;

    ArrayList<HashMap<String, String>> announcementList;
    ArrayList<HashMap<String, String>> eventList;
    ArrayList<HashMap<String, String>> announcementAndEventList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_announcements);

        announcementList = new ArrayList<>();
        eventList = new ArrayList<>();

        announcementAndEventList = new ArrayList<>();

        lv = (ListView) findViewById(R.id.list);

        new GetContacts().execute();

    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //Toast.makeText(daily_announcements.this,"Json Data is downloading",Toast.LENGTH_LONG).show();

        }

        private String buildAnnouncementURL(){
            return "http://104.131.35.222:5000/announcements?date=2018-01-10";
        }

        private String makeTagsStringFromJSONArray(JSONArray jsonTags){
            ArrayList<String> tagsList = new ArrayList<String>();
            try {
                for (int k=0;k<jsonTags.length();k++){
                    tagsList.add(jsonTags.getString(k));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
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
                    String date = jsonObj.getString("date");

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
                        String datetime = "Today";
                        // Tags node is JSON Array
                        JSONArray jsonTags = c.getJSONArray("tags");
                        String tags = makeTagsStringFromJSONArray(jsonTags);

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
                        String tags = makeTagsStringFromJSONArray(jsonTags);

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
                    // TODO create a list item at the bottom that links to adding your own announcement
                    // TODO continued:      actually shrink listView and add the button that links to the form

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

            // Combine the 2 lists into 1
            announcementAndEventList.addAll(announcementList);
            announcementAndEventList.addAll(eventList);

            ListAdapter announcementAdapter = new SimpleAdapter(daily_announcements.this, announcementAndEventList,
                    R.layout.list_item, new String[]{ "datetime","title"},
                    new int[]{R.id.datetime, R.id.title});


            lv.setAdapter(announcementAdapter);


        }
    }
}