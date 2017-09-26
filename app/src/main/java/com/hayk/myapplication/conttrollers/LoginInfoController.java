package com.hayk.myapplication.conttrollers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.hayk.myapplication.model.UserInfo;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by User on 13.09.2017.
 */
public class LoginInfoController {

    private static LoginInfoController instance;

    public ArrayList<UserInfo> getRegisteredData(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        ArrayList<UserInfo> infoArrayList = new ArrayList<>();

        Map<String, ?> allEntries = sharedPreferences.getAll();

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {

            String loginInfoJsonString = (String) entry.getValue();
            infoArrayList.add(UserInfo.convertFromJson(loginInfoJsonString));
        }
        return infoArrayList;
    }

    public void addRegisteredData(UserInfo userInfo, Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String email = userInfo.getRegisteredEmail();

        editor.putString(email, userInfo.convertToJson(userInfo));
        editor.apply();
    }

    public void addUserProfile(String firstName, String lastName, String userAge, String email, Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String userProfile = sharedPreferences.getString(email, null);
        UserInfo userInfo = UserInfo.convertFromJson(userProfile);
        userInfo.setUserFirstName(firstName);
        userInfo.setUserLastName(lastName);
        userInfo.setUserAge(userAge);

        editor.putString(email, userInfo.convertToJson(userInfo));
        editor.apply();
    }

    public UserInfo getUserProfile(Context context, String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String userProfile = sharedPreferences.getString(key, null);

        return UserInfo.convertFromJson(userProfile);
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
