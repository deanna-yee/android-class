package com.yee.employeeslist;

/*
* Deanna Yee
* CIS 135 OL
* File Name: MainActivity.java
* File Description: sets the employee name, employee position, and
*   image to employee list item from the database
* Assignment #: 7
* Date: 5/3/17
*/

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;

public class EmployeesCursorAdapter extends CursorAdapter{

    //Constants to database column names
    public static final String EMPLOYEE_NAME = "employeeName";
    public static final String EMPLOYEE_POSITION = "employeePosition";
    public static final String EMPLOYEE_IMAGE = "employeeImage";

    //Constructor
    public EmployeesCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    //makes a new view for employee list item
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(
                R.layout.employee_list_item, viewGroup, false);
    }

    //binds the employee name, employee position, and image to the employee list item
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        //get the employee name, employee position, and image from database
        String employeeName = cursor.getString(
                cursor.getColumnIndex(EMPLOYEE_NAME));
        String employeePosition = cursor.getString(
                cursor.getColumnIndex(EMPLOYEE_POSITION));
        byte[] image = cursor.getBlob(cursor.getColumnIndex(EMPLOYEE_IMAGE));
        ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);

        //get views
        TextView nameTextView = (TextView) view.findViewById(R.id.nameTextView);
        TextView phoneTextView = (TextView) view.findViewById(R.id.positionTextView);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageDocIcon);

        //set employee name, employee position, and image to the views
        nameTextView.setText(employeeName);
        phoneTextView.setText(employeePosition);
        imageView.setImageBitmap(theImage);
    }
}
