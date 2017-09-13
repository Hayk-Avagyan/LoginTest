package com.hayk.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class MainActivity extends Activity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginFragment loginFragment = new LoginFragment();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, loginFragment)
                .commit();

    }

}