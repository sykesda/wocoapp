package edu.wofford.sykesda.wocoapptest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class daily_announce_tags_filtering extends AppCompatActivity {

    ListView lv;
    TagsModel[] modelItems;
    int activityLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_announce_tags_filtering);

        lv = (ListView) findViewById(R.id.listView1);
        lv.setItemsCanFocus(true);

        ArrayList tagsList = this.getIntent().getStringArrayListExtra("tagList");
        ArrayList allTagsList = this.getIntent().getStringArrayListExtra("allTagsList");



        modelItems = new TagsModel[allTagsList.size()];

        for (int i = 0; i<allTagsList.size(); i++){
            if (tagsList.contains(allTagsList.get(i))){
                activityLevel = 1;
            } else { activityLevel = 0;}

            modelItems[i] = new TagsModel(allTagsList.get(i).toString(), activityLevel);
        }

        CustomAdapter adapter = new CustomAdapter(this, modelItems);
        lv.setAdapter(adapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tags_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.done_tags:
                // make tags list from selected model items
                ArrayList<String> modifiedTagsList = new ArrayList<>();
                for (TagsModel tag : modelItems){
                    if (tag.getValue() == 1){ // its selected
                        modifiedTagsList.add(tag.getName());
                    }
                }

                // send tags to previous activity
                // make new intent for tags
                Intent announcementListIntent = new Intent();

                // send tags back over to list
                //announcementListIntent.putExtra("modifiedTagList", modifiedTagsList);
                Bundle extras = new Bundle();
                extras.putSerializable("modifiedTagList",modifiedTagsList);
                announcementListIntent.putExtras(extras);


                setResult(RESULT_OK, announcementListIntent);
                finish();

                return true;


            default:
                return super.onOptionsItemSelected(item);

        }
    }



    protected class CustomAdapter extends ArrayAdapter<TagsModel> {
        TagsModel[] modelItems = null;
        Context context;

        private CustomAdapter(Context context, TagsModel[] resource) {
            super(context, R.layout.row_da_tags, resource);
            this.context = context;
            this.modelItems = resource;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.row_da_tags, parent, false);
            TextView name = (TextView) convertView.findViewById(R.id.textView1);
            CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox1);

            name.setText(modelItems[position].getName());
            if (modelItems[position].getValue() == 1)
                cb.setChecked(true);
            else
                cb.setChecked(false);

            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    //System.out.println("clicked position " + position + " ******** and has value " +modelItems[position].getValue() + "************************************");
                    modelItems[position].changeValue();
                    //System.out.println(" ******** now has value " +modelItems[position].getValue() + "************************************");
                }
            });




            return convertView;
        }
    }

}