/*
* Deanna Yee
* CIS 135 OL
* File Name: MainActivity.java
* File Description: contains the class defintion for the main activity
* Assignment #: 2
* Date: 2/13/17
*/
package com.yee.roshambo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Instances for TextView and Buttons
    private TextView infoTextView;
    private Button rockButton;
    private Button paperButton;
    private Button scissorsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get TextView
        infoTextView = (TextView) findViewById(R.id.infoTextView);

        //get buttons for rock, paper, and scissors
        rockButton = (Button) findViewById(R.id.rockButton);
        paperButton = (Button) findViewById(R.id.paperButton);
        scissorsButton = (Button) findViewById(R.id.scissorsButton);

        //set click listener to buttons for rock, paper, and scissors
        rockButton.setOnClickListener(this);
        paperButton.setOnClickListener(this);
        scissorsButton.setOnClickListener(this);
    }

    //Determines which button was clicked and displays the results
    public void onClick(View v){
        String result = "";
        switch (v.getId()){
            case R.id.rockButton:
                result = play("rock");
                break;
            case R.id.paperButton:
                result = play("paper");
                break;
            case R.id.scissorsButton:
                result = play("scissors");
                break;
        }
        infoTextView.setText(result);
    }

    //Determines whether the player wins, loses, or ties
    public String play(String userChoice){
        String result = "";

        Random computerChoiceGenerator = new Random();
        //0: rock, 1: paper 2: scissors
        int computerChoice = computerChoiceGenerator.nextInt(3);

        switch (userChoice){
            case "rock":
                switch (computerChoice){
                    case 0: //rock
                        result = "Rock vs. rock. It's a tie.";
                        break;
                    case 1: //paper
                        result = "Paper beats rock. You lose.";
                        break;
                    case 2: //scissors
                        result = "Rock beats scissors. You win!";
                        break;
                }
                break;
            case "paper":
                switch (computerChoice){
                    case 0: //rock
                        result = "Paper beats rock. You win!";
                        break;
                    case 1: //paper
                        result = "Paper vs. paper. It's a tie.";
                        break;
                    case 2: //scissors
                        result = "Scissors beats paper. You lose.";
                        break;
                }
                break;
            case "scissors":
                switch (computerChoice){
                    case 0: //rock
                        result = "Rock beats scissors. You lose.";
                        break;
                    case 1: //paper
                        result = "Scissors beats paper. You win!";
                        break;
                    case 2: //scissors
                        result = "Scissors vs. scissors. It's a tie.";
                        break;
                }
                break;
        }
        return result;
    }
}
