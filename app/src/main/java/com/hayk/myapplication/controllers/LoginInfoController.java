package com.hayk.myapplication.controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.hayk.myapplication.interfaces.OnUserInfoAddListener;
import com.hayk.myapplication.model.UserInfoTimeStamp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by User on 13.09.2017.
 */
public class LoginInfoController {

    private static LoginInfoController instance;

    public ArrayList<UserInfoTimeStamp> getRegisteredData(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        ArrayList<UserInfoTimeStamp> infoArrayList = new ArrayList<>();

        Map<String, ?> allEntries = sharedPreferences.getAll();

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {

            String loginInfoJsonString = (String) entry.getValue();
            infoArrayList.add(UserInfoTimeStamp.convertFromJson(loginInfoJsonString));
        }
        return infoArrayList;
    }

    public void addRegisteredData(Context context, String email) {
        long date = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy  HH:mm:ss");
        String currentTime = dateFormat.format(date);

        OnUserInfoAddListener onUserInfoAddListener = (OnUserInfoAddListener) context;

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String userProfile = sharedPreferences.getString(email, null);
        UserInfoTimeStamp userInfoTimeStamp;
        if (userProfile == null) {
            userInfoTimeStamp = new UserInfoTimeStamp(email);
            userInfoTimeStamp.getRegistrationTimeList().add(currentTime);
            onUserInfoAddListener.onItemAdd(userInfoTimeStamp);
        } else {
            userInfoTimeStamp = UserInfoTimeStamp.convertFromJson(userProfile);
            userInfoTimeStamp.getRegistrationTimeList().add(currentTime);
        }

        editor.putString(email, userInfoTimeStamp.convertToJson(userInfoTimeStamp));
        editor.apply();
    }

    public void addUserProfile(String firstName, String lastName, String userAge, String email, Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String userProfile = sharedPreferences.getString(email, null);
        UserInfoTimeStamp userInfoTimeStamp = UserInfoTimeStamp.convertFromJson(userProfile);
        userInfoTimeStamp.setUserFirstName(firstName);
        userInfoTimeStamp.setUserLastName(lastName);
        userInfoTimeStamp.setUserAge(userAge);

        editor.putString(email, userInfoTimeStamp.convertToJson(userInfoTimeStamp));
        editor.apply();
    }

    public UserInfoTimeStamp getUserProfile(Context context, String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String userProfile = sharedPreferences.getString(key, null);

        return UserInfoTimeStamp.convertFromJson(userProfile);
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
