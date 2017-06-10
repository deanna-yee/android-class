package com.yee.speedymath;

/*
* Deanna Yee
* CIS 135 OL
* File Name: MathSolution.java
* File Description: contains the class definition to check the answer
* Assignment #: final
* Date: 5/18/17
*/

public class MathSolution {

    //solves the problem given the 2 numbers and the symbol
    public int solveProblem(int first, int second, String symbol){
        int answer = 0;

        //solve the problem
        if(symbol.equals("add")){
            answer = first + second;
        }else if(symbol.equals("sub")){
            answer = first - second;
        }else if(symbol.equals("mult")){
            answer = first * second;
        }
        return answer;
    }

    //checks if your answer is the correct answer
    public boolean checkAnswer(int yourAnswer, int first, int second, String symbol){
        int correctAnswer = solveProblem(first, second, symbol);
        return correctAnswer == yourAnswer;
    }
}
