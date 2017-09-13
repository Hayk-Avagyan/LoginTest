package com.hayk.myapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by User on 12.09.2017.
 */

public class LoginFragment extends Fragment {

    public static final String USER = "user";
    public static final String PASSWORD = "password";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        final EditText emailValidate = (EditText) view.findViewById(R.id.username);
        final EditText passwordValidate = (EditText) view.findViewById(R.id.password);
        Button signIn = (Button) view.findViewById(R.id.sign_in);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String passwordPattern = getString(R.string.passwordPattern);
                String password = passwordValidate.getText().toString().trim();
                String email = emailValidate.getText().toString().trim();
                String emailPattern = getString(R.string.emailPattern);

                if (email.matches(emailPattern) && password.matches(passwordPattern)) {
                    Toast.makeText(getActivity(), R.string.ValidEmailAddress, Toast.LENGTH_SHORT).show();

                    String username = emailValidate.getText().toString();
                    String passwordText = passwordValidate.getText().toString();

                    Bundle bundle = new Bundle();
                    bundle.putString(USER, username);
                    bundle.putString(PASSWORD, passwordText);
                    SuccessfulLoginFragment successfulLoginFragment = new SuccessfulLoginFragment();
                    successfulLoginFragment.setArguments(bundle);

                    getFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(R.animator.slide_left_enter, R.animator.slide_right_exit, R.animator.slide_right_enter, R.animator.slide_left_exit)
                            .replace(R.id.login_container, successfulLoginFragment)
                            .addToBackStack(null)
                            .commit();

                } else {
                    Toast.makeText(getActivity(), R.string.Invalidemail, Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }
}