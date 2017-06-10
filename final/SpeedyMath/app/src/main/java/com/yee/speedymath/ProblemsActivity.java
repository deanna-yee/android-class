package com.yee.speedymath;

/*
* Deanna Yee
* CIS 135 OL
* File Name: ProblemsActivity.java
* File Description: contains the class definition for the problem activity
* Assignment #: final
* Date: 5/18/17
*/

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

public class ProblemsActivity extends AppCompatActivity {

    private GamePlay gamePlay;
    private String symbol;
    private MathSolution mathSolution;
    private TextView top_number;
    private TextView bottom_number;
    private TextView symbol_view;
    private TextView score_view;
    private TextView timer_view;
    private EditText answer_editText;
    private CountDownTimer timer;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problems);

        //get the game play from the number activity
        gamePlay = (GamePlay) getIntent().getSerializableExtra("gamePlay");

        //create a math solution to check the correct answer
        mathSolution = new MathSolution();

        //get the text and edit views
        top_number = (TextView) findViewById(R.id.top_number);
        bottom_number = (TextView) findViewById(R.id.bottom_number);
        symbol_view = (TextView) findViewById(R.id.symbol);
        score_view = (TextView) findViewById(R.id.score);
        timer_view = (TextView) findViewById(R.id.timer);
        answer_editText = (EditText) findViewById(R.id.answer);

        //display information
        timer_view.setText("timer: " + gamePlay.getTime() / 1000 + "s");
        score_view.setText("score: " + gamePlay.getScore());
        gamePlay.setNumbers();
        displaySymbol();
        displayNumbers();

        //create a timer
        timer = new CountDownTimer(gamePlay.getTime(), 1000) {

            public void onTick(long millisUntilFinished) {
                timer_view.setText("time: " + millisUntilFinished / 1000 + "s");
            }

            public void onFinish() {
                timer_view.setText("time: 0s");

                //create game over dialog
                createGameOverDialog();
            }
        };

        //create start dialog
        createStartDialog();

        answer_editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                int yourAnswer = Integer.parseInt(answer_editText.getText().toString());
                if (mathSolution.checkAnswer(yourAnswer, gamePlay.getFirst_number(),
                        gamePlay.getSecond_number(), symbol)) {
                    gamePlay.setScore(gamePlay.getScore() + 1);
                    score_view.setText("Score: " + Integer.toString(gamePlay.getScore()));
                    gamePlay.setNumbers();
                    displaySymbol();
                    displayNumbers();
                    handled = true;
                }
                answer_editText.setText("");
                return handled;
            }

        });
    }

    //create intent and pass GamePlay to NumberActivity
    public void createIntent(){
        Intent intent = new Intent(ProblemsActivity.this, ProblemsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("gamePlay", gamePlay);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //create and show dialog to game over
    public void createGameOverDialog(){
        AlertDialog.Builder alertGameOverDialogBuilder = new AlertDialog.Builder(
                ProblemsActivity.this);
        alertGameOverDialogBuilder.setTitle("Game Over");
        alertGameOverDialogBuilder.setMessage("Score: " + gamePlay.getScore())
                .setCancelable(false)
                .setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gamePlay.setScore(0);
                        createIntent();
                    }
                })
                .setNegativeButton("Main Menu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ProblemsActivity.this,
                                MainMenuActivity.class);
                        startActivity(intent);
                    }
                });
        AlertDialog alertGameOverDialog = alertGameOverDialogBuilder.create();
        alertGameOverDialog.show();
    }

    //create and show dialog to start the game
    public void createStartDialog(){
        AlertDialog.Builder alertStartDialogBuilder = new AlertDialog.Builder(this);
        alertStartDialogBuilder.setTitle("Start Game");
        alertStartDialogBuilder.setMessage("Click Start Button to start the game")
                .setCancelable(false)
                .setPositiveButton("Start", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        timer.start();
                    }
                });
        AlertDialog alertStartDialog = alertStartDialogBuilder.create();
        alertStartDialog.show();
    }

    //determine and display math symbol
    public void setMathSymbol(String symbol){
        switch(symbol){
            case "add":
                symbol_view.setText("+");
                break;
            case "sub":
                symbol_view.setText("-");
                break;
            case "mult":
                symbol_view.setText("x");
                break;
        }
    }

    //display symbol
    public void displaySymbol(){
        if(gamePlay.getMathSymbol().equals("random")){
            symbol = gamePlay.determineSymbol();
        }else{
            symbol = gamePlay.getMathSymbol();
        }
        setMathSymbol(symbol);
    }

    //display numbers
    public void displayNumbers() {
        top_number.setText(Integer.toString(gamePlay.getFirst_number()));
        bottom_number.setText(Integer.toString(gamePlay.getSecond_number()));
    }
}
