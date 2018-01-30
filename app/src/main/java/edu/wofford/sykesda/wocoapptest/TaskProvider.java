package edu.wofford.sykesda.wocoapptest;

import android.content.ContentProvider;
import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;


public class TaskProvider extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "WoffordApp.db";

    private Context theContext;

    public TaskProvider(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        theContext = context;
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE keyToJSON (\"theKey\" INT(2), \"JSON\" TEXT);");
        // db.execSQL("INSERT INTO keyToJSON (theKey, JSON) VALUES (\"something\", \"{lmao}\")");
        updateLocalData getReady = new updateLocalData();

        getReady.execute();
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over

        db.execSQL("DROP TABLE IF EXISTS keyToJSON");
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // onUpgrade(db, oldVersion, newVersion);

        // TODO: We might not want to edit this one.
    }

    public void fixIt() {


        updateLocalData getReady = new updateLocalData();
        getReady.execute();
    }

    public class updateLocalData extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {

            DateFormat myFormat = new SimpleDateFormat("yyyy-mm-dd", Locale.US);
            Date currentDate = new Date();

            String actualDate = myFormat.format(currentDate);

            HttpHandler sh = new HttpHandler();
            String url = "http://104.131.35.222:5000/changes?date=20150101";

            return sh.makeServiceCall(url);

        }

        @Override
        protected void onPostExecute(String s) {

            try {

                TaskProvider dbHandler = new TaskProvider(theContext);
                SQLiteDatabase theDatabase = dbHandler.getWritableDatabase();

                JSONObject result = new JSONObject(s);
                Iterator<String> theIterator = result.keys();

                while(theIterator.hasNext()) {
                    String currentKey = theIterator.next();
                    String value = result.get(currentKey).toString();

                    theDatabase.execSQL("INSERT INTO keyToJSON (theKey, JSON) VALUES (?, ?)", new String[]{currentKey, value});
                }

            } catch (final JSONException e) {
                // TODO: Do something here with this exception
            }

        }
    }
}