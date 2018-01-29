package edu.wofford.sykesda.wocoapptest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class food_menu extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_menu);

        final ArrayList<ExampleItem> exampleList = new ArrayList<>();

        exampleList.add(new ExampleItem(R.drawable.ic_campus_food, "Campus Food", ""));
        exampleList.add(new ExampleItem(R.drawable.ic_cafes, "Cafes", ""));
        exampleList.add(new ExampleItem(R.drawable.ic_restaurants, "Restaurants", ""));
        exampleList.add(new ExampleItem(R.drawable.ic_discount, "Student Discounts", ""));

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(exampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ExampleItem choice = exampleList.get(position);
                if (position == 0) {
                    Intent Campus_Food = new Intent(food_menu.this, campus_food_menu.class);
                    startActivity(Campus_Food);
                }

                if (position == 1){
                    Intent Cafes = new Intent(food_menu.this, cafes.class);
                    startActivity(Cafes);
                }

                if (position == 2){
                    Intent Restaurants = new Intent(food_menu.this, restaurants.class);
                    startActivity(Restaurants);
                }

                if (position == 3){
                    Intent Discount = new Intent(food_menu.this, student_activity.class);
                    startActivity(Discount);
                }

            }
        });
    }
}
