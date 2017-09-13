package com.hayk.myapplication;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
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

    public static final String USER = "user";
    public static final String PASSWORD = "password";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.successful_login_fragment, container, false);
        TextView successfulLogin = (TextView) view.findViewById(R.id.successful_login);
        TextView successfulPassword = (TextView) view.findViewById(R.id.successful_password);
        Button logoutButton = (Button) view.findViewById(R.id.logout);

        Bundle bundle = getArguments();
        String user = bundle.getString(USER);
        String password = bundle.getString(PASSWORD);

        successfulLogin.setText(user);
        successfulPassword.setText(password);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

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

}