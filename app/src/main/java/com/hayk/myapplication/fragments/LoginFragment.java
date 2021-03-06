package com.hayk.myapplication.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hayk.myapplication.R;
import com.hayk.myapplication.controllers.LoginInfoController;

/**
 * Created by User on 12.09.2017.
 */

public class LoginFragment extends Fragment {

    private final String TAG = getClass().getSimpleName();

    private EditText emailView;
    private EditText passwordView;
    private Button signIn;
    private Button loginInfo;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        initViews(view);
        initOnClickListener();

        return view;
    }

    public void initViews(View view) {
        emailView = (EditText) view.findViewById(R.id.username);
        passwordView = (EditText) view.findViewById(R.id.password);
        signIn = (Button) view.findViewById(R.id.sign_in);
        loginInfo = (Button) view.findViewById(R.id.registered_data);
    }

    public void initOnClickListener() {
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passwordPattern = getString(R.string.passwordPattern);
                String password = passwordView.getText().toString().trim();
                String emailAddress = emailView.getText().toString().trim();
                String emailPattern = getString(R.string.emailPattern);

                if (emailAddress.matches(emailPattern) && password.matches(passwordPattern)) {

                    LoginInfoController.getInstance().addLoginInfo(getActivity(), emailAddress);

                    emailView.getText().clear();
                    passwordView.getText().clear();

                    getFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(R.animator.slide_right_enter, R.animator.slide_left_exit, R.animator.slide_left_enter, R.animator.slide_right_exit)
                            .replace(R.id.fragment_container, RegistrationFormFragment.newInstance(emailAddress))
                            .commit();

                } else {
                    Toast.makeText(getActivity(), R.string.invalidEmail, Toast.LENGTH_SHORT).show();
                }
            }
        });

        loginInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.animator.slide_right_enter, R.animator.slide_left_exit, R.animator.slide_left_enter, R.animator.slide_right_exit)
                        .replace(R.id.fragment_container, LoginInfoFragment.newInstance())
                        .addToBackStack(TAG)
                        .commit();
            }
        });
    }
}