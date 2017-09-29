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
import android.widget.EditText;
import android.widget.Toast;

import com.hayk.myapplication.R;
import com.hayk.myapplication.controllers.LoginInfoController;
import com.hayk.myapplication.model.UserInfoTimeStamp;

/**
 * Created by User on 11.09.2017.
 */

public class RegistrationFormFragment extends Fragment {

    private static final String TAG_LOG = "LOG_D";
    private static final String USER_KEY = "email";

    private EditText firstNameView;
    private EditText lastNameView;
    private EditText userAgeView;
    private Button logoutButton;
    private Button saveButton;

    public static RegistrationFormFragment newInstance(String email) {
        Log.d(TAG_LOG, "newInstance: " + email);
        RegistrationFormFragment registrationFormFragment = new RegistrationFormFragment();

        if (email != null) {
            Bundle args = new Bundle();
            args.putString(USER_KEY, email);
            registrationFormFragment.setArguments(args);
        }
        return registrationFormFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.registration_form_fragment, container, false);

        initViews(view);
        initOnClickListener();

        Bundle bundle = getArguments();
        String email = bundle.getString(USER_KEY);

        UserInfoTimeStamp userInfoTimeStamp = LoginInfoController.getInstance().getUserProfile(getActivity(), email);
        if (userInfoTimeStamp != null) {
            firstNameView.setText(userInfoTimeStamp.getUserFirstName());
            lastNameView.setText(userInfoTimeStamp.getUserLastName());
            userAgeView.setText(userInfoTimeStamp.getUserAge());
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
        firstNameView = (EditText) view.findViewById(R.id.first_name);
        lastNameView = (EditText) view.findViewById(R.id.last_name);
        userAgeView = (EditText) view.findViewById(R.id.user_age);
        logoutButton = (Button) view.findViewById(R.id.logout);
        saveButton = (Button) view.findViewById(R.id.save);
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

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstName = firstNameView.getText().toString().trim();
                String lastName = lastNameView.getText().toString().trim();
                String userAge = userAgeView.getText().toString().trim();

                if (firstName.isEmpty() || lastName.isEmpty() || userAge.isEmpty()) {

                    Toast.makeText(getActivity(), R.string.empty_profile_field, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), R.string.save_profile_changes, Toast.LENGTH_SHORT).show();

                    Bundle bundle = getArguments();
                    String email = bundle.getString(USER_KEY);

                    LoginInfoController.getInstance().addUserProfile(firstName, lastName, userAge, email, getActivity());
                }

            }
        });
    }

}