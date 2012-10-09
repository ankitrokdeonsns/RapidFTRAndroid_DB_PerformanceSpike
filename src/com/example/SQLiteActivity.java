package com.example;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;


public class SQLiteActivity extends Activity{

    SQLiteHelper sqLiteHelper ;
    private static int noOfRecords = 500;
    private SQLiteDatabase database;
    long startTime,endTime;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite_layout);
        sqLiteHelper= new SQLiteHelper(getApplicationContext(),"sqlite.db",null,1);
        database = sqLiteHelper.getWritableDatabase();

        DBOperations();

    }

    private void DBOperations() {
        StringBuilder stats=new StringBuilder();

        startTime = System.currentTimeMillis();
        insertRecords();
        endTime = System.currentTimeMillis();
        stats.append("\n\n Time to insert ").append(noOfRecords).append(" records : ").append(endTime - startTime);


        startTime = System.currentTimeMillis();
        int retrievedNoOfRecords = retrieveRecords();
        endTime = System.currentTimeMillis();
        stats.append("\n\n Time to retrieve ").append(retrievedNoOfRecords).append(" records : ").append(endTime - startTime);

        TextView SQLCipherStatTextView = (TextView)findViewById(R.id.SQLITE_STATS);
        SQLCipherStatTextView.setText(stats.toString());

    }

    private void insertRecords() {
        for (int i = 0; i < noOfRecords; i++)
            database.execSQL("insert into children values(?,?,?)",
                    new Object[]{"username " + i, "id + " + i, "this is a child " + i});
    }

    private int retrieveRecords() {
        Cursor cursor = database.rawQuery("select * from children", null);
        int retrievedNoOfRecords = cursor.getCount();
        cursor.close();
        return retrievedNoOfRecords;
    }
}
