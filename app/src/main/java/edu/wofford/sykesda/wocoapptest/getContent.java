package edu.wofford.sykesda.wocoapptest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import edu.wofford.sykesda.wocoapptest.TaskProvider;


public class getContent {

    private TaskProvider dbHelper;
    private SQLiteDatabase db;

    public getContent(Context context) {
        dbHelper = new TaskProvider(context);
        db = dbHelper.getReadableDatabase();
    }

    public String requestJSON(String requestedContent) {

        Cursor result = db.rawQuery("SELECT * FROM keyToJSON WHERE theKey=?", new String[]{requestedContent});

        if(result.moveToFirst()) {

            return result.getString(result.getColumnIndex("JSON"));

        } else {
            return "{}";
        }

    }

}
