/*
* Deanna Yee
* CIS 135 OL
* File Name: MainActivity.java
* File Description: contains the class defintion for the main activity
* Assignment #: 1
* Date: 2/1/17
*/
package com.yee.bmicalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {
    private BMICalculator bmiCalc;
    public NumberFormat bmiFormat = NumberFormat.getInstance();
    private EditText weightEditText;
    private EditText heightEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //create a bmi calculator
        bmiCalc = new BMICalculator(1.0f, 1.0f);
        //bmiFormat formatted to display one decimal place after decimal point
        bmiFormat.setMaximumFractionDigits(1);
        bmiFormat.setMinimumFractionDigits(1);

        setContentView(R.layout.activity_main);

        //get EditText for both weight and height
        weightEditText = (EditText) findViewById(R.id.amount_weight);
        heightEditText = (EditText) findViewById(R.id.amount_height);

        //add a text handler to both EditText for weight and height
        TextChangeHandler textChangeHandler = new TextChangeHandler();
        weightEditText.addTextChangedListener(textChangeHandler);
        heightEditText.addTextChangedListener(textChangeHandler);
    }

    public void calculate(){
        //get value from EditText for weight and height
        String weightString = weightEditText.getText().toString();
        String heightString = heightEditText.getText().toString();

        //get the TextView for bmi and status
        TextView bmiTextView = (TextView) findViewById(R.id.amount_bmi);
        TextView statusTextView = (TextView) findViewById(R.id.status);

        try{
            //convert weightString and heightString to floats
            float weightAmount = Float.parseFloat(weightString);
            float heightAmount = Float.parseFloat(heightString);

            //update the bmiCalc to the values in the EditText
            bmiCalc.setWeight(weightAmount);
            bmiCalc.setHeight(heightAmount);

            //get the bmi and status from bmiCalc
            float bmi = bmiCalc.calculateBMI();
            String status = bmiCalc.bmiStatus(bmi);

            //update view with formatted bmi and the status
            bmiTextView.setText(bmiFormat.format(bmi));
            statusTextView.setText(status);

        }catch (NumberFormatException nfe){
            /*do not display anything in bmi and status if there is no value in 
              weight or height*/
            bmiTextView.setText("");
            statusTextView.setText("");
        }
    }

    private class TextChangeHandler implements TextWatcher {

        //when the value of height or weight changes calculate the bmi and status
        public void afterTextChanged(Editable e) {
            calculate();
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after){
            //needs to be implemented because it's an abstract method of TextWatcher
        }

        public void onTextChanged(CharSequence s, int start, int before, int after) {
            //needs to be implemented because it's an abstract method of TextWatcher
        }
    }
}
