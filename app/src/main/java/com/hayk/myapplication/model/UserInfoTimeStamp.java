package com.hayk.myapplication.model;

import java.util.ArrayList;

/**
 * Created by User on 27.09.2017.
 */

public class UserInfoTimeStamp extends UserInfo {

    private ArrayList<Long> registrationTimeList = new ArrayList<>();

    public UserInfoTimeStamp(String registeredEmail) {
        super(registeredEmail);
    }

    public ArrayList<Long> getRegistrationTimeList() {
        return registrationTimeList;
    }

    public void setRegistrationTimeList(ArrayList<Long> registrationTimeList) {
        this.registrationTimeList = registrationTimeList;
    }
}
