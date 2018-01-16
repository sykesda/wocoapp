package edu.wofford.sykesda.wocoapptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class daily_announcements_details extends AppCompatActivity {

    private TextView announcementTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_announcements_details);

        //Start: Code copied and edited from tutorial
        // 1
        String title = this.getIntent().getExtras().getString("title");
        // url = this.getIntent().getExtras().getString("url");

        // 2 change from title in title bar to a title in TextView
        //setTitle(title);
        announcementTitle = findViewById(R.id.titleTextView);
        announcementTitle.setText(title);

        // 3
        //mWebView = (WebView) findViewById(R.id.detail_web_view);

        // 4
        //mWebView.loadUrl(url);
        //End: Code copied and edited from tutorial
    }
}
