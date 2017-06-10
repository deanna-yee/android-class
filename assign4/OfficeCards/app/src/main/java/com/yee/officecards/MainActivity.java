package com.yee.officecards;

/*
* Deanna Yee
* deannayee@my.smccd.edu
* CIS 135 OL
* File Name: MainActivity.java
* File Description: contains the class definition for Main Activity
* Assignment #: 4
* Date: 3/27/17
*/

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //changes the color of the collapsing tool bar
        CollapsingToolbarLayout collapsingToolbarLayout =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setContentScrimColor(Color.RED);

        //gets recycler view
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        //set the layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //set the adapter
        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
    }
}
