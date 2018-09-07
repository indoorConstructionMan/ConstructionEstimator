package com.example.dubsy.constructionestimator;

import android.provider.BaseColumns;

public final class DatabaseSchema {
    private DatabaseSchema() {}

    public static class Users implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PASSWORD = "password";
    }

    public static class Contracts implements BaseColumns {
        public static final String TABLE_NAME = "contracts";
        public static final String COLUMN_NAME_ADDRESS = "address";
        public static final String COLUMN_NAME_BOARD_FOOTAGE = "boardfootage";
        public static final String COLUMN_NAME_RATE = "rate";
    }

}
