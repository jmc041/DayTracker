package com.dailytracksolutions.android.daytracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.dailytracksolutions.android.daytracker.database.EntryDbSchema.EntryBaseHelper;
import com.dailytracksolutions.android.daytracker.database.EntryDbSchema.EntryDbSchema;
import com.dailytracksolutions.android.daytracker.database.SettingsDbSchema.SettingsBaseHelper;
import com.dailytracksolutions.android.daytracker.database.SettingsDbSchema.SettingsDbSchema;
import com.dailytracksolutions.android.daytracker.database.SettingsDbSchema.SettingsDbSchema.SettingsTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by jacksonmalcolmchaplin on 20/10/17.
 */

class SettingsLab {
    private static SettingsLab sSettingsLab;

    private List<Settings> mSettings;

    public static SettingsLab get(Context context) {
        if (sSettingsLab == null) {
            sSettingsLab = new SettingsLab(context);
        }
        return sSettingsLab;
    }
    private SettingsLab(Context context) {
        mSettings = new ArrayList<>();
    }


    public List<Settings> getSettings() {
        return mSettings;
    }

    public Settings getSettings(UUID id) {
        for (Settings settings : mSettings) {
            if (settings.getSId().equals(id)) {
                return settings;
            }
        }
        return null;
    }


}


//    private static SettingsLab sSettingsLab;
//    //private List<Settings> mSettings;
//    private Context mContext;
//    private SQLiteDatabase mDatabase;
//
//    public static SettingsLab get(Context context) {
//        if (sSettingsLab == null) {
//            sSettingsLab = new SettingsLab(context);
//        }
//        return sSettingsLab;
//    }
//
//    private SettingsLab(Context context){
//        mContext = context.getApplicationContext();
//        mDatabase = new SettingsBaseHelper(mContext)
//                .getWritableDatabase();
//        //mSettings = new ArrayList<>();
//
//    }
//    public void addSettings(Settings s) {
//        //mSettings.add(s);
////        ContentValues values = getContentValues(s);
////
////        mDatabase.insert(SettingsDbSchema.SettingsTable.NAME, null, values);
//    }
//
//    public List<Settings> getSettings() {
//        //return mEntries;
//        return new ArrayList<>();
//        //return mSettings;
//    }
//
//    public Settings getSettings(UUID id){
//        return null;
//    }
//
////    private static ContentValues getContentValues(Entry entry) {
////        ContentValues values = new ContentValues();
////        values.put(SettingsTable.Cols.UUID, sSettingsLab.getSettings().toString();
////        values.put(SettingsTable.Cols.NAME, sSettingsLab.getName());
////
////
////
////
////        return values;
////    }
//}
