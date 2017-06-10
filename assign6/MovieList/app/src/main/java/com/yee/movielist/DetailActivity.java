package com.yee.movielist;

/*
* Deanna Yee
* CIS 135 OL
* File Name: DetailActivity.java
* File Description: contains the class definition for the detail activity
*                  displays the poster image, rating, title, release year, and plot
*                  for the movie that has been clicked
* Assignment #: 6
* Date: 4/23/17
*/

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.yee.movielist.models.MovieModel;


public class DetailActivity extends AppCompatActivity {

    private ImageView ivMovieIcon;
    private TextView tvMovie;
    private TextView tvYear;
    private TextView tvPlot;
    private RatingBar rbMovieRating;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Showing and Enabling clicks on the Home/Up button
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // setting up text views and stuff
        setUpUIViews();

        // recovering data from MainActivity, sent via intent
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            // getting the model from MainActivity send via extras
            String json = bundle.getString("movieModel");
            MovieModel movieModel = new Gson().fromJson(json, MovieModel.class);

            // Then later, when you want to display image
            ImageLoader.getInstance().displayImage(movieModel.getImage(), ivMovieIcon, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    progressBar.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {
                    progressBar.setVisibility(View.GONE);
                }
            });

            //set title, year and plot
            tvMovie.setText(movieModel.getMovie());
            tvYear.setText("Year: " + movieModel.getYear());
            tvPlot.setText(movieModel.getPlot());

            // rating bar
            rbMovieRating.setRating(movieModel.getRating() / 2);



        }

    }

    //gets all the uiviews
    private void setUpUIViews() {
        ivMovieIcon = (ImageView)findViewById(R.id.ivIcon);
        tvMovie = (TextView)findViewById(R.id.tvMovie);
        tvYear = (TextView)findViewById(R.id.tvYear);
        tvPlot = (TextView)findViewById(R.id.tvPlot);
        rbMovieRating = (RatingBar)findViewById(R.id.rbMovie);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
