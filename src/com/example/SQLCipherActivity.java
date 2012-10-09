package com.example;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import net.sqlcipher.database.SQLiteDatabase;

import java.io.File;

public class SQLCipherActivity extends Activity {

    private static int noOfRecords = 500;
    private SQLiteDatabase database;
    long startTime,endTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite_layout);

        initializeSQLCipher();
        DBOperations();

    }

    private void closeDB() {
        database.close();
    }

    private void openDB() {
        File databaseFile = getDatabasePath("demo.db");
        database = SQLiteDatabase.openOrCreateDatabase(databaseFile, "demo", null);
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
        openDB();
        for (int i = 0; i < noOfRecords; i++)
            database.execSQL("insert into children values(?,?,?)",
                    new Object[]{"username " + i, "id + " + i, "this is a child " + i});
        closeDB();
    }

    private int retrieveRecords() {
        openDB();
        Cursor cursor = database.rawQuery("select * from children", null);
        int retrievedNoOfRecords = cursor.getCount();
        cursor.close();
        closeDB();
        return retrievedNoOfRecords;
    }

    private void initializeSQLCipher() {
        SQLiteDatabase.loadLibs(this);
        File databaseFile = getDatabasePath("demo.db");
        databaseFile.mkdirs();
        databaseFile.delete();
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databaseFile, "demo", null);
        database.execSQL("CREATE TABLE CHILDREN (" +
                "OWNER_USERNAME VARCHAR(255)," +
                "CHILD_ID VARCHAR(255)," +
                "CONTENT VARCHAR(9999))");

        database.close();
    }


}
