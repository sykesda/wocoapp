package edu.wofford.sykesda.wocoapptest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class study extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        final ArrayList<ExampleItem> exampleList = new ArrayList<>();

        exampleList.add(new ExampleItem(R.mipmap.ic_library_foreground, "Library", "Click here to access the library website"));
        exampleList.add(new ExampleItem(R.mipmap.ic_degree_works_foreground, "Degree Works", "Click here to access the Degree Works webpage"));
        exampleList.add(new ExampleItem(R.mipmap.ic_moodle_foreground, "Moodle", "Click here to access the Moodle website."));
        exampleList.add(new ExampleItem(R.mipmap.ic_tutoring_foreground, "Learning Assistance", "Click here to access the Learning Assistance webpage."));

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(exampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ExampleItem thing = exampleList.get(position);
                if (position == 3){
                    visitSite("http://www.wofford.edu/learningAssistance/");
                }
                if (position == 2){
                    visitSite("http://moodle2.wofford.edu/moodle/");
                }
                if(position == 1){
                    visitSite("http://degreeworks.wofford.edu/");
                }
                if (position == 0){
                    visitSite("https://www.wofford.edu/library/");
                }
            }
        });
    }

    private  void  visitSite(String url){
        Intent siteIntent = new Intent(Intent.ACTION_VIEW);
        siteIntent.setData(Uri.parse(url));
        startActivity(siteIntent);
    }
}
