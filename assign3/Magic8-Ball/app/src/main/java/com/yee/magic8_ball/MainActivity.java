/*
* Deanna Yee
* deannayee@my.smccd.edu
* CIS 135 OL
* File Name: MainActivity.java
* File Description: contains the class defintion for the main activity
* Assignment #: 3
* Date: 2/23/17
*/
package com.yee.magic8_ball;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    //Array of all the possible answers
    String[] answers = {"It is certain",
                        "It is decidedly so",
                        "Without a doubt",
                        "Yes - definitely",
                        "You may rely on it",
                        "As I see it, yes",
                        "Most likely",
                        "Outlook good",
                        "Signs point to yes",
                        "Yes",
                        "Reply hazy, try again",
                        "Ask again later",
                        "Better not tell you now",
                        "Cannot predict now",
                        "Concentrate and ask again",
                        "Don't count on it",
                        "My reply is no",
                        "My sources say no",
                        "Outlook not so good",
                        "Very doubtful"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the ask button
        Button askButton = (Button)findViewById(R.id.askButton);

        //When button is clicked get a random answer and set it to text view
        askButton.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        //Get a Random answer from the array
                        Random random = new Random();
                        int randomIndex = random.nextInt(answers.length);
                        String answer = answers[randomIndex];

                        //Set the answer to the text view
                        TextView statusText = (TextView) findViewById(R.id.statusText);
                        statusText.setText(answer);
                    }
                }
        );
    }
}
