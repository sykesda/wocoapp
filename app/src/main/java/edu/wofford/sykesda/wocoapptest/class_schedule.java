package edu.wofford.sykesda.wocoapptest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQuery;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class class_schedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_schedule);

        TextView hey = findViewById(R.id.textView5);

        getContent test = new getContent(getApplicationContext());
        hey.setText(test.requestJSON("laundry_rooms"));

    }
}
