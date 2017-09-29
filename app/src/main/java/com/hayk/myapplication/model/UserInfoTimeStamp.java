package com.hayk.myapplication.model;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by User on 27.09.2017.
 */

public class UserInfoTimeStamp extends UserInfo {

    private ArrayList<String> registrationTimeList = new ArrayList<>();

    public UserInfoTimeStamp(String registeredEmail) {
        super(registeredEmail);
    }

    public ArrayList<String> getRegistrationTimeList() {
        return registrationTimeList;
    }

    public void setRegistrationTimeList(ArrayList<String> registrationTimeList) {
        this.registrationTimeList = registrationTimeList;
    }

    public static UserInfoTimeStamp convertFromJson(String s) {
        Gson gson = new Gson();
        return gson.fromJson(s, UserInfoTimeStamp.class);
    }

    public String convertToJson(UserInfoTimeStamp userInfoTimeStamp) {
        Gson gson = new Gson();
        return gson.toJson(userInfoTimeStamp);
    }
}
