package com.hayk.myapplication.model;

import com.google.gson.Gson;

/**
 * Created by User on 14.09.2017.
 */

public class UserInfo {

    private String registeredEmail;
    private String registrationTime;
    private String userFirstName;
    private String userLastName;
    private String userAge;

    public UserInfo(String registeredEmail, String registrationTime) {
        this.registeredEmail = registeredEmail;
        this.registrationTime = registrationTime;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getRegisteredEmail() {
        return registeredEmail;
    }

    public void setRegisteredEmail(String registeredEmail) {
        this.registeredEmail = registeredEmail;
    }

    public String getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(String registrationTime) {
        this.registrationTime = registrationTime;
    }

    public static UserInfo convertFromJson(String s) {
        Gson gson = new Gson();
        return gson.fromJson(s, UserInfo.class);
    }

    public String convertToJson(UserInfo userInfo) {
        Gson gson = new Gson();
        return gson.toJson(userInfo);
    }
}
