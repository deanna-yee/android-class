package com.yee.employees;

/*
* Deanna Yee
* CIS 135 OL
* File Name: DBOpenHelper.java
* File Description: create the database employees and delete the table if it exists during upgrades
*   and create new table
* Assignment #: 7
* Date: 5/3/17
*/

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper{

    //Constants for db name and version
    private static final String DATABASE_NAME = "employees.db";
    private static final int DATABASE_VERSION = 1;

    //Constants for table and columns
    public static final String TABLE_EMPLOYEES = "employees";
    public static final String EMPLOYEE_ID = "_id";
    public static final String EMPLOYEE_NAME = "employeeName";
    public static final String EMPLOYEE_POSITION = "employeePosition";
    public static final String EMPLOYEE_IMAGE = "employeeImage";
    public static final String EMPLOYEE_CREATED_ON = "employeeCreationTimeStamp";

    public static final String[] ALL_COLUMNS =
            {EMPLOYEE_ID, EMPLOYEE_NAME, EMPLOYEE_POSITION, EMPLOYEE_IMAGE, EMPLOYEE_CREATED_ON};

    //Create Table
    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_EMPLOYEES + " (" +
                    EMPLOYEE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    EMPLOYEE_NAME + " TEXT, " +
                    EMPLOYEE_POSITION + " TEXT, " +
                    EMPLOYEE_IMAGE + " BLOB, " +
                    EMPLOYEE_CREATED_ON + " TEXT default CURRENT_TIMESTAMP" +
                    ")";
    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //execute SQL and create the employee table
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    //Drop table if the table exists and create new table
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_EMPLOYEES);
        onCreate(sqLiteDatabase);
    }
}
