package edu.wofford.sykesda.wocoapptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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

public class tv_guide extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //WebView tv_guide_page;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_guide);

        // The following list contains the channels we're supposed to have.
        ArrayList<HashMap<String, String>> channels = new ArrayList<>();

        getContent fetchContent = new getContent(getApplicationContext());
        String jsonString = fetchContent.requestJSON("tv_guide");

        try {

            JSONObject channelList = (JSONObject) new JSONTokener(jsonString).nextValue();
            Iterator actualChannels = channelList.keys();

            while(actualChannels.hasNext()) {
                HashMap<String, String> prepareHash = new HashMap<>();
                String channelName = actualChannels.next().toString();
                prepareHash.put("channel_name", channelName);
                prepareHash.put("channel_number", channelList.getString(channelName));

                channels.add(prepareHash);
            }


        } catch(final JSONException e) {
            // TODO: Do something here if the JSON request failed.
            HashMap<String, String> prepareToAdd = new HashMap<>();
            prepareToAdd.put("channel_info", jsonString);
            channels.add(prepareToAdd);
        }

        ListView theListView = findViewById(R.id.channelGuide);
        ListAdapter announcementAdapter = new SimpleAdapter(getApplicationContext(), channels,
                R.layout.channel_guide_displaybox, new String[]{ "channel_name", "channel_number"},
                new int[]{R.id.tvChannelName, R.id.tvChannelNumber});

        theListView.setAdapter(announcementAdapter);

//            tv_guide_page = findViewById(R.id.tvguide);
//            tv_guide_page.setWebViewClient(new WebViewClient());
//            tv_guide_page.loadUrl("https://www.wofford.edu/technology/CableChanges/");
    }
}
