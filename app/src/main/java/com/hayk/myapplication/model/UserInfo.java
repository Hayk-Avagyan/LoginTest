package com.hayk.myapplication.model;

/**
 * Created by User on 14.09.2017.
 */

public class UserInfo {

    private String registeredEmail;
    private String userFirstName;
    private String userLastName;
    private String userAge;

    UserInfo(String registeredEmail) {
        this.registeredEmail = registeredEmail;
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

}
