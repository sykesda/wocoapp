package edu.wofford.sykesda.wocoapptest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class laundryRooms extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry_rooms);

        getContent fetchLaundryRooms = new getContent(getApplicationContext());
        String jsonStringResponse = fetchLaundryRooms.requestJSON("laundry_rooms");

        final ArrayList<HashMap<String, String>> laundryRooms = new ArrayList<>();


        try {

            JSONObject jsonObjectResponse = (JSONObject) new JSONTokener(jsonStringResponse).nextValue();
            Iterator rooms = jsonObjectResponse.keys();

            while(rooms.hasNext()) {
                String currentRoom = rooms.next().toString();

                HashMap<String, String> prepare = new HashMap<>();
                prepare.put("room_code", jsonObjectResponse.getString(currentRoom));
                prepare.put("room_name", currentRoom);

                laundryRooms.add(prepare);
            }


        } catch (final JSONException e) {
            // Todo: Do something here to handle the exception.
        }

        ListView prepareToDisplay = findViewById(R.id.laundryRooms);
        ListAdapter laundryAdapter = new SimpleAdapter(getApplicationContext(), laundryRooms,
                R.layout.laundryrooms_display, new String[]{ "room_name"},
                new int[]{R.id.laundryRoomName});

        prepareToDisplay.setAdapter(laundryAdapter);

        prepareToDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HashMap<String, String> useThis = laundryRooms.get(i);

                String url = "http://m.laundryview.com/lvs.php?s=3248#/laundry_room.php?lr=" + useThis.get("room_code");
                Intent laundryRoomIntent = new Intent(Intent.ACTION_VIEW);
                laundryRoomIntent.setData(Uri.parse(url));
                startActivity(laundryRoomIntent);
            }
        });
    }
}
