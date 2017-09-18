package com.hayk.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hayk.myapplication.fragments.LoginFragment;

public class MainActivity extends Activity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, LoginFragment.newInstance())
                .commit();

    }

}