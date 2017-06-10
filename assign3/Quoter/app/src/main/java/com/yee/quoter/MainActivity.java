/*
* Deanna Yee
* deannayee@my.smccd.edu
* CIS 135 OL
* File Name: MainActivity.java
* File Description: contains the class defintion for the main activity
* Assignment #: 3
* Date: 2/24/17
*/
package com.yee.quoter;

import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
    implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener
{
    //stores all the quotes
    private ArrayList<Quote> quotes;

    //stores the text views
    private TextView quoteView;
    private TextView authorView;

    //stores a gestureDetector
    private GestureDetectorCompat gDetector;

    //keeps track of the indexOf the array
    int quoteIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get text views
        quoteView = (TextView)findViewById(R.id.quoteView);
        authorView = (TextView)findViewById(R.id.authorView);

        //create an ArrayList to store the quotes
        quotes = new ArrayList<Quote>();

        //create all the quotes
        createQuotes();

        //start the index at 0
        quoteIndex = 0;

        //allow double tap events to the screen
        this.gDetector = new GestureDetectorCompat(this, this);
        gDetector.setOnDoubleTapListener(this);
    }

    //allows touch events to the screen
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    //when the screen is double tapped display the quote and author
    @Override
    public boolean onDoubleTap(MotionEvent event){
        //restart the quotes once it hits the end of the ArrayList
        if(quoteIndex == quotes.size()){
            quoteIndex = 0;
        }

        //gets a quote from the ArrayList
        Quote quote = quotes.get(quoteIndex);

        //displays the quote and author
        quoteView.setText(quote.getQuote());
        authorView.setText(quote.getAuthor());

        //increments the quoteIndex to the next quote
        quoteIndex++;
        return true;
    }

    //create all the quotes
    public void createQuotes(){
        //Quotes from https://www.brainyquote.com/
        //Friendship Quotes
        createAndStoreQuote("Friends show their love in times of trouble, not in happiness.",
                "Euripides");
        createAndStoreQuote("A friend is someone who gives you total freedom to be yourself.",
                "Jim Morrison");
        createAndStoreQuote("A real friend is one who walks in when the rest of " +
                        "the world walks out.",
                "Walter Winchell");

        //Happiness Quotes
        createAndStoreQuote("There is only one happiness in this life, to love and be loved.",
                "George Sand");
        createAndStoreQuote("Success is not the key to happiness. " +
                        "Happiness is the key to success." +
                        " If you love what you are doing, you will be successful.",
                "Albert Schweitzer");
        createAndStoreQuote("Every day is a new day, and you'll never be able to " +
                        "find happiness if you don't move on.",
                "Carrie Underwood");

        //Inspirational Quotes
        createAndStoreQuote("The best and most beautiful things in the world cannot be seen " +
                        "or even touched - they must be felt with the heart.",
                "Helen Keller");
        createAndStoreQuote("No act of kindness, no matter how small, is ever wasted.",
                "Aesop");
        createAndStoreQuote("A hero is someone who has given his or her life to " +
                        "something bigger than oneself.",
                "Joseph Campbell");

        //Motivational Quotes
        createAndStoreQuote("Only I can change my life. No one can do it for me.",
                "Carol Burnett");
        createAndStoreQuote("Optimism is the faith that leads to achievement. " +
                        "Nothing can be done without hope and confidence.",
                "Helen Keller");
        createAndStoreQuote("Believe in yourself! Have faith in your abilities! " +
                        "Without a humble but reasonable confidence in your own " +
                        "powers you cannot be successful or happy.",
                "Normon Vincent Peale");

    }

    //creates a new quote and stores them in the array
    public void createAndStoreQuote(String quote, String author){
        Quote newQuote = new Quote(quote, author);
        quotes.add(newQuote);
    }

    //does nothing when event happens
    @Override
    public boolean onDown(MotionEvent event) {
        return true;
    }

    //does nothing when event happens
    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
        return true;
    }

    //does nothing when event happens
    @Override
    public void onLongPress(MotionEvent event) {
    }

    //does nothing when event happens
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return true;
    }

    //does nothing when event happens
    @Override
    public void onShowPress(MotionEvent event) {

    }

    //does nothing when event happens
    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        return true;
    }

    //does nothing when event happens
    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        return true;
    }

    //does nothing when event happens
    @Override
    public boolean onSingleTapConfirmed(MotionEvent event){
        return true;
    }
}
