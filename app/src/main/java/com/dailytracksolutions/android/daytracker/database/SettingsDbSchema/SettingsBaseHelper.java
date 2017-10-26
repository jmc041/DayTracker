package com.dailytracksolutions.android.daytracker.database.SettingsDbSchema;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dailytracksolutions.android.daytracker.database.SettingsDbSchema.SettingsDbSchema.SettingsTable;

/**
 * Created by jacksonmalcolmchaplin on 26/10/17.
 */

public class SettingsBaseHelper extends SQLiteOpenHelper{
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "SettingsBase.db";

    public SettingsBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + SettingsTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                SettingsTable.Cols.UUID + ", " +
                SettingsTable.Cols.NAME + ", " +
                SettingsTable.Cols.COMMENTS + ", " +
                SettingsTable.Cols.EMAIL +
                ")"
        );
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
