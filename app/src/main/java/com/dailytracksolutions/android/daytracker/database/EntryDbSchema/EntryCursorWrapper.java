package com.dailytracksolutions.android.daytracker.database.EntryDbSchema;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.dailytracksolutions.android.daytracker.Entry;
import com.dailytracksolutions.android.daytracker.database.EntryDbSchema.EntryDbSchema.EntryTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by jacksonmalcolmchaplin on 19/10/17.
 */

public class EntryCursorWrapper extends CursorWrapper {

    public EntryCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Entry getEntry() {
        String uuidString = getString(getColumnIndex(EntryTable.Cols.UUID));
        String title = getString(getColumnIndex(EntryTable.Cols.TITLE));
        String details = getString(getColumnIndex(EntryTable.Cols.DETAILS));
        long date = getLong(getColumnIndex(EntryTable.Cols.DATE));
        int isWork = getInt(getColumnIndex(EntryTable.Cols.WORK));
        int isLeisure = getInt(getColumnIndex(EntryTable.Cols.LEISURE));
        int isSport = getInt(getColumnIndex(EntryTable.Cols.SPORT));
        int isStudy = getInt(getColumnIndex(EntryTable.Cols.STUDY));

        Entry entry = new Entry (UUID.fromString(uuidString));
        entry.setTitle(title);
        entry.setDetails(details);
        entry.setDate(new Date(date));
        entry.setWork(isWork != 0 );
        entry.setLeisure(isLeisure != 0 );
        entry.setSport(isSport != 0 );
        entry.setStudy(isStudy != 0 );

        return entry;

    }
}
