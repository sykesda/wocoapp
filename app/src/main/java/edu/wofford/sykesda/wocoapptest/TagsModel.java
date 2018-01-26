package edu.wofford.sykesda.wocoapptest;

/**
 * Created by mint on 1/23/18.
 * This class is used by the tags for daily announcements
 * http://techlovejump.com/android-listview-with-checkbox/
 */

public class TagsModel {
    String name;
    int value; /* 0 -&gt; checkbox disable, 1 -&gt; checkbox enable */

    TagsModel(String name, int value){
        this.name = name;
        this.value = value;
    }
    public String getName(){
        return this.name;
    }
    public int getValue(){
        return this.value;
    }
    public void changeValue(){
        if (this.value == 1){
            this.value = 0;
        } else {
            this.value = 1;
        }
    }

}
