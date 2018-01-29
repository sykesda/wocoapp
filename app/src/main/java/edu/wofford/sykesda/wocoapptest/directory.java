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

public class directory extends AppCompatActivity {

    private static final String TAG = "directory";

    private ArrayList<HashMap<String, String>> studentResults = new ArrayList<>();
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

                if(theView.getChildCount() > 0) {
                    // TODO: Something needs to be done here to empty previous cards.
                    // For whatever reason the following function makes the app crash.
                    // Everything seems to point out that is the way to remove all the cards
                    // I suspect it's the fact that I'm trying to add more things to right after?

                    // theView.removeAllViews();
                }

                EditText personObj = (EditText) findViewById(R.id.personIWant);

                String searchFor = personObj.toString();

                getContact werkItBaby = new getContact(personObj.getText().toString(),  null, null);
                werkItBaby.execute();

                Intent moreDetails = new Intent(getApplicationContext(), directoryExtraDetails.class);

                theView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        HashMap<String, String> useThis = studentResults.get(i);

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

        // private ArrayList<HashMap<String, String>> studentResults = new ArrayList<>();

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

                ArrayList<JSONArray> Everything = new ArrayList<>(Arrays.asList(theStudentResults, theEmployeeResults));

                for (int sm = 0; sm < Everything.size(); sm++) {

                    JSONArray currentObject = Everything.get(sm);

                    for (int i = 0; i < currentObject.length(); i++) {

                        JSONObject current = currentObject.getJSONObject(i);

                        String firstname, lastname, middlename, phone, preferred, suffix;

                        ArrayList<String> fields = new ArrayList<>(Arrays.asList("firstname",
                                "lastname",
                                "middlename",
                                "phone",
                                "preferred",
                                "suffix",
                                "email"));

                        HashMap<String, String> thisResult = new HashMap<>();

                        for (int holder = 0; holder < fields.size(); holder++) {
                            String currentField = fields.get(holder);
                            thisResult.put(currentField, current.getString(currentField));
                        }

                        studentResults.add(thisResult);

                    }

                }

            } catch (final JSONException e) {
                // TODO: do something here with the exception. This is what causing the crash, I think
            }

            ListAdapter announcementAdapter = new SimpleAdapter(directory.this, studentResults,
                    R.layout.directory_display_contact, new String[]{ "firstname", "lastname", "email", "middlename"},
                    new int[]{R.id.firstName, R.id.lastName, R.id.emailAddr, R.id.middleName});

            theView.setAdapter(announcementAdapter);
        }

    }


}
