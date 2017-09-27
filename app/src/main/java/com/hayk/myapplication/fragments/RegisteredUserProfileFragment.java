package com.hayk.myapplication.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hayk.myapplication.R;
import com.hayk.myapplication.controllers.LoginInfoController;

/**
 * Created by User on 20.09.2017.
 */

public class RegisteredUserProfileFragment extends Fragment {

    private static final String TAG_LOG = "LOG_D";
    private static final String USER_KEY = "email";

    private TextView profileFirstNameView;
    private TextView profileLastNameView;
    private TextView profileAgeView;

    public static RegisteredUserProfileFragment newInstance(String email) {
        Log.d(TAG_LOG, "newInstance: " + email);
        RegisteredUserProfileFragment userProfileFragment = new RegisteredUserProfileFragment();

        Bundle args = new Bundle();
        args.putString(USER_KEY, email);
        userProfileFragment.setArguments(args);

        return userProfileFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.registered_user_profile_fragment, container, false);
        initViews(view);

        Bundle bundle = getArguments();
        String key = bundle.getString(USER_KEY);

        profileFirstNameView.setText(LoginInfoController.getInstance().getUserProfile(getActivity(), key).getUserFirstName());
        profileLastNameView.setText(LoginInfoController.getInstance().getUserProfile(getActivity(), key).getUserLastName());
        profileAgeView.setText(LoginInfoController.getInstance().getUserProfile(getActivity(), key).getUserAge());

        return view;
    }

    public void initViews(View view) {
        profileFirstNameView = (TextView) view.findViewById(R.id.profile_first_name);
        profileLastNameView = (TextView) view.findViewById(R.id.profile_last_name);
        profileAgeView = (TextView) view.findViewById(R.id.profile_age);

    }

}
