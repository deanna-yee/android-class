/*
* Deanna Yee
* deannayee@my.smccd.edu
* CIS 135 OL
* File Name: MainActivity.java
* File Description: contains the class defintion for the main activity
* Assignment #: Midterm
* Date: 3/12/17
*/
package com.yee.chinesezodiac;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
        implements ChineseZodiacListFragment.OnChineseZodiacSelected{

    //displays the list of chinese zodiacs
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.root_layout, ChineseZodiacListFragment.newInstance(),
                            "chineseZodiacList").commit();
        }
    }

    //changes from the list layout to the detail layout
    @Override
    public void onChineseZodiacSelected(int imageResId, String name, String description, String url){
        final ChineseZodiacDetails detailsFragment = ChineseZodiacDetails.newInstance(imageResId,
                name, description, url);
        getSupportFragmentManager().beginTransaction().replace(R.id.root_layout, detailsFragment,
                "chineseZodiacDetails").addToBackStack(null).commit();
    }
}
