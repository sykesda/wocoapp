package edu.wofford.sykesda.wocoapptest;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class ResidentAssistant extends AppCompatActivity {
    private static final int REQUEST_CALL = 1;
    private static final String TAG = ResidentAssistant.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_resident_assistant);
        setContentView(R.layout.activity_call_emergency_golf);
        final ArrayList<ExampleItem> exampleList = new ArrayList<>();
        // TODO fix the texts on each item
        exampleList.add(new ExampleItem(R.drawable.ic_resident_assistants, "Marsh", "Click here to make a call to request a golf cart ride."));
        exampleList.add(new ExampleItem(R.drawable.ic_resident_assistants, "Greene/Carlisle", "Click here call Campus Safety to report a crime on campus."));
        exampleList.add(new ExampleItem(R.drawable.ic_resident_assistants, "Shipp/DuPre", "Click here to call 9-1-1 to report a medical emergency."));
        exampleList.add(new ExampleItem(R.drawable.ic_resident_assistants, "Lesesne/Wightman", "Click here to call 9-1-1 to report a medical emergency."));
        exampleList.add(new ExampleItem(R.drawable.ic_resident_assistants, "Village", "Click here to call 9-1-1 to report a medical emergency."));

        final ExampleItem golf_cart = exampleList.get(0);
        ExampleItem campus_safety = exampleList.get(1);
        ExampleItem medical_emergency = exampleList.get(2);

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
                if (position == 4){
                    makePhoneCall("8648097311");
                }
                if (position == 3){
                    makePhoneCall("8648097922");
                }
                if (position == 2){
                    makePhoneCall("8648097921");
                }
                if (position == 1) {
                    makePhoneCall("8648097920");
                }
                if (position ==0){
                    makePhoneCall("8648097919");

                }
            }
        });
    }
    private void makePhoneCall(String number){
        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        dialIntent.setData(Uri.parse("tel:" + number));
        if (dialIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(dialIntent);
        } else {
            Log.e(TAG, "Can't resolve app for ACTION_DIAL Intent.");
        }

    }


}
