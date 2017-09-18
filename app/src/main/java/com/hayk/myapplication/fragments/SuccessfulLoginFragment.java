package com.hayk.myapplication.fragments;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.hayk.myapplication.R;

/**
 * Created by User on 11.09.2017.
 */

public class SuccessfulLoginFragment extends Fragment {

    private static final String TAG_LOG = "LOG_D";
    private static final String USER_KEY = "email";
    private static final String PASSWORD_KEY = "password";

    private TextView emailView;
    private TextView passwordView;
    private Button logoutButton;

    public static SuccessfulLoginFragment newInstance(String email, String password) {
        Log.d(TAG_LOG, "newInstance: " + email + password);
        SuccessfulLoginFragment successfulLoginFragment = new SuccessfulLoginFragment();

        if (email != null && password != null) {
            Bundle args = new Bundle();
            args.putString(USER_KEY, email);
            args.putString(PASSWORD_KEY, password);
            successfulLoginFragment.setArguments(args);
        }
        return successfulLoginFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.successful_login_fragment, container, false);

        initViews(view);
        initOnClickListener();

        Bundle bundle = getArguments();
        if (getArguments() != null) {
            String email = bundle.getString(USER_KEY);
            String password = bundle.getString(PASSWORD_KEY);
            emailView.setText(email);
            passwordView.setText(password);
        }

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    public void initViews(View view) {
        emailView = (TextView) view.findViewById(R.id.successful_login);
        passwordView = (TextView) view.findViewById(R.id.successful_password);
        logoutButton = (Button) view.findViewById(R.id.logout);
    }

    public void initOnClickListener() {
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, LoginFragment.newInstance())
                        .commit();
            }
        });
    }

}