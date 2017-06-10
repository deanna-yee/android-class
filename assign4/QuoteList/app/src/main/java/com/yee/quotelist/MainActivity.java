package com.yee.quotelist;

/*
* Deanna Yee
* deannayee@my.smccd.edu
* CIS 135 OL
* File Name: MainActivity.java
* File Description: contains the class definition for Main Activity
* Assignment #: 4
* Date: 3/25/17
*/

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //get the index of quote that will be added
    int quoteIndex = 2;

    //stores all the quotes
    ArrayList<Quote> quotes = new ArrayList<Quote>();
    //stores the quotes that are shown
    ArrayList<Quote> displayedQuotes = new ArrayList<Quote>();
    ArrayAdapter<Quote> quoteAdapter;
    private ListView quoteListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set toolbar to the action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //get the list view for quotes
        quoteListView = (ListView) findViewById(R.id.listView);

        //set an array adapter to the list view
        quoteAdapter = new ArrayAdapter<Quote>(this,
                android.R.layout.simple_list_item_1, displayedQuotes);
        quoteListView.setAdapter(quoteAdapter);

        //get the floating action button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        //set a click listener for floating action button
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addListItem();
                Snackbar.make(view, "Quote added to list", Snackbar.LENGTH_LONG)
                        .setAction("Undo", undoOnClickListener).show();
            }
        });

        //adds all the quotes to quotes array list
        createQuotes();

        //adds the first to quotes and displays them
        addNewQuote(quotes.get(0).getQuote(), quotes.get(0).getAuthor(), displayedQuotes);
        addNewQuote(quotes.get(1).getQuote(), quotes.get(1).getAuthor(), displayedQuotes);
        quoteAdapter.notifyDataSetChanged();
    }

    //remove quote and decrease index by 1
    View.OnClickListener undoOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            displayedQuotes.remove(displayedQuotes.size() - 1);
            quoteAdapter.notifyDataSetChanged();
            Snackbar.make(view, "Quote removed", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            quoteIndex--;
        }
    };

    private void addListItem(){
        //resets to the first quote once you reach the end of the array
        if(quoteIndex == quotes.size()){
            quoteIndex = 0;
        }
        //adds the quote and displays it
        addNewQuote(quotes.get(quoteIndex).getQuote(), quotes.get(quoteIndex).getAuthor(),
                displayedQuotes);
        quoteAdapter.notifyDataSetChanged();

        quoteIndex++;
    }

    //creates a new quote and stores them in the array
    public void addNewQuote(String quote, String author, ArrayList<Quote> quoteArray){
        Quote newQuote = new Quote(quote, author);
        quoteArray.add(newQuote);
    }

    //create all the quotes
    public void createQuotes(){
        //Quotes from https://www.brainyquote.com/
        //Friendship Quotes
        addNewQuote("Friends show their love in times of trouble, not in happiness.",
                "Euripides", quotes);
        addNewQuote("A friend is someone who gives you total freedom to be yourself.",
                "Jim Morrison", quotes);
        addNewQuote("A real friend is one who walks in when the rest of " +
                        "the world walks out.",
                "Walter Winchell", quotes);

        //Happiness Quotes
        addNewQuote("There is only one happiness in this life, to love and be loved.",
                "George Sand", quotes);
        addNewQuote("Success is not the key to happiness. " +
                        "Happiness is the key to success." +
                        " If you love what you are doing, you will be successful.",
                "Albert Schweitzer", quotes);
        addNewQuote("Every day is a new day, and you'll never be able to " +
                        "find happiness if you don't move on.",
                "Carrie Underwood",quotes);

        //Inspirational Quotes
        addNewQuote("The best and most beautiful things in the world cannot be seen " +
                        "or even touched - they must be felt with the heart.",
                "Helen Keller", quotes);
        addNewQuote("No act of kindness, no matter how small, is ever wasted.",
                "Aesop", quotes);
        addNewQuote("A hero is someone who has given his or her life to " +
                        "something bigger than oneself.",
                "Joseph Campbell", quotes);

        //Motivational Quotes
        addNewQuote("Only I can change my life. No one can do it for me.",
                "Carol Burnett", quotes);
        addNewQuote("Optimism is the faith that leads to achievement. " +
                        "Nothing can be done without hope and confidence.",
                "Helen Keller", quotes);
        addNewQuote("Believe in yourself! Have faith in your abilities! " +
                        "Without a humble but reasonable confidence in your own " +
                        "powers you cannot be successful or happy.",
                "Normon Vincent Peale",quotes);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_quote_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
