package edu.wofford.sykesda.wocoapptest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class student_activity extends AppCompatActivity {

    int[] images = {R.drawable.ic_restaurant, R.drawable.ic_movies ,R.drawable.ic_hardware, R.drawable.ic_software, R.drawable.ic_insurance,
            R.drawable.ic_shopping, R.drawable.ic_entertainment};
    String[] items = {"Restaurants Discounts", "Movies Discounts", "Hardware Discounts", "Software Discounts", "Auto Discounts", "Shopping Discounts", "Entertainment Discounts"};
    String[] descriptions = {"Click here for Restaurants information","Click here for Movies information","Click here for Hardware information", "Click here for Software information",
            "Click here for Auto insurance information", "Click here for Shopping information", "Click here for Entertainment information"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_activity);

        ListView listView = (ListView) findViewById(R.id.listView);
        //ListAdapter itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        //ListAdapter descriptionsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2);

        CustomAdapter customAdapter = new CustomAdapter();

        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent restaurant_discount_Activity = new Intent(student_activity.this, Restaurant_discount.class);
                        startActivity(restaurant_discount_Activity);
                        break;

                    case 1:
                        Intent movie_discount_Activty = new Intent(student_activity.this, Movies_discount.class);
                        startActivity(movie_discount_Activty);
                        break;

                    case 2:
                        Intent hardware_discount_Activty = new Intent(student_activity.this, Hardware_discount.class);
                        startActivity(hardware_discount_Activty);
                        break;

                    case 3:
                        Intent software_discount_Activity = new Intent(student_activity.this, Software_discounts.class);
                        startActivity(software_discount_Activity);
                        break;

                    case 4:
                        Intent auto_discount_Activty = new Intent(student_activity.this, Auto_discounts.class);
                        startActivity(auto_discount_Activty);
                        break;

                    case 5:
                        Intent shopping_discount_Activty = new Intent(student_activity.this, Shopping_discounts.class);
                        startActivity(shopping_discount_Activty);
                        break;

                    case 6:
                        Intent entertainment_discount_Activty = new Intent(student_activity.this, Entertainment_discounts.class);
                        startActivity(entertainment_discount_Activty);
                        break;
                }
            }
        });
    }


    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.customlayout,null);

            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            TextView textView_name = (TextView) view.findViewById(R.id.textView_name);
            TextView textView_descripton = (TextView) view.findViewById(R.id.textView_description);

            imageView.setImageResource(images[i]);
            textView_name.setText(items[i]);
            textView_descripton.setText(descriptions[i]);

            return view;
        }
    }
}