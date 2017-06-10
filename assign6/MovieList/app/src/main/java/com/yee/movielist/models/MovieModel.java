package com.yee.movielist.models;

/*
* Deanna Yee
* CIS 135 OL
* File Name: MovieModel.java
* File Description: contains the class definition for the Movie Model
*                  stores the information for the title, release year, plot, rating and
*                  path to image for each movie
* Assignment #: 6
* Date: 4/23/17
*/

import com.google.gson.annotations.SerializedName;
import com.yee.movielist.R;

/**
 * Created by hisham on 9/6/2015.
 */
public class MovieModel {

    //title
    @SerializedName("title")
    private String movie;

    //release year
    @SerializedName("release_date")
    private String year;

    //rating
    @SerializedName("vote_average")
    private float rating;

    //image of poster
    @SerializedName("poster_path")
    private String image;

    //plot
    @SerializedName("overview")
    private String plot;

    //return title
    public String getMovie() {
        return movie;
    }

    //set title
    public void setMovie(String movie) {
        this.movie = movie;
    }

    //return release year
    public String getYear() {
        return year;
    }

    //set release year
    public void setYear(String year) {
        if((year == null) || year == ""){
            this.year = "NA";
        }else{
            String[] splitDate = year.split("-");
            this.year = splitDate[0];
        }
    }

    //return rating
    public float getRating() {
        return rating;
    }

    //set rating
    public void setRating(float rating) {
        this.rating = rating;
    }

    //return image path
    public String getImage() {
        return image;
    }

    //set image path
    public void setImage(String image) {
        if((image == null) || (image == "")) {
            this.image = "drawable://" + R.drawable.default_image;
        }else {
            this.image = "https://image.tmdb.org/t/p/w185" + image;
        }
    }

    //return overview
    public String getPlot() {
        return plot;
    }

    //set overview
    public void setPlot(String plot) {
        this.plot = plot;
    }
}
