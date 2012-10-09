package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button sqlCipherButton = (Button)findViewById(R.id.SQL_CIPHER_BUTTON);
        sqlCipherButton.setOnClickListener(sqlCipherListener);

        Button sqliteButton = (Button)findViewById(R.id.SQLITE_BUTTON);
        sqliteButton.setOnClickListener(sqliteListener);
    }

    private View.OnClickListener sqlCipherListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(getApplicationContext(),SQLCipherActivity.class));
        }
    };

    private View.OnClickListener sqliteListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(getApplicationContext(),SQLiteActivity.class));
        }
    };



}
