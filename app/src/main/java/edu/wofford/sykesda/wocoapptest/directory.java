package edu.wofford.sykesda.wocoapptest;

import android.os.AsyncTask;
// import android.support.v7.app.AlertController;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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

public class directory extends AppCompatActivity {

    private static final String TAG = "directory";

    // TextView werkIt = (TextView) findViewById(R.id.displayHere);

    //private ArrayList<HashMap<String, String>> useInfo;
    //private ListView hello = (ListView) findViewById(R.id.recyleView);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);


        Button theButtSearch = (Button) findViewById(R.id.findPerson);
        //final TextView werkIt = (TextView) findViewById(R.id.displayHere);

        theButtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText personObj = (EditText) findViewById(R.id.personIWant);

                String searchFor = personObj.toString();

                getContact werkItBaby = new getContact(personObj.getText().toString(),  null, null);
                werkItBaby.execute();

            }
        });


    }

    private class getContact extends AsyncTask<String, Void, String> {

        private String requestedName;
        private TextView fixHere;

        private ListView theView = (ListView) findViewById(R.id.recyleView);

        private ArrayList<HashMap<String, String>> studentResults = new ArrayList<>();

        private getContact(String requestedName, TextView fixHere, RecyclerView theDisplay) {
            this.requestedName = requestedName;
            this.fixHere = fixHere;
        }

        @Override
        protected String doInBackground(String... strings) {

            HttpHandler sh = new HttpHandler();
            String url = "http://104.131.35.222:5000/directory?q=" + this.requestedName;
            String jsonStr = sh.makeServiceCall(url);

            return jsonStr;
        }

        @Override
        protected void onPostExecute(String jsonResult) {

            try {
                JSONObject theJObject = new JSONObject(jsonResult);

                JSONArray theStudentResults = theJObject.getJSONArray("students");
                JSONArray theEmployeeResults = theJObject.getJSONArray("employees");

                for (int i = 0; i < theStudentResults.length(); i++) {

                    JSONObject current = theStudentResults.getJSONObject(i);

                    String firstname, lastname, middlename, phone, preferred, suffix;

                    ArrayList<String> fields = new ArrayList<>(Arrays.asList(   "firstname",
                                                                                    "lastname",
                                                                                    "middlename",
                                                                                    "phone",
                                                                                    "preferred",
                                                                                    "suffix",
                                                                                    "email"));

                    HashMap<String, String> thisResult = new HashMap<>();

                    for(int _ = 0; _ < fields.size(); _++) {
                        String currentField = fields.get(_);
                        thisResult.put(currentField, current.getString(currentField));
                    }

                    studentResults.add(thisResult);

                }

            } catch (final JSONException e) {
                // TODO: do something here with the exception
            }

            ListAdapter announcementAdapter = new SimpleAdapter(directory.this, studentResults,
                    R.layout.directory_display_contact, new String[]{ "firstname", "lastname", "email"},
                    new int[]{R.id.firstName, R.id.lastName, R.id.emailAddr});

            theView.setAdapter(announcementAdapter);
        }

    }


}
