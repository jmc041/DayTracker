package com.dailytracksolutions.android.daytracker.database.SettingsDbSchema;

/**
 * Created by jacksonmalcolmchaplin on 26/10/17.
 */

public class SettingsDbSchema {
    public static final class SettingsTable {
        public static final String NAME = "settings";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String NAME = "name";
            public static final String COMMENTS ="comments";
            public static final String EMAIL = "email";

        }
    }
}
