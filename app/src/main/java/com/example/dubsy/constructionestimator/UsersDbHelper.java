package com.example.dubsy.constructionestimator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UsersDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DrywallContract.db";

    private static UsersDbHelper sInstance;

    private static final String SQL_CREATE_USERS_TABLE = "CREATE TABLE " +
            DatabaseSchema.Users.TABLE_NAME + " (" +
            DatabaseSchema.Users._ID + " INTEGER PRIMARY KEY," +
            DatabaseSchema.Users.COLUMN_NAME_USERNAME + " TEXT," +
            DatabaseSchema.Users.COLUMN_NAME_EMAIL + " TEXT," +
            DatabaseSchema.Users.COLUMN_NAME_PASSWORD + " TEXT)";

    private static final String SQL_DELETE_USERS_TABLE = "DROP TABLE IF EXISTS " + DatabaseSchema.Users.TABLE_NAME;

    public static synchronized UsersDbHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new UsersDbHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private UsersDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_USERS_TABLE);
        onCreate(sqLiteDatabase);
    }
}