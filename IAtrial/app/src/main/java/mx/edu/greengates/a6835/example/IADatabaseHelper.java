package mx.edu.greengates.a6835.example;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;

/*
* This database helper is created based on website
* “Save Data Using SQLite  :   Android Developers.” Android Developers, developer.android.com/training/data-storage/sqlite.
* */


public class IADatabaseHelper extends SQLiteOpenHelper {  //defining each columns of table in the database
    public static final String IADB_NAME = "Student.db"; //file name of the database
    public static final String TABLE_NAME = "students_info"; //table name inside the database
    public static final String COLUMN_1 = "ID"; //column that contains id number for individual student
    public static final String COLUMN_2 = "first_name"; //column that contains first name of individual student
    public static final String COLUMN_3 = "last_name"; //column that contains last name of individual student
    public static final String COLUMN_4 = "phone_number"; //column that contains phone number for individual student
    public static final String COLUMN_5 = "class_data"; //column that contains date of class for individual student

    public IADatabaseHelper(Context context) { //Constrictor
        super(context, IADB_NAME, null, 1);
    }

    @Override //creating SQLitedatabase
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY, NAME TEXT , LAST TEXT , PHONE TEXT , CLASS TEXT )");
    }

    @Override //Used when upgrading the database
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }


    public boolean insertData(String firstname, String lastname, String phone, String date) {//method for inserting data to database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVlues = new ContentValues();
        cVlues.put(COLUMN_2, firstname);
        cVlues.put(COLUMN_3, lastname);
        cVlues.put(COLUMN_4, phone);
        cVlues.put(COLUMN_5, date);
        long result = db.insert(TABLE_NAME, null, cVlues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllStudents() { //Cursor which will track record by record stored in the database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_NAME, null);
        return c;
    }
}