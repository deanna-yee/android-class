package com.yee.longevitycalculator;

/*
* Deanna Yee
* CIS 135 OL
* File Name: LongevityActivity.java
* File Description: contains the class defintion for the longevity activity
* and displays the age, bmi, and life expectancy
* Assignment #: 5
* Date: 4/3/17
*/

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class LongevityActivity extends AppCompatActivity {

    private TextView ageView;
    private TextView bmiView;
    private TextView lifeExpectancyView;
    private TextView statusView;
    private Button modifyDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_longevity);

        DecimalFormat decimalFormat = new DecimalFormat(".##");

        //get text views
        ageView = (TextView) findViewById(R.id.age_info);
        bmiView = (TextView) findViewById(R.id.bmi);
        lifeExpectancyView = (TextView) findViewById(R.id.lifeExpectancy);
        statusView = (TextView) findViewById(R.id.status);

        //get button
        modifyDataButton = (Button) findViewById(R.id.modifyDataButton);

        final Lifetime lifetime = (Lifetime) getIntent().getSerializableExtra("Lifetime");

        //set information to text
        ageView.setText(String.valueOf(lifetime.getAge()));
        bmiView.setText(String.valueOf(decimalFormat.format(lifetime.getBMI())));
        lifeExpectancyView.setText(String.valueOf(decimalFormat.format(
                lifetime.calculateLifeExpectancy()) + " years"));
        statusView.setText(lifetime.getMessages());

        modifyDataButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //pass lifetime back to Main activity
                Intent intent = new Intent(LongevityActivity.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Lifetime", lifetime);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
