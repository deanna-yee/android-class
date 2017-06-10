package com.yee.longevitycalculator;

/*
* Deanna Yee
* CIS 135 OL
* File Name: MainActivity.java
* File Description: contains the class defintion for the main activity
* Assignment #: 5
* Date: 4/3/17
*/

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private RadioGroup genderGroup;
    private RadioButton genderButton;
    private Button doneButton;
    private Spinner smokingSpinner;
    private Spinner lifestyleSpinner;
    private EditText ageEditText;
    private EditText weightEditText;
    private EditText heightInchesEditText;
    private EditText heightFeetEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Spinner elements
        smokingSpinner = (Spinner) findViewById(R.id.smokingSpinner);
        lifestyleSpinner = (Spinner) findViewById(R.id.lifestyleSpinner);

        //create spinners
        createSpinners();

        //get gender group
        genderGroup = (RadioGroup)findViewById(R.id.gender);

        //get edit texts
        ageEditText = (EditText) findViewById(R.id.age);
        weightEditText = (EditText) findViewById(R.id.weight);
        heightInchesEditText = (EditText) findViewById(R.id.heightInches);
        heightFeetEditText = (EditText) findViewById(R.id.heightFeet);

        //get button
        doneButton = (Button) findViewById(R.id.done);

        //set on click listener to done button
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //creates a lifetime from information
                Lifetime lifetime = createLifetime();

                //pass lifetime to longevity activity
                Intent intent = new Intent(MainActivity.this, LongevityActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Lifetime", lifetime);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        //displays old information from the main activity
        displayInfoFromLifetime();

    }

    //creates the information stored on the spinners
    public void createSpinners(){

        //Spinner click listener
        smokingSpinner.setOnItemSelectedListener(this);
        lifestyleSpinner.setOnItemSelectedListener(this);

        //smoking spinner drop down element
        List<String> smoking = new ArrayList<String>();
        smoking.add("Never");
        smoking.add("Quit 2+ years ago");
        smoking.add("Smoker");

        //lifestyle spinner drop down element
        List<String> lifestyle = new ArrayList<String>();
        lifestyle.add("Couch potato");
        lifestyle.add("Some exercise/good diet");
        lifestyle.add("Super healthy");

        //Creating adapter for lifestyle and smoking spinner
        ArrayAdapter<String> smokingDataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, smoking);
        ArrayAdapter<String> lifeStyleDataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lifestyle);

        //Drop down layout style - list view with radio button
        smokingDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lifeStyleDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //attaching data adapter to spinner
        smokingSpinner.setAdapter(smokingDataAdapter);
        lifestyleSpinner.setAdapter(lifeStyleDataAdapter);
    }

    //creates a lifetime from the information in main activity
    public Lifetime createLifetime(){
        //get information from the main activity
        int gender = genderGroup.getCheckedRadioButtonId();
        int age = Integer.valueOf(ageEditText.getText().toString());
        int weight = Integer.valueOf(weightEditText.getText().toString());
        int heightFeet = Integer.valueOf(heightFeetEditText.getText().toString());
        int heightInches = Integer.valueOf(heightInchesEditText.getText().toString());
        int smokingChoice = smokingSpinner.getSelectedItemPosition();
        int lifeStyleChoice = lifestyleSpinner.getSelectedItemPosition();

        Lifetime lifetime = new Lifetime(gender, age, weight, heightFeet, heightInches,
                smokingChoice, lifeStyleChoice);

        return lifetime;
    }

    //displays the old information from the main activity
    public void displayInfoFromLifetime(){
        //get lifetime from Longevity activity
        Lifetime lifetime = (Lifetime) getIntent().getSerializableExtra("Lifetime");
        if(lifetime != null){
            //display all the information that was filled in before
            genderButton = (RadioButton) findViewById(lifetime.getGender());
            genderButton.setChecked(true);
            ageEditText.setText(String.valueOf(lifetime.getAge()));
            weightEditText.setText(String.valueOf(lifetime.getWeight()));
            heightFeetEditText.setText(String.valueOf(lifetime.getHeightFeet()));
            heightInchesEditText.setText(String.valueOf(lifetime.getHeightInches()));
            smokingSpinner.setSelection(lifetime.getSmoking());
            lifestyleSpinner.setSelection(lifetime.getLifestyle());
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    public void onNothingSelected(AdapterView<?> arg0){

    }
}
