package com.yee.longevitycalculator;

/*
* Deanna Yee
* CIS 135 OL
* File Name: MainActivity.java
* File Description: contains the class definition for Lifetime and calculates the life expectancy
* Assignment #: 5
* Date: 4/3/17
*/

import java.io.Serializable;

public class Lifetime implements Serializable {

    //stores the information from the user interface
    private int age;
    private int weight;
    private int heightFeet;
    private int heightInches;
    private int gender;
    private int smoking;
    private int lifestyle;

    //constant numbers
    private final static double averageLifespan = 79.13;
    private final static long serialVersionUID = 1L;

    //constructor of Lifetime with no parameters
    public Lifetime(){
        age = 0;
        weight = 0;
        heightFeet = 0;
        heightInches = 0;
        gender = 0;
        smoking = 0;
        lifestyle = 0;
    }

    //constructor of Lifetime with all parameters
    public Lifetime(int gender, int age, int weight, int heightFeet, int heightInches,
                    int smoking, int lifestyle){
        if(age > 0) {
            this.age = age;
        }
        if(weight > 0) {
            this.weight = weight;
        }
        if(heightFeet > 0) {
            this.heightFeet = heightFeet;
        }
        if((heightInches > 0) && (heightInches < 12)) {
            this.heightInches = heightInches;
        }
        this.gender = gender;
        this.smoking = smoking;
        this.lifestyle = lifestyle;
    }

    //returns the age of the person
    public int getAge(){
        return age;
    }

    //returns the weight of the person
    public int getWeight(){
        return weight;
    }

    //returns the height in feet
    public int getHeightFeet(){
        return  heightFeet;
    }

    //returns the height in inches
    public int getHeightInches(){
        return heightInches;
    }

    //returns the gender of the person
    //gender: 0 = female, 1 = male
    public int getGender(){
        return gender;
    }

    //returns if they are smokers or not
    //smoking: 0 = never, 1 = quit 2+ years ago, 2 = smoker
    public int getSmoking(){
        return smoking;
    }

    //returns the lifestyle
    //lifestyle: 0 = couch potato, 1 = some exercise/good diet, 2 = super healthy
    public int getLifestyle(){
        return lifestyle;
    }

    //sets the age
    public void setAge(int age){
        if(age > 0){
            this.age = age;
        }
    }

    //sets the weight
    public void setWeight(int weight){
        if(weight > 0){
            this.weight = weight;
        }
    }

    //sets the height in feet
    public void setHeightFeet(int heightFeet){
        if(heightFeet > 0){
            this.heightFeet = heightFeet;
        }
    }

    //sets the height in inches
    public void setHeightInches(int heightInches){
        if((heightInches > 0) && (heightInches < 12)){
            this.heightInches = heightInches;
        }
    }

    //set the gender
    public void setGender(int gender){
        this.gender = gender;
    }

    //sets whether they smoke or not
    public void setSmoking(int smoking){
        this.smoking = smoking;
    }

    //sets the person's lifestyle
    public void setLifestyle(int lifestyle){
        this.lifestyle = lifestyle;
    }

    //calculates and returns the bmi
    public double getBMI(){
        int height = (heightFeet * 12) + heightInches;
        int heightSquared = height * height;
        double bmi = ((double) weight/(double) heightSquared) * 703;
        return bmi;
    }

    //calculates and returns the estimated life span of a person
    public double calculateLifeExpectancy(){
        double subOrAddValues = 0;
        double estimatedLifespan = averageLifespan;
        //if bmi is greater than 25.0 subtract from averageLifespan
        if(getBMI() > 25.0){
            subOrAddValues = (getBMI() - 25.0) * .18;
            estimatedLifespan -= subOrAddValues;
        }
        //if the person is a smoker then subtract 9 years from esimatedLifespan
        if(smoking == 2){
            estimatedLifespan -= 9;
        }
        //if the person was a former smoker then subtract 4.5 from estimatedLifespan
        if(smoking == 1){
            estimatedLifespan -= 4.5;
        }
        //if the person is a male then subtract 5 years from estimatedLifespan
        if(gender == R.id.male){
            estimatedLifespan -= 5;
        }
        //if the lifestyle is moderately active add 7.1 years to estimatedLifespan
        if(lifestyle == 1){
            estimatedLifespan += 7.1;
        }
        //if the lifestyle is very healthy add 14.2 years to estimatedLifespan
        else if(lifestyle == 2){
            estimatedLifespan += 14.2;
        }
        return estimatedLifespan;
    }

    //gets messages that will be displayed
    public String getMessages(){
        String message = "";
        //if person is not a smoker or is moderately active or very active
        if((smoking != 2) || (lifestyle == 1) || (lifestyle == 2)){
            message = "Congratulations on making good lifestyle choices.\n\n";
        }
        //if person is a smoker
        if(smoking == 2) {
            message += "If you quit smoking now you will live a lot longer.\n\n";
        }
        //if the user is a couch potato
        if(lifestyle == 0) {
            message += "Exercise can help you live a longer, healthier life.";
        }
        return message;
    }
}
