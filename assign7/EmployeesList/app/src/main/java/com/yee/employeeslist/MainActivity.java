package com.yee.employeeslist;

/*
* Deanna Yee
* CIS 135 OL
* File Name: MainActivity.java
* File Description: contains the class definition for the main activity
* Assignment #: 7
* Date: 5/3/17
*/

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private CursorAdapter cursorAdapter;

    //Constants to access employee database
    private static final String AUTHORITY = "com.yee.employees";
    private static final String BASE_PATH = "employees";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH );

    // Constant to identify the requested operation
    private static final int CONTACTS = 1;
    private static final int CONTACT_ID = 2;

    //create uriMatcher and add uris
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(AUTHORITY,BASE_PATH, CONTACTS);
        uriMatcher.addURI(AUTHORITY,BASE_PATH + "/#",CONTACT_ID);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //sets cursor adatper to list view
        cursorAdapter = new EmployeesCursorAdapter(this,null,0);
        ListView list = (ListView) findViewById(android.R.id.list);
        list.setAdapter(cursorAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initialize loader
        getLoaderManager().initLoader(0, null, this);

        //restarts loader when floating action button is clicked
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartLoader();

            }
        });
    }

    //restarts the loader
    private void restartLoader() {
        getLoaderManager().restartLoader(0,null,this);
    }

    //returns a new cursor loader
    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this, CONTENT_URI, null, null, null, null);
    }

    //swaps in a new cursor, returning the new cursor
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        cursorAdapter.swapCursor(cursor);

    }

    //swap  in no cursor, returning the old cursor
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cursorAdapter.swapCursor(null);
    }
}
