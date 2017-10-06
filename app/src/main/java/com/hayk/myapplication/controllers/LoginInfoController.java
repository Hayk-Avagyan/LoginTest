package com.hayk.myapplication.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.hayk.myapplication.databases.UserInfoDbHelper;
import com.hayk.myapplication.model.UserInfoTimeStamp;

import java.util.ArrayList;

/**
 * Created by User on 13.09.2017.
 */
public class LoginInfoController {

    private final int NO_MAPPING_ID = -1;

    private static LoginInfoController instance;

    public ArrayList<UserInfoTimeStamp> getAllUserInfo(Context context) {
        UserInfoDbHelper userInfoDbHelper = new UserInfoDbHelper(context);
        SQLiteDatabase database = userInfoDbHelper.getReadableDatabase();
        String[] projection = {UserInfoDbHelper.UserInfoEntry.USER_PROFILE_ID, UserInfoDbHelper.UserInfoEntry.EMAIL, UserInfoDbHelper.UserInfoEntry.FIRST_NAME, UserInfoDbHelper.UserInfoEntry.LAST_NAME, UserInfoDbHelper.UserInfoEntry.AGE};
        Cursor cursor = database.query(UserInfoDbHelper.UserInfoEntry.TABLE_NAME, projection, null, null, null, null, null);

        ArrayList<UserInfoTimeStamp> timeStampArrayList = new ArrayList<>();
        while (cursor.moveToNext()) {
            UserInfoTimeStamp userInfoTimeStamp = new UserInfoTimeStamp(cursor.getString(cursor.getColumnIndex(UserInfoDbHelper.UserInfoEntry.EMAIL)));
            userInfoTimeStamp.setUserFirstName(cursor.getString(cursor.getColumnIndex(UserInfoDbHelper.UserInfoEntry.FIRST_NAME)));
            userInfoTimeStamp.setUserLastName(cursor.getString(cursor.getColumnIndex(UserInfoDbHelper.UserInfoEntry.LAST_NAME)));
            userInfoTimeStamp.setUserAge(cursor.getString(cursor.getColumnIndex(UserInfoDbHelper.UserInfoEntry.AGE)));

            String profileId = String.valueOf(cursor.getInt(cursor.getColumnIndex(UserInfoDbHelper.UserInfoEntry.USER_PROFILE_ID)));

            String[] projectionTime = {UserInfoDbHelper.UserInfoEntry.TIME};
            String selectionTime = UserInfoDbHelper.UserInfoEntry.USER_PROFILE_ID + " =?";
            String[] selectionArgsTime = {profileId};
            Cursor cursorTime = database.query(UserInfoDbHelper.UserInfoEntry.TABLE_NAME_TIME, projectionTime, selectionTime, selectionArgsTime, null, null, null);

            ArrayList<Long> arrayList = userInfoTimeStamp.getRegistrationTimeList();
            while (cursorTime.moveToNext()) {
                arrayList.add(cursorTime.getLong(cursorTime.getColumnIndex(UserInfoDbHelper.UserInfoEntry.TIME)));
            }
            timeStampArrayList.add(userInfoTimeStamp);
            cursorTime.close();
        }
        cursor.close();
        return timeStampArrayList;
    }

    public void addLoginInfo(Context context, String email) {
        long date = System.currentTimeMillis();
        UserInfoDbHelper userInfoDbHelper = new UserInfoDbHelper(context);
        SQLiteDatabase database = userInfoDbHelper.getWritableDatabase();
        if (!isEmailContained(context, email)) {
            ContentValues contentValuesEmail = new ContentValues();
            contentValuesEmail.put(UserInfoDbHelper.UserInfoEntry.EMAIL, email);
            database.insert(UserInfoDbHelper.UserInfoEntry.TABLE_NAME, UserInfoDbHelper.UserInfoEntry.COLUMN_NAME_NULLABLE, contentValuesEmail);
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put(UserInfoDbHelper.UserInfoEntry.TIME, date);
        contentValues.put(UserInfoDbHelper.UserInfoEntry.USER_PROFILE_ID, getIdByEmail(context, email));
        database.insert(UserInfoDbHelper.UserInfoEntry.TABLE_NAME_TIME, UserInfoDbHelper.UserInfoEntry.COLUMN_NAME_NULLABLE, contentValues);
    }

    public int setPersonalData(String firstName, String lastName, String userAge, String email, Context context) {
        UserInfoDbHelper userInfoDbHelper = new UserInfoDbHelper(context);
        SQLiteDatabase database = userInfoDbHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserInfoDbHelper.UserInfoEntry.FIRST_NAME, firstName);
        contentValues.put(UserInfoDbHelper.UserInfoEntry.LAST_NAME, lastName);
        contentValues.put(UserInfoDbHelper.UserInfoEntry.AGE, userAge);
        String selection = UserInfoDbHelper.UserInfoEntry.EMAIL + " =?";
        String[] selectionArgs = {email};

        return database.update(UserInfoDbHelper.UserInfoEntry.TABLE_NAME, contentValues, selection, selectionArgs);
    }

