package edu.wofford.sykesda.wocoapptest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class avi extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avi);
        final ArrayList<ExampleItem> exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem(R.mipmap.ic_nutrifacts_foreground, "NutriFacts", "Click here to get nutrition facts for your food"));
        exampleList.add(new ExampleItem(R.mipmap.ic_recipes_foreground, "Share Recipes", "Click here to share your recipes"));
        exampleList.add(new ExampleItem(R.mipmap.ic_feedback_foreground, "Provide Feedback", "Click here to give your feedback"));
        exampleList.add(new ExampleItem(R.mipmap.ic_dietician_foreground, "Dietician", "Click here to ask dietary questions"));

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
                    visitSite("http://www.avinutrisource.com/nutrilink.php");
                }
                if (position == 2){
                    visitSite("https://www.aviserves.com/wofford/share-your-experience.html");
                }
                if (position == 1){
                    visitSite("https://www.aviserves.com/wofford/recipes-from-home.html");
                }
                if (position == 0){
                    visitSite("http://www.avinutrisource.com/nutrifacts.php");
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
