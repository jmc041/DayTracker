package com.dailytracksolutions.android.daytracker.database.EntryDbSchema;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dailytracksolutions.android.daytracker.database.EntryDbSchema.EntryDbSchema.EntryTable;

/**
 * Created by jacksonmalcolmchaplin on 19/10/17.
 */

public class EntryBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "EntryBase.db";

    public EntryBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + EntryTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                EntryTable.Cols.UUID + ", " +
                EntryTable.Cols.TITLE + ", " +
                EntryTable.Cols.DETAILS + ", " +
                EntryTable.Cols.DATE + ", " +
                EntryTable.Cols.WORK + ", " +
                EntryTable.Cols.LEISURE + ", " +
                EntryTable.Cols.SPORT + ", " +
                EntryTable.Cols.STUDY +
                ")"
        );
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