    public UserInfoTimeStamp getUserInfoByEmail(Context context, String email) {
        UserInfoTimeStamp userInfoTimeStamp = new UserInfoTimeStamp(email);
        UserInfoDbHelper userInfoDbHelper = new UserInfoDbHelper(context);
        SQLiteDatabase database = userInfoDbHelper.getReadableDatabase();

        String[] projection = {UserInfoDbHelper.UserInfoEntry.USER_PROFILE_ID, UserInfoDbHelper.UserInfoEntry.FIRST_NAME, UserInfoDbHelper.UserInfoEntry.LAST_NAME, UserInfoDbHelper.UserInfoEntry.AGE};
        String selection = UserInfoDbHelper.UserInfoEntry.EMAIL + " =?";
        String[] selectionArgs = {email};
        Cursor cursor = database.query(UserInfoDbHelper.UserInfoEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            userInfoTimeStamp.setUserFirstName(cursor.getString(cursor.getColumnIndex(UserInfoDbHelper.UserInfoEntry.FIRST_NAME)));
            userInfoTimeStamp.setUserLastName(cursor.getString(cursor.getColumnIndex(UserInfoDbHelper.UserInfoEntry.LAST_NAME)));
            userInfoTimeStamp.setUserAge(cursor.getString(cursor.getColumnIndex(UserInfoDbHelper.UserInfoEntry.AGE)));
        }

        String profileId = String.valueOf(cursor.getInt(cursor.getColumnIndex(UserInfoDbHelper.UserInfoEntry.USER_PROFILE_ID)));
        cursor.close();

        String[] projectionTime = {UserInfoDbHelper.UserInfoEntry.TIME};
        String selectionTime = UserInfoDbHelper.UserInfoEntry.USER_PROFILE_ID + " =?";
        String[] selectionArgsTime = {profileId};
        Cursor cursorTime = database.query(UserInfoDbHelper.UserInfoEntry.TABLE_NAME_TIME, projectionTime, selectionTime, selectionArgsTime, null, null, null);

        ArrayList<Long> arrayList = userInfoTimeStamp.getRegistrationTimeList();
        while (cursorTime.moveToNext()) {
            arrayList.add(cursorTime.getLong(cursorTime.getColumnIndex(UserInfoDbHelper.UserInfoEntry.TIME)));
        }
        cursorTime.close();

        return userInfoTimeStamp;
    }

    private boolean isEmailContained(Context context, String email) {
        UserInfoDbHelper userInfoDbHelper = new UserInfoDbHelper(context);
        SQLiteDatabase database = userInfoDbHelper.getReadableDatabase();
        String[] projection = {UserInfoDbHelper.UserInfoEntry.EMAIL};
        String selection = UserInfoDbHelper.UserInfoEntry.EMAIL + " =?";
        String[] selectionArgs = {email};
        Cursor cursor = database.query(UserInfoDbHelper.UserInfoEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        boolean isEmailContained = (cursor.getCount() > 0);
        cursor.close();
        return isEmailContained;
    }

    /**
     * @return Id of the LoginInfo corresponding to given email
     * or -1 if such email is does not exist.
     */
    private int getIdByEmail(Context context, String email) {
        UserInfoDbHelper userInfoDbHelper = new UserInfoDbHelper(context);
        SQLiteDatabase database = userInfoDbHelper.getReadableDatabase();
        String[] projection = {UserInfoDbHelper.UserInfoEntry.USER_PROFILE_ID};
        String selection = UserInfoDbHelper.UserInfoEntry.EMAIL + " =?";
        String[] selectionArgs = {email};

        Cursor cursor = database.query(UserInfoDbHelper.UserInfoEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(UserInfoDbHelper.UserInfoEntry.USER_PROFILE_ID));
            cursor.close();
            return id;
        } else {
            cursor.close();
            return NO_MAPPING_ID;
        }
    }

    private LoginInfoController() {
    }

    public static LoginInfoController getInstance() {
        Log.w("Tag", "LoginInfoController:getInstance()");
        if (null == instance) {
            instance = new LoginInfoController();
        }
        return instance;
    }

}
