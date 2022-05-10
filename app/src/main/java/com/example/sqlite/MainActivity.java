package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase database;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        database = databaseHelper.getWritableDatabase();

        deleteAllMountains();
        //addMountains("Kinnekulle", 306);
        printMountains();
        Log.d("MainA", "inga berg");
    }
    private void deleteAllMountains() {
        database.delete(DatabaseTables.Mountain.TABLE_NAME, null, null);
    }

    private void addMountains(String name, int height) {
        ContentValues values = new ContentValues();
        values.put(DatabaseTables.Mountain.COLUMN_NAME_NAME, name);
        values.put(DatabaseTables.Mountain.COLUMN_NAME_HEIGHT, height);
        database.insert(DatabaseTables.Mountain.TABLE_NAME, null, values);
    }
    private void printMountains() {
        Cursor cursor = database.query(DatabaseTables.Mountain.TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Mountain mountain = new Mountain(
                    cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseTables.Mountain.COLUMN_NAME_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseTables.Mountain.COLUMN_NAME_NAME)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseTables.Mountain.COLUMN_NAME_HEIGHT))
            );
            Log.d("MainA", mountain.getName());
        }
        cursor.close();
    }

}