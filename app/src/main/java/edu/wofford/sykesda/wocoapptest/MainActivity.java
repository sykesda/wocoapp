package edu.wofford.sykesda.wocoapptest;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button LoginBttn = (Button) findViewById(R.id.LoginBttn);
        Button ContinueAsGuestBttn = (Button) findViewById(R.id.ContinueAsGuestBttn);

        LoginBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: This is for whenever a user is trying to log in! Authentication is needed
                getApplicationContext().deleteDatabase("WoffordApp.db");
                getContent something = new getContent(getApplicationContext()); // Let's see if this works.

                // If this works, then the database stuff isn't crashing :)
                SwitchActivity();

            }
        });
    }

    protected void SwitchActivity() {

        Intent myIntent = new Intent(this, MainMenuLoggedIn.class);
        startActivity(myIntent);
    }
}
