package com.xyys.testsqlite;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import org.litepal.tablemanager.Connector;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase db = Connector.getDatabase();
    }
}