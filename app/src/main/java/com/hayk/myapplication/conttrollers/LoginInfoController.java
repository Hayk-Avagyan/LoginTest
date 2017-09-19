package com.hayk.myapplication.conttrollers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.hayk.myapplication.model.LoginInfo;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by User on 13.09.2017.
 */
public class LoginInfoController {

    private static LoginInfoController instance;

    public ArrayList<LoginInfo> getRegisteredData(Context context) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        ArrayList<LoginInfo> infoArrayList = new ArrayList<>();

        Map<String, ?> allEntries = sharedPreferences.getAll();

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {

            String loginInfoJsonString = (String) entry.getValue();
            infoArrayList.add(LoginInfo.convertFromJson(loginInfoJsonString));
        }
        return infoArrayList;
    }

    public void addRegisteredData(LoginInfo loginInfo, Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String newKey = String.valueOf(sharedPreferences.getAll().size() + 1);

        editor.putString(newKey, loginInfo.convertToJson(loginInfo));
        editor.apply();
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
