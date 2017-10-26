package com.dailytracksolutions.android.daytracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dailytracksolutions.android.daytracker.database.EntryDbSchema.EntryBaseHelper;
import com.dailytracksolutions.android.daytracker.database.EntryDbSchema.EntryCursorWrapper;
import com.dailytracksolutions.android.daytracker.database.EntryDbSchema.EntryDbSchema;
import com.dailytracksolutions.android.daytracker.database.EntryDbSchema.EntryDbSchema.EntryTable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by jacksonmalcolmchaplin on 17/10/17.
 */

public class EntryLab {
    private static EntryLab sEntryLab;

    //private List<Entry> mEntries;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static EntryLab get(Context context) {
        if (sEntryLab == null) {
            sEntryLab = new EntryLab(context);
        }
        return sEntryLab;
    }

    private EntryLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new EntryBaseHelper(mContext)
                .getWritableDatabase();
        //mEntries = new ArrayList<>();

    }
    public void addEntry(Entry e) {
        //mEntries.add(e);
        ContentValues values = getContentValues(e);

        mDatabase.insert(EntryTable.NAME, null, values);
    }

/////////////////////////////////////////////////////DELETE BUTTON///////////////
    public void deleteEntry(Entry e) {
        //ContentValues values = getContentValues(e);

    }

    public List<Entry> getEntries() {
        //return mEntries;
        //return new ArrayList<>();
        List<Entry> entries = new ArrayList<>();

        EntryCursorWrapper cursor = queryEntries(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                entries.add(cursor.getEntry());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return entries;
    }

    public Entry getEntry(UUID id) {
        EntryCursorWrapper cursor = queryEntries(
                EntryTable.Cols.UUID + " = ?",
                new String[] {id.toString() }
        );

        try {
            if (cursor.getCount() == 0) {
                return  null;
            }

            cursor.moveToFirst();
            return cursor.getEntry();
        } finally {
            cursor.close();
        }
    }

    public File getPhotoFile(Entry entry) {
        File filesDir = mContext.getFilesDir();
        return new File (filesDir, entry.getPhotoFilename());
    }

    public void updateEntry(Entry entry) {
        String uuidString = entry.getId().toString();
        ContentValues values = getContentValues(entry);

        mDatabase.update(EntryTable.NAME, values,
                EntryTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    //private Cursor queryEntries(String whereClause, String[] whereArgs) {
    private EntryCursorWrapper queryEntries(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                EntryTable.NAME,
                null, //columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, //having
                null //orderBy
        );
        return new EntryCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Entry entry) {
        ContentValues values = new ContentValues();
        values.put(EntryTable.Cols.UUID, entry.getId().toString());
        values.put(EntryTable.Cols.TITLE, entry.getTitle());
        values.put(EntryTable.Cols.DETAILS, entry.getDetails());
        values.put(EntryTable.Cols.DATE, entry.getDate().getTime());
        values.put(EntryTable.Cols.WORK, entry.isWork() ? 1 : 0 );
        values.put(EntryTable.Cols.LEISURE, entry.isLeisure() ? 1 : 0 );
        values.put(EntryTable.Cols.SPORT, entry.isSport() ? 1 : 0 );
        values.put(EntryTable.Cols.STUDY, entry.isStudy() ? 1 : 0 );

        return values;
    }
}
