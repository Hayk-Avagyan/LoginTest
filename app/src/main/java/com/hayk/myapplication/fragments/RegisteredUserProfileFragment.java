package com.hayk.myapplication.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hayk.myapplication.R;
import com.hayk.myapplication.adapters.RegistrationTimeAdapter;
import com.hayk.myapplication.controllers.LoginInfoController;
import com.hayk.myapplication.model.UserInfoTimeStamp;

/**
 * Created by User on 20.09.2017.
 */

public class RegisteredUserProfileFragment extends Fragment {

    private static final String TAG_LOG = "LOG_D";
    private static final String USER_EMAIL = "EMAIL";

    private TextView profileFirstNameView;
    private TextView profileLastNameView;
    private TextView profileAgeView;
    private TextView userEmailView;

    public static RegisteredUserProfileFragment newInstance(String email) {
        Log.d(TAG_LOG, "newInstance: " + email);
        RegisteredUserProfileFragment userProfileFragment = new RegisteredUserProfileFragment();

        Bundle args = new Bundle();
        args.putString(USER_EMAIL, email);
        userProfileFragment.setArguments(args);

        return userProfileFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.registered_user_profile_fragment, container, false);
        initViews(view);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_time_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        Bundle bundle = getArguments();
        String key = bundle.getString(USER_EMAIL);

        UserInfoTimeStamp userInfoTimeStamp = LoginInfoController.getInstance().getUserInfoByEmail(getActivity(), key);

        RegistrationTimeAdapter adapter = new RegistrationTimeAdapter(userInfoTimeStamp.getRegistrationTimeList());
        recyclerView.setAdapter(adapter);

        profileFirstNameView.setText(userInfoTimeStamp.getUserFirstName());
        profileLastNameView.setText(userInfoTimeStamp.getUserLastName());
        profileAgeView.setText(userInfoTimeStamp.getUserAge());
        userEmailView.setText(userInfoTimeStamp.getRegisteredEmail());

        return view;
    }

    public void initViews(View view) {
        profileFirstNameView = (TextView) view.findViewById(R.id.profile_first_name);
        profileLastNameView = (TextView) view.findViewById(R.id.profile_last_name);
        profileAgeView = (TextView) view.findViewById(R.id.profile_age);
        userEmailView = (TextView) view.findViewById(R.id.registered_user_email);

    }

}
