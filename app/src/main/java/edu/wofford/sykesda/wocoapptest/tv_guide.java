package edu.wofford.sykesda.wocoapptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class tv_guide extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        WebView tv_guide_page;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_guide);

            tv_guide_page = findViewById(R.id.tvguide);
            tv_guide_page.setWebViewClient(new WebViewClient());
            tv_guide_page.loadUrl("https://www.wofford.edu/technology/CableChanges/");
    }
}
