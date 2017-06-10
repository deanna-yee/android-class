package com.yee.officecards;

/*
* Deanna Yee
* deannayee@my.smccd.edu
* CIS 135 OL
* File Name: Employee.java
* File Description: contains the class definition for Employee and stores the employee's name,
*                   position, and picture
* Assignment #: 4
* Date: 3/27/17
*/

public class Employee {
    private String name;
    private String position;
    private int image;

    //constructor for employee
    public Employee(String name, String position, int image){
        this.name = name;
        this.position = position;
        this.image = image;
    }

    //gets the name of the employee
    public String getName(){
        return name;
    }

    //gets the position of the employee
    public String getPosition(){
        return position;
    }

    //gets the image name of the employee
    public int getImage(){
        return image;
    }

    //sets the name of the employee
    public void setName(String name){
        this.name = name;
    }

    //sets the position of the employee
    public void setPosition(String position){
        this.position = position;
    }

    //sets the image name of the employee
    public  void setImage(int image){
        this.image = image;
    }
}
