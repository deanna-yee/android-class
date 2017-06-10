package com.yee.employees;

/*
* Deanna Yee
* CIS 135 OL
* File Name: MainActivity.java
* File Description: contains the class definition for the main activity
* Assignment #: 7
* Date: 5/3/17
*/

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private CursorAdapter cursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cursorAdapter = new EmployeesCursorAdapter(this, null, 0);

        ListView list = (ListView) findViewById(android.R.id.list);
        list.setAdapter(cursorAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getLoaderManager().initLoader(0, null, this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater li = LayoutInflater.from(MainActivity.this);
                View getEmpIdView = li.inflate(R.layout.dialog_get_name_position, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                // set dialog_get_name_position.xml to alertdialog builder
                alertDialogBuilder.setView(getEmpIdView);

                //get name and position input from the edit text
                final EditText nameInput = (EditText) getEmpIdView.findViewById(R.id.editTextDialogNameInput);
                final EditText positionInput = (EditText) getEmpIdView.findViewById(R.id.editTextDialogPositionInput);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                // edit text
                                insertEmployee(nameInput.getText().toString(), positionInput.getText().toString());
                                restartLoader();

                            }
                        }).create()
                        .show();

            }
        });
    }

    //insert employee's name, position, and image to the database
    private void insertEmployee(String employeeName,String employeePosition) {
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.EMPLOYEE_NAME, employeeName);
        values.put(DBOpenHelper.EMPLOYEE_POSITION, employeePosition);

        //check to see if there is a profile image and sets the correct image
        String[] employeeNameArray = employeeName.toLowerCase().split(" ");
        String firstName = employeeNameArray[0];
        String lastName = employeeNameArray[1];
        String imageName = firstName + "_" + lastName;
        int resourceId = getResources().getIdentifier(imageName, "drawable",
                getApplicationContext().getPackageName());

        if(resourceId != 0) {
           try {
               Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resourceId);
               ByteArrayOutputStream stream = new ByteArrayOutputStream(16 * 1024);
               bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
               byte[] image = stream.toByteArray();
               stream.close();
               values.put(DBOpenHelper.EMPLOYEE_IMAGE, image);
           } catch (IOException e) {
               e.printStackTrace();
           }
       }else {
           try {
               Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic);
               ByteArrayOutputStream stream = new ByteArrayOutputStream(16 * 1024);
               bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
               byte[] image = stream.toByteArray();
               stream.close();
               values.put(DBOpenHelper.EMPLOYEE_IMAGE, image);
           } catch (IOException e) {
               e.printStackTrace();

           }
       }

        //display a toast when employee is created
        Uri employeeUri  = getContentResolver().insert(EmployeesProvider.CONTENT_URI, values);
        Toast.makeText(this,"Created Employee " + employeeName, Toast.LENGTH_LONG).show();
    }

    //deletes all the employees in the database
    private void deleteAllEmployees() {

        getContentResolver().delete(EmployeesProvider.CONTENT_URI, null, null);
        restartLoader();
        Toast.makeText(this,"All Employees Deleted",Toast.LENGTH_LONG).show();
    }

    //creates the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //if the option is selected from the menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.deleteAllEmployees:
                deleteAllEmployees();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //restart loader to 0 items
    private void restartLoader() {
        getLoaderManager().restartLoader(0, null, this);
    }

    //creates new loader cursor
    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this, EmployeesProvider.CONTENT_URI, null, null, null, null);
    }

    //swap new cursor, and return old cursor
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        cursorAdapter.swapCursor(cursor);
    }

    //swap no cursor, and return old cursor
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cursorAdapter.swapCursor(null);
    }
}
