package com.hayk.myapplication.model;

/**
 * Created by User on 14.09.2017.
 */

public class LoginInfo {

    private String registeredEmail;
    private String registrationTime;

    public LoginInfo(String registeredEmail, String registrationTime) {
        this.registeredEmail = registeredEmail;
        this.registrationTime = registrationTime;
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
}
