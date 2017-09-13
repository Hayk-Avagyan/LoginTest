package com.hayk.myapplication;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by User on 11.09.2017.
 */

public class SuccessfulLoginFragment extends Fragment {

    public static final String USER_KEY = "user";
    public static final String PASSWORD_KEY = "password";

    TextView successfulLogin;
    TextView successfulPassword;
    Button logoutButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.successful_login_fragment, container, false);
        findItems(view);

        Bundle bundle = getArguments();
        if (getArguments() != null) {
            String user = bundle.getString(USER_KEY);
            String password = bundle.getString(PASSWORD_KEY);
            successfulLogin.setText(user);
            successfulPassword.setText(password);
        }

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginFragment loginFragment = new LoginFragment();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.logout_container, loginFragment)
                        .commit();
            }
        });
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    public void findItems(View view) {
        successfulLogin = (TextView) view.findViewById(R.id.successful_login);
        successfulPassword = (TextView) view.findViewById(R.id.successful_password);
        logoutButton = (Button) view.findViewById(R.id.logout);
    }

}