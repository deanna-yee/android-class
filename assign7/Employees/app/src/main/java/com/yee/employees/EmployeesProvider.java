package com.yee.employees;

/*
* Deanna Yee
* CIS 135 OL
* File Name: EmployeesProvider.java
* File Description: Provides content to application and encapsulate data and provide it to
*   application, share data with multiple application
* Assignment #: 7
* Date: 5/3/17
*/

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

public class EmployeesProvider extends ContentProvider {

    //Constants to access the employee database
    private static final String AUTHORITY = "com.yee.employees";
    private static final String BASE_PATH = "employees";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH );

    private static final int EMPLOYEES = 1;
    private static final int EMPLOYEE_ID = 2;

    //add URI to uriMatcher
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(AUTHORITY,BASE_PATH, EMPLOYEES);
        uriMatcher.addURI(AUTHORITY,BASE_PATH + "/#", EMPLOYEE_ID);
    }

    private SQLiteDatabase database;

    //create and get database
    @Override
    public boolean onCreate() {
        DBOpenHelper helper = new DBOpenHelper(getContext());
        database = helper.getWritableDatabase();
        return true;
    }

    //handle query request
    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        Cursor cursor;
        switch (uriMatcher.match(uri)) {
            case EMPLOYEES:
                cursor =  database.query(DBOpenHelper.TABLE_EMPLOYEES, DBOpenHelper.ALL_COLUMNS,
                        s, null, null, null, DBOpenHelper.EMPLOYEE_NAME +" ASC");
                break;
            default:
                throw new IllegalArgumentException("This is an Unknown URI " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(),uri);

        return cursor;
    }

    //get type of employees
    @Nullable
    @Override
    public String getType(Uri uri) {

        switch (uriMatcher.match(uri)) {
            case EMPLOYEES:
                return "vnd.android.cursor.dir/employees";
            default:
                throw new IllegalArgumentException("This is an Unknown URI " + uri);
        }
    }

    //insert employee to the data base
    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        long id = database.insert(DBOpenHelper.TABLE_EMPLOYEES , null, contentValues);

        if (id > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, id);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        throw new SQLException("Insertion Failed for URI :" + uri);

    }

    //when employee is deleted update employee count
    @Override
    public int delete(Uri uri, String s, String[] strings) {
        int delCount = 0;
        switch (uriMatcher.match(uri)) {
            case EMPLOYEES:
                delCount =  database.delete(DBOpenHelper.TABLE_EMPLOYEES, s, strings);
                break;
            default:
                throw new IllegalArgumentException("This is an Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return delCount;
    }

    //update employee count
    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        int updCount = 0;
        switch (uriMatcher.match(uri)) {
            case EMPLOYEES:
                updCount =  database.update(DBOpenHelper.TABLE_EMPLOYEES,contentValues, s, strings);
                break;
            default:
                throw new IllegalArgumentException("This is an Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return updCount;
    }
}
