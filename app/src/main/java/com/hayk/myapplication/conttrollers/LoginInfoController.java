package com.hayk.myapplication.conttrollers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.hayk.myapplication.model.LoginInfo;

import java.util.ArrayList;

/**
 * Created by User on 13.09.2017.
 */
public class LoginInfoController {
    private static LoginInfoController instance;
    private ArrayList<LoginInfo> registeredData;
    private static final String USER_PREFERENCES_KEY = "user-preferences_key";
    private static final String CURRENT_TIME_PREFERENCES_KEY = "current_time_preferences_key";

    public ArrayList<LoginInfo> getRegisteredData(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        registeredData.add(new LoginInfo(sharedPreferences.getString(USER_PREFERENCES_KEY, ""), sharedPreferences.getString(CURRENT_TIME_PREFERENCES_KEY, "")));
        return registeredData;
    }

    public void addRegisteredData(LoginInfo loginInfo, Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_PREFERENCES_KEY, loginInfo.getRegisteredEmail());
        editor.putString(CURRENT_TIME_PREFERENCES_KEY, loginInfo.getRegistrationTime());
        editor.apply();
        registeredData.add(loginInfo);
    }

    private LoginInfoController() {
        this.registeredData = new ArrayList<>();
    }

    public static LoginInfoController getInstance() {
        Log.w("Tag", "ItemsContainer:getInstance()");
        if (null == instance) {
            instance = new LoginInfoController();
        }
        return instance;
    }

}
