package com.yee.employees;

/*
* Deanna Yee
* CIS 135 OL
* File Name: EmployeesCursorAdapter.java
* File Description: gets the value from the cursor and sets the nameTextView, imageView, and
*   positionTextView for each employee list item
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

    //constructor
    public EmployeesCursorAdapter(Context context, Cursor c, int flags){
        super(context, c, flags);
    }

    //inflate the layout employee_list_item.xml
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup){
        return LayoutInflater.from(context).inflate(R.layout.employee_list_item, viewGroup, false);
    }

    //get the values from the cursor and setting it to the nameTextView, imageView and positionTextView
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        //get employee name, position, and image from database
        String employeeName = cursor.getString(cursor.getColumnIndex(DBOpenHelper.EMPLOYEE_NAME));
        String employeePosition = cursor.getString(cursor.getColumnIndex(DBOpenHelper.EMPLOYEE_POSITION));
        byte[] image = cursor.getBlob(cursor.getColumnIndex(DBOpenHelper.EMPLOYEE_IMAGE));
        ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);

        //get views
        TextView nameTextView = (TextView) view.findViewById(R.id.nameTextView);
        TextView positionTextView = (TextView) view.findViewById(R.id.positionTextView);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageDocIcon);

        //set name, position, and image
        nameTextView.setText(employeeName);
        positionTextView.setText(employeePosition);
        imageView.setImageBitmap(theImage);
    }
}
