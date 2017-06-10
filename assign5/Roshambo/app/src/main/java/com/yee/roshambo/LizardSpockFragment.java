package com.yee.roshambo;

/*
* Deanna Yee
* CIS 135 OL
* File Name: LizardSpockFragment.java
* File Description: contains the class defintion for the LizardSpockFragment and determines
* if you win, lose or tie based on what button you press, does rock, paper, scissor, lizard, spock
* Assignment #: 5
* Date: 3/31/17
*/

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class LizardSpockFragment extends Fragment implements View.OnClickListener {

    //Instances for TextView, Images and Buttons
    private TextView infoTextView;
    private Button rockButton;
    private Button paperButton;
    private Button scissorsButton;
    private Button lizardButton;
    private Button spockButton;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lizard_spock, container, false);

        //get TextView
        infoTextView = (TextView) view.findViewById(R.id.infoTextView);

        //get buttons for rock, lizard, paper, spock and scissors
        rockButton = (Button) view.findViewById(R.id.rockButton);
        paperButton = (Button) view.findViewById(R.id.paperButton);
        scissorsButton = (Button) view.findViewById(R.id.scissorsButton);
        lizardButton = (Button) view.findViewById(R.id.lizardButton);
        spockButton = (Button) view.findViewById(R.id.spockButton);

        //set click listener to buttons for rock, lizard, paper, spock and scissors
        rockButton.setOnClickListener(this);
        paperButton.setOnClickListener(this);
        scissorsButton.setOnClickListener(this);
        lizardButton.setOnClickListener(this);
        spockButton.setOnClickListener(this);

        return view;
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
            case R.id.lizardButton:
                result = play("lizard");
                break;
            case R.id.spockButton:
                result = play("spock");
                break;
        }
        infoTextView.setText(result);
    }

    //Determines whether the player wins, loses, or ties
    public String play(String userChoice){
        String result = "";

        //0: rock, 1: paper 2: scissors 3: lizard 4: spock
        //generate computer's choice
        Random computerChoiceGenerator = new Random();
        int computerChoice = computerChoiceGenerator.nextInt(5);

        //determine which button has been pressed and determine whether player wins, loses, or ties
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
                    case 3: //lizard
                        result = "Rock beats lizard. You win!";
                        break;
                    case 4: //spock
                        result = "Spock beats rock. You lose.";
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
                    case 3: //lizard
                        result = "Lizard beats paper. You lose.";
                        break;
                    case 4: //spock
                        result = "Paper beats spock. You win!";
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
                    case 3: //lizard
                        result = "Scissors beats lizard. You win!";
                        break;
                    case 4: //spock
                        result = "Spock beats scissors. You lose.";
                        break;
                }
                break;
            case "lizard":
                switch (computerChoice){
                    case 0: //rock
                        result = "Rock beats lizard. You lose.";
                        break;
                    case 1: //paper
                        result = "Lizard beats paper. You win!";
                        break;
                    case 2: //scissors
                        result = "Scissors beats lizard. You lose.";
                        break;
                    case 3: //lizard
                        result = "Lizard vs lizard. It's a tie.";
                        break;
                    case 4: //spock
                        result = "Lizard beats spock. You win!";
                        break;
                }
                break;
            case "spock":
                switch (computerChoice){
                    case 0: //rock
                        result = "Spock beats rock. You win!";
                        break;
                    case 1: //paper
                        result = "Paper beats spock. You lose.";
                        break;
                    case 2: //scissors
                        result = "Spock beats scissors. You win!";
                        break;
                    case 3: //lizard
                        result = "Lizard beats spock. You lose.";
                        break;
                    case 4: //spock
                        result = "Spock vs spock. It's a tie.";
                        break;
                }
                break;
        }
        return result;
    }


}
