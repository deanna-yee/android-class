package com.yee.speedymath;

/*
* Deanna Yee
* CIS 135 OL
* File Name: NumberActivity.java
* File Description: contains the class definition for the number activity
* Assignment #: final
* Date: 5/18/17
*/

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class NumberActivity extends AppCompatActivity implements View.OnClickListener{

    private GamePlay gamePlay;
    private Button number_1;
    private Button number_2;
    private Button number_3;
    private Button number_4;
    private Button number_5;
    private Button number_6;
    private Button number_7;
    private Button number_8;
    private Button number_9;
    private Button number_10;
    private Button number_11;
    private Button number_12;
    private Button number_random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        //get buttons
        number_1 = (Button) findViewById(R.id.number_1);
        number_2 = (Button) findViewById(R.id.number_2);
        number_3 = (Button) findViewById(R.id.number_3);
        number_4 = (Button) findViewById(R.id.number_4);
        number_5 = (Button) findViewById(R.id.number_5);
        number_6 = (Button) findViewById(R.id.number_6);
        number_7 = (Button) findViewById(R.id.number_7);
        number_8 = (Button) findViewById(R.id.number_8);
        number_9 = (Button) findViewById(R.id.number_9);
        number_10 = (Button) findViewById(R.id.number_10);
        number_11 = (Button) findViewById(R.id.number_11);
        number_12 = (Button) findViewById(R.id.number_12);
        number_random = (Button) findViewById(R.id.number_random);

        //set click listener to buttons
        number_1.setOnClickListener(this);
        number_2.setOnClickListener(this);
        number_3.setOnClickListener(this);
        number_4.setOnClickListener(this);
        number_5.setOnClickListener(this);
        number_6.setOnClickListener(this);
        number_7.setOnClickListener(this);
        number_8.setOnClickListener(this);
        number_9.setOnClickListener(this);
        number_10.setOnClickListener(this);
        number_11.setOnClickListener(this);
        number_12.setOnClickListener(this);
        number_random.setOnClickListener(this);

        //get gamePlay from main menu activity
        gamePlay = (GamePlay) getIntent().getSerializableExtra("gamePlay");
    }

    //Determine which button is pressed and set number to that number
    //if random set the number to 0
    public void onClick(View v){
        switch (v.getId()){
            case R.id.number_1:
                gamePlay.setNumber(1);
                createIntent();
                break;
            case R.id.number_2:
                gamePlay.setNumber(2);
                createIntent();
                break;
            case R.id.number_3:
                gamePlay.setNumber(3);
                createIntent();
                break;
            case R.id.number_4:
                gamePlay.setNumber(4);
                createIntent();
                break;
            case R.id.number_5:
                gamePlay.setNumber(5);
                createIntent();
                break;
            case R.id.number_6:
                gamePlay.setNumber(6);
                createIntent();
                break;
            case R.id.number_7:
                gamePlay.setNumber(7);
                createIntent();
                break;
            case R.id.number_8:
                gamePlay.setNumber(8);
                createIntent();
                break;
            case R.id.number_9:
                gamePlay.setNumber(9);
                createIntent();
                createIntent();
                break;
            case R.id.number_10:
                gamePlay.setNumber(10);
                createIntent();
                break;
            case R.id.number_11:
                gamePlay.setNumber(11);
                createIntent();
                break;
            case R.id.number_12:
                gamePlay.setNumber(12);
                createIntent();
                break;
            case R.id.number_random:
                gamePlay.setNumber(0);
                createIntent();
                break;
        }
    }

    //create intent and pass GamePlay to NumberActivity
    public void createIntent(){
        Intent intent = new Intent(NumberActivity.this, ProblemsActivity.class);
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
