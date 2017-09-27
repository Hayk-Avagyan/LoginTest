package com.hayk.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.hayk.myapplication.fragments.LoginFragment;
import com.hayk.myapplication.interfaces.OnUserInfoAddListener;
import com.hayk.myapplication.model.UserInfo;

public class MainActivity extends Activity implements OnUserInfoAddListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, LoginFragment.newInstance())
                .commit();

    }

    @Override
    public void onItemAdd(UserInfo userInfo) {
        String registeredEmail = userInfo.getRegisteredEmail();
        String registrationTime = userInfo.getRegistrationTime();
        Toast.makeText(this, registeredEmail + "  " + registrationTime, Toast.LENGTH_LONG).show();

    }
}