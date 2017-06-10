/*
* Deanna Yee
* CIS 135 OL
* File Name: MainActivity.java
* File Description: contains the class defintion for the main activity
* Assignment #: 5
* Date: 3/31/17
*/
package com.yee.roshambo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //sets the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        //sets the traditional roshambo and initial state
        TraditionalFragment traditionalFragment = new TraditionalFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, traditionalFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_roshambo, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Determines which menu option is checked
        switch(item.getItemId()){
            case R.id.menu_traditional:
                if(!item.isChecked()){
                    item.setChecked(true);
                    //replace so traditional fragment is showing
                    TraditionalFragment traditionalFragment = new TraditionalFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainer, traditionalFragment).commit();
                }
                return true;
            case R.id.menu_lizard_spock:
                if(!item.isChecked()){
                    item.setChecked(true);
                    //replace so lizard spock fragment is showing
                    LizardSpockFragment lizardSpockFragment = new LizardSpockFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainer, lizardSpockFragment).commit();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
