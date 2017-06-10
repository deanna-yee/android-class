package com.yee.speedymath;

/*
* Deanna Yee
* CIS 135 OL
* File Name: GamePlay.java
* File Description: contains the class definition for the info of the game
* Assignment #: final
* Date: 5/18/17
*/

import java.io.Serializable;
import java.util.Random;

public class GamePlay implements Serializable{
    private String mathSymbol;
    private int number;
    private int time;
    private int score;
    private int first_number;
    private int second_number;
    private Random random;

    GamePlay(){
        mathSymbol = "";
        number = 0;
        time = 60000;
        score = 0;
        first_number = 0;
        second_number = 0;
        random = new Random();
    }

    //set the math symbol
    public void setMathSymbol(String mathSymbol){
        this.mathSymbol = mathSymbol;
    }

    //sets the number
    public void setNumber(int number){
        this.number = number;
    }

    //set the time
    public void setTime(int time){
        this.time = time;
    }

    //set the score
    public void setScore(int score){
        this.score = score;
    }

    //set first number
    public void setFirst_number(int first_number){
        this.first_number = first_number;
    }

    //set second number
    public void setSecond_number(int second_number){
        this.second_number = second_number;
    }

    //get the number
    public int getNumber() {
        return number;
    }

    //get the score
    public int getScore() {
        return score;
    }

    //get the time
    public int getTime() {
        return time;
    }

    //get the math symbol
    public String getMathSymbol() {
        return mathSymbol;
    }

    //get first number
    public int getFirst_number(){
        return first_number;
    }

    //get second number
    public int getSecond_number(){
        return second_number;
    }

    //determine number shown
    public int determineNumber(){
        return random.nextInt(13);
    }

    //determine symbol when random is chosen
    public String determineSymbol(){
        int randomSymbol = random.nextInt(3);
        switch (randomSymbol){
            case 0: //add
                return "add";
            case 1: //sub
                return "sub";
            case 2: //mult
                return "mult";
            default:
                return "";
        }
    }

    //determine which position the selected number is at
    public int positionOfSelectedNumber(){
        return random.nextInt(2);
    }

    //display numbers
    public void setNumbers(){
        if(number == 0){
            first_number = determineNumber();
            second_number = determineNumber();
        }else{
            if(positionOfSelectedNumber() == 0){
               first_number = number;
                second_number = determineNumber();
            }else {
                first_number = determineNumber();
                second_number = number;
            }
        }
    }
}

