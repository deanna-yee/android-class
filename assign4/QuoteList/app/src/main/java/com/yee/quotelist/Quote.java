/*
* Deanna Yee
* deannayee@my.smccd.edu
* CIS 135 OL
* File Name: Quote.java
* File Description: contains the class definition for Quote and stores the quote and author
* Assignment #: 4
* Date: 3/25/17
*/
package com.yee.quotelist;

public class Quote {
    //stores the quote and the author
    private String quote;
    private String author;

    //creates a Quote with its quote and author
    public Quote(String newQuote, String newAuthor){
        this.quote = newQuote;
        this.author = newAuthor;
    }

    //get the quote
    public String getQuote(){
        return quote;
    }

    //get the author
    public String getAuthor(){
        return author;
    }

    //format string for printing
    @Override
    public String toString(){
        return quote + " -" + author;
    }
}
