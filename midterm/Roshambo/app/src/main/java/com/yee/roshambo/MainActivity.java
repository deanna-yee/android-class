/*
* Deanna Yee
* CIS 135 OL
* File Name: MainActivity.java
* File Description: contains the class defintion for the main activity
* Assignment #: midterm
* Date: 3/13/17
*/
package com.yee.roshambo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Instances for TextView, Images and Buttons
    private TextView infoTextView;
    private ImageView imageView;
    private Button rockButton;
    private Button paperButton;
    private Button scissorsButton;
    private Button lizardButton;
    private Button spockButton;
    private boolean traditional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //sets traditional mode as the start up
        traditional = true;

        //sets the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //get TextView
        infoTextView = (TextView) findViewById(R.id.infoTextView);

        //get ImageView
        imageView = (ImageView) findViewById(R.id.rpsImageView);

        //get buttons for rock, lizard, paper, spock and scissors
        rockButton = (Button) findViewById(R.id.rockButton);
        paperButton = (Button) findViewById(R.id.paperButton);
        scissorsButton = (Button) findViewById(R.id.scissorsButton);
        lizardButton = (Button) findViewById(R.id.lizardButton);
        spockButton = (Button) findViewById(R.id.spockButton);

        //set click listener to buttons for rock, lizard, paper, spock and scissors
        rockButton.setOnClickListener(this);
        paperButton.setOnClickListener(this);
        scissorsButton.setOnClickListener(this);
        lizardButton.setOnClickListener(this);
        spockButton.setOnClickListener(this);

        //set to traditional mode
        updateView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_roshambo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Determines which menu option is checked
        switch(item.getItemId()){
            case R.id.menu_traditional:
                if(!item.isChecked()){
                    item.setChecked(true);
                    traditional = true;
                }
                updateView();
                return true;
            case R.id.menu_lizard_spock:
                if(!item.isChecked()){
                    item.setChecked(true);
                    traditional = false;
                }
                updateView();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void updateView(){
        infoTextView.setText("Play");
        if(traditional){
            //sets the lizard and spock button as hidden
            lizardButton.setVisibility(View.INVISIBLE);
            spockButton.setVisibility(View.INVISIBLE);

            //sets the image to traditional image
            imageView.setImageResource(R.drawable.rps);
        } else {
            //sets the lizard and spock button as hidden
            lizardButton.setVisibility(View.VISIBLE);
            spockButton.setVisibility(View.VISIBLE);

            //sets the image to lizard-spock image
            imageView.setImageResource(R.drawable.rpsls);
        }
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

        Random computerChoiceGenerator = new Random();
        int computerChoice = 0;
        //0: rock, 1: paper 2: scissors 3: lizard 4: spock
        if(traditional) {
            //if traditional only select from rock, paper, or scissors
            computerChoice = computerChoiceGenerator.nextInt(3);
        } else {
            //if lizard-spock select from rock, paper, scissors, lizard, or spock
            computerChoice = computerChoiceGenerator.nextInt(5);
        }

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
