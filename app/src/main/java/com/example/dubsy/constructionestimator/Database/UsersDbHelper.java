package com.example.dubsy.constructionestimator.Database;
// This file currently does Users, and Contracts. Needs to be refactored.

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.example.dubsy.constructionestimator.Database.Model.ContractsModel;
import com.example.dubsy.constructionestimator.Database.Model.UsersModel;
import com.example.dubsy.constructionestimator.Utilities.UserSession;

import java.util.ArrayList;

public class UsersDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "drywall_contracts_database.db";

    //*********************************************************************************
    // Users Table
    private static final String SQL_CREATE_USERS_TABLE = "CREATE TABLE " +
            DatabaseSchema.Users.TABLE_NAME + " (" +
            DatabaseSchema.Users._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            DatabaseSchema.Users.COLUMN_NAME_USERNAME + " TEXT," +
            DatabaseSchema.Users.COLUMN_NAME_EMAIL + " TEXT," +
            DatabaseSchema.Users.COLUMN_NAME_PASSWORD + " TEXT)";

    private static final String SQL_DELETE_USERS_TABLE = "DROP TABLE IF EXISTS " + DatabaseSchema.Users.TABLE_NAME;
    // Users Table End


    // Contracts Table
    private static final String SQL_CREATE_CONTRACTS_TABLE = "CREATE TABLE " +
            DatabaseSchema.Contracts.TABLE_NAME + " (" +
            DatabaseSchema.Contracts._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            DatabaseSchema.Contracts.COLUMN_NAME_ADDRESS + " TEXT," +
            DatabaseSchema.Contracts.COLUMN_NAME_BOARD_FOOTAGE + " INTEGER," +
            DatabaseSchema.Contracts.COLUMN_NAME_USER_ID + " INTEGER," +
            DatabaseSchema.Contracts.COLUMN_NAME_RATE + " INTEGER, FOREIGN KEY (" +
            DatabaseSchema.Contracts.COLUMN_NAME_USER_ID + ") REFERENCES " +
            DatabaseSchema.Users.TABLE_NAME + "(" +
            DatabaseSchema.Users._ID + "));";

    private static final String SQL_DELETE_CONTRACTS_TABLE = "DROP TABLE IF EXISTS " + DatabaseSchema.Contracts.TABLE_NAME;
    // Contracts Table End
    //*********************************************************************************


    // Dbhelper singleton
    private static UsersDbHelper sInstance;

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
        sqLiteDatabase.execSQL(SQL_CREATE_CONTRACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_USERS_TABLE);
        sqLiteDatabase.execSQL(SQL_DELETE_CONTRACTS_TABLE);
        onCreate(sqLiteDatabase);
    }

    public long createUserAccount(UsersModel user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseSchema.Users.COLUMN_NAME_USERNAME, user.getUsername());
        values.put(DatabaseSchema.Users.COLUMN_NAME_EMAIL, user.getEmailAddress());
        values.put(DatabaseSchema.Users.COLUMN_NAME_PASSWORD, user.getHashedPassword());

        long userId = db.insert(DatabaseSchema.Users.TABLE_NAME, null, values);
        db.close();

        return userId;

    }

    // Not currently connected. Really super buggy.
    public boolean userExist(UsersModel u) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                DatabaseSchema.Users.COLUMN_NAME_USERNAME,
                DatabaseSchema.Users.COLUMN_NAME_EMAIL,
                DatabaseSchema.Users.COLUMN_NAME_PASSWORD
        };

        String selection = DatabaseSchema.Users.COLUMN_NAME_USERNAME + " = ?";
        String[] selectionArgs = { u.getUsername() };


        Cursor cursor = db.query(
                DatabaseSchema.Users.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        while(cursor.moveToNext()) {
            if (u.getUsername().equals(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseSchema.Users.COLUMN_NAME_USERNAME)))) {
                cursor.close();
                db.close();
                return true;
            }
        }

        cursor.close();
        db.close();
        return false;
    }


    public long createContract(ContractsModel contract) {
        long userId;

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseSchema.Contracts.COLUMN_NAME_ADDRESS, contract.getSiteAddress());
        values.put(DatabaseSchema.Contracts.COLUMN_NAME_BOARD_FOOTAGE, contract.getBoardFootage());
        values.put(DatabaseSchema.Contracts.COLUMN_NAME_RATE, contract.getRate());
        values.put(DatabaseSchema.Contracts.COLUMN_NAME_USER_ID, UserSession.getInstance().getUserId());

        userId = db.insert(DatabaseSchema.Contracts.TABLE_NAME, null, values);

        db.close();

        return userId;
    }

    // sloppy/buggy implementation
    public ArrayList<ContractsModel> grabAllContracts(boolean where, int id) {
        ArrayList<ContractsModel> c = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectStatement = "";
        if (where) {
            selectStatement = "SELECT * FROM CONTRACTS WHERE " + DatabaseSchema.Contracts.COLUMN_NAME_USER_ID + " = " + id;
        } else {
            selectStatement = "SELECT * FROM CONTRACTS";
        }

        Cursor cursor = db.rawQuery(selectStatement, null);

        if  (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String address = cursor.getString(cursor.getColumnIndex(DatabaseSchema.Contracts.COLUMN_NAME_ADDRESS));
                String boardFeet = cursor.getString(cursor.getColumnIndex(DatabaseSchema.Contracts.COLUMN_NAME_BOARD_FOOTAGE));
                String rate = cursor.getString(cursor.getColumnIndex(DatabaseSchema.Contracts.COLUMN_NAME_RATE));
                c.add(new ContractsModel(address, boardFeet, rate));
                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();
        return c;
    }

    public int getUserId(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        int returnValue = 0;
        String selectStatement = "Select _ID from Users WHERE username = ?";
        Cursor cursor = db.rawQuery(selectStatement, new String[]{username});
        if  (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                returnValue = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseSchema.Users._ID)));
                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();
        return returnValue;
    }

}