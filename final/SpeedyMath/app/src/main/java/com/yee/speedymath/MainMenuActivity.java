package com.yee.speedymath;

/*
* Deanna Yee
* CIS 135 OL
* File Name: MainMenuActivity.java
* File Description: contains the class definition for the main menu activity
* Assignment #: final
* Date: 5/18/17
*/

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener{

    private GamePlay gamePlay;
    private Button add;
    private Button sub;
    private Button mult;
    private Button random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //sets the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initialize gamePlay
        gamePlay = new GamePlay();

        //get buttons
        add = (Button) findViewById(R.id.add);
        sub = (Button) findViewById(R.id.sub);
        mult = (Button) findViewById(R.id.mult);
        random = (Button) findViewById(R.id.random);

        //set listeners
        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        mult.setOnClickListener(this);
        random.setOnClickListener(this);

    }

    //Determines which button is pressed
    public void onClick(View v){
        switch (v.getId()){
            case R.id.add:
                gamePlay.setMathSymbol("add");
                createIntent();
                break;
            case R.id.sub:
                gamePlay.setMathSymbol("sub");
                createIntent();
                break;
            case R.id.mult:
                gamePlay.setMathSymbol("mult");
                createIntent();
                break;
            case R.id.random:
                gamePlay.setMathSymbol("random");
                createIntent();
                break;
            default:
                break;
        }
    }

    //create intent and pass GamePlay to NumberActivity
    public void createIntent(){
        Intent intent = new Intent(MainMenuActivity.this, NumberActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("gamePlay", gamePlay);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_time, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //Determines which menu option is checked
        switch(item.getItemId()){
            case R.id.menu_30_seconds:
                item.setChecked(true);
                gamePlay.setTime(30000);
                return true;
            case R.id.menu_1_minute:
                item.setChecked(true);
                gamePlay.setTime(60000);
                return true;
            case R.id.menu_1_5_minutes:
                item.setChecked(true);
                gamePlay.setTime(90000);
                return true;
            case R.id.menu_2_minutes:
                item.setChecked(true);
                gamePlay.setTime(120000);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
