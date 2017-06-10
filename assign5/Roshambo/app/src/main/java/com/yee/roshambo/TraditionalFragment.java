package com.yee.roshambo;

/*
* Deanna Yee
* CIS 135 OL
* File Name: TraditionalFragment.java
* File Description: contains the class defintion for the TraditionalFragment and determines
* if you win, lose or tie based on what button you press, only does rock, paper and scissor
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


public class TraditionalFragment extends Fragment implements View.OnClickListener {

    //get buttons and text view
    private Button rockButton;
    private Button paperButton;
    private Button scissorsButton;
    private TextView infoTextView;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_traditional, container, false);

        //get buttons and text view
        rockButton = (Button) view.findViewById(R.id.rockButton);
        paperButton = (Button) view.findViewById(R.id.paperButton);
        scissorsButton = (Button) view.findViewById(R.id.scissorsButton);
        infoTextView = (TextView) view.findViewById(R.id.infoTextView);

        //sets a listener to buttons
        rockButton.setOnClickListener(this);
        paperButton.setOnClickListener(this);
        scissorsButton.setOnClickListener(this);

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
        }
        infoTextView.setText(result);
    }

    //Determines whether the player wins, loses, or ties
    public String play(String userChoice){
        String result = "";

        Random computerChoiceGenerator = new Random();
        int computerChoice = computerChoiceGenerator.nextInt(3);
        //0: rock, 1: paper 2: scissors

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
