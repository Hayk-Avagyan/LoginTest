package com.hayk.myapplication.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by User on 29.09.2017.
 */

public class UserInfoDbHelper extends SQLiteOpenHelper {

    public static abstract class UserInfoEntry implements BaseColumns {
        public static final String TABLE_NAME = "USER_INFO";
        public static final String TABLE_NAME_TIME = "USER_INFO_TIME";

        public static final String USER_PROFILE_ID = "USER_PROFILE_ID";
        static final String TIME_ID = "TIME_ID";
        public static final String EMAIL = "USER_EMAIL";
        public static final String FIRST_NAME = "USER_FIRST_NAME";
        public static final String LAST_NAME = "USER_LAST_NAME";
        public static final String AGE = "USER_AGE";
        public static final String TIME = "USER_REGISTRATION_TIME";
        public static final String COLUMN_NAME_NULLABLE = "NUll";
    }

    private static final String DB_NAME = "REGISTERED_USER_INFORMATION";
    private static final int DB_VERSION = 1;
    private static final String TEXT_TYPE = " TEXT";
    private static final String TEXT_TYPE_TIME = " INTEGER";
    private static final String COMMA_SEP = ",";

    private static final String CREATE_TABLE = "Create table " + UserInfoEntry.TABLE_NAME + "(" + UserInfoEntry.USER_PROFILE_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + UserInfoEntry.EMAIL + TEXT_TYPE + COMMA_SEP + UserInfoEntry.FIRST_NAME + TEXT_TYPE + COMMA_SEP
            + UserInfoEntry.LAST_NAME + TEXT_TYPE + COMMA_SEP + UserInfoEntry.AGE + TEXT_TYPE + ")";

    private static final String CREATE_TIME_TABLE = "Create table " + UserInfoEntry.TABLE_NAME_TIME + "(" + UserInfoEntry.TIME_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + UserInfoEntry.USER_PROFILE_ID + " INTEGER, " + UserInfoEntry.TIME + TEXT_TYPE_TIME + COMMA_SEP
            + "FOREIGN KEY(" + UserInfoEntry.USER_PROFILE_ID + ") REFERENCES " + UserInfoEntry.TABLE_NAME + UserInfoEntry.USER_PROFILE_ID + ")";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + UserInfoEntry.TABLE_NAME;

    public UserInfoDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TIME_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

}
