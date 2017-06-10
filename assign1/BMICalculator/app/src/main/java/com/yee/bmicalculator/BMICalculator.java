package com.yee.bmicalculator;

/**
 * Deanna Yee
 * CIS 135 OL
 * File Name: BMICalculator.java
 * File Description: contains the class definition to calculate the BMI and determine 
 	if a person is obese, overweight, normal, underweight based on their height 
 	and weight
 * Assignment #: 1
 * 2/1/17
 */

public class BMICalculator {
    private float weight;
    private float height;

    //constructor for BMICalculator with a parameter for weight and height
    public BMICalculator(float newWeight, float newHeight) {
        setWeight(newWeight);
        setHeight(newHeight);

    }

    //gets the weight
    public float getWeight() {
        return weight;
    }

    //gets the height
    public  float getHeight() {
        return height;
    }

    /*sets the weight to a new value
     *does not allow values less than or equal to 0*/
    public void setWeight(float newWeight) {
        if(newWeight > 0){
           weight = newWeight;
        }
    }

    /*sets the height to a new value
    * does not allow values less than or equal to 0*/
    public void setHeight(float newHeight) {
        if(newHeight > 0) {
            height = newHeight;
        }
    }

    //calculates the BMI
    public float calculateBMI() {
        float squareHeight = (float) Math.pow(height, 2);
        return (weight/squareHeight) * 703;
    }

    //determines the status of your health based on the bmi
    public String bmiStatus(float bmi) {
        if(bmi < 18.5){
            return "Underweight";
        }
        else if(bmi < 25.0){
            return "Normal";
        }
        else if(bmi < 30.0){
            return "Overweight";
        }
        else{
            return "Obese";
        }
    }
}
