package com.dailytracksolutions.android.daytracker.database.EntryDbSchema;

/**
 * Created by jacksonmalcolmchaplin on 19/10/17.
 */

public class EntryDbSchema {
    public static final class EntryTable {
        public static final String NAME = "entries";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DETAILS ="details";
            public static final String DATE = "date";
            public static final String WORK = "work";
            public static final String LEISURE = "leisure";
            public static final String SPORT = "sport";
            public static final String STUDY = "study";

        }

    }
}
