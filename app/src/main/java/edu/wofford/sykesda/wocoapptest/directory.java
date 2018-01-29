package edu.wofford.sykesda.wocoapptest;

import android.content.Intent;
import android.os.AsyncTask;
// import android.support.v7.app.AlertController;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class directory extends AppCompatActivity {

    private static final String TAG = "directory";

    private ArrayList<HashMap<String, String>> theResults = new ArrayList<>();
    private ListView theView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);

        theView = (ListView) findViewById(R.id.recyleView);
        Button theButtSearch = (Button) findViewById(R.id.findPerson);

        theButtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                theResults.clear();

                EditText personObj = (EditText) findViewById(R.id.personIWant);

                getContact findContact = new getContact(personObj.getText().toString(),  null, null);
                findContact.execute();

                theView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        HashMap<String, String> useThis = theResults.get(i);

                        // Get ready to start new activity
                        Intent toStartDetails = new Intent(getApplicationContext(), directoryExtraDetails.class);
                        Bundle prepare = new Bundle();

                        prepare.putSerializable("contact", useThis);
                        toStartDetails.putExtras(prepare);

                        startActivity(toStartDetails);


                    }
                });

            }
        });


    }

    private class getContact extends AsyncTask<String, Void, String> {

        private String requestedName;
        private TextView fixHere;

        private getContact(String requestedName, TextView fixHere, RecyclerView theDisplay) {
            this.requestedName = requestedName;
            this.fixHere = fixHere;
        }

        @Override
        protected String doInBackground(String... strings) {

            HttpHandler sh = new HttpHandler();
            String url = "http://104.131.35.222:5000/directory?q=" + this.requestedName;

            return sh.makeServiceCall(url);
        }

        @Override
        protected void onPostExecute(String jsonResult) {

            try {
                JSONObject theJObject = new JSONObject(jsonResult);

                JSONArray theStudentResults = theJObject.getJSONArray("students");
                JSONArray theEmployeeResults = theJObject.getJSONArray("employees");

                ArrayList<JSONArray> twoGroups = new ArrayList<>(Arrays.asList(theStudentResults, theEmployeeResults));

                for(int e = 0; e < twoGroups.size(); e++) {
                    JSONArray currentlyUsing = twoGroups.get(e);

                    for(int i = 0; i < currentlyUsing.length(); i++) {

                        HashMap<String, String> personInfo = new HashMap<>();
                        JSONObject currentPerson = currentlyUsing.getJSONObject(i);

                        Iterator<String> theKeys = currentPerson.keys();
                        while(theKeys.hasNext()) {
                            String currentKey = theKeys.next();
                            personInfo.put(currentKey, currentPerson.getString(currentKey));
                        }

                        theResults.add(personInfo);

                    }
                }

                ListAdapter announcementAdapter = new SimpleAdapter(directory.this, theResults,
                        R.layout.directory_display_contact, new String[]{ "first", "last", "email", "middle"},
                        new int[]{R.id.firstName, R.id.lastName, R.id.emailAddr, R.id.middleName});

                theView.setAdapter(announcementAdapter);

            } catch (final JSONException e) {
                // TODO: If there's a problem with the JSON object, we'll get here. I need to do something here eventually.
            }
        }

    }


}
