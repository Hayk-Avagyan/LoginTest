package com.hayk.myapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
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

    private final String TAG = getClass().getSimpleName();
    private static final String TAG_LOG = "LOG_D";
    public static final String USER_KEY = "user";
    public static final String PASSWORD_KEY = "password";

    EditText emailValidate;
    EditText passwordValidate;
    Button signIn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        findItems(view);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passwordPattern = getString(R.string.passwordPattern);
                String password = passwordValidate.getText().toString().trim();
                String email = emailValidate.getText().toString().trim();
                String emailPattern = getString(R.string.emailPattern);

                if (email.matches(emailPattern) && password.matches(passwordPattern)) {
                    Toast.makeText(getActivity(), R.string.ValidEmailAddress, Toast.LENGTH_SHORT).show();
                    getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    getFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(R.animator.slide_right_enter, R.animator.slide_left_exit, R.animator.slide_left_enter, R.animator.slide_right_exit)
                            .replace(R.id.login_container, newInstance(email, password))
                            .addToBackStack(TAG)
                            .commit();

                } else {
                    Toast.makeText(getActivity(), R.string.Invalidemail, Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }

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

    public void findItems(View view) {
        emailValidate = (EditText) view.findViewById(R.id.username);
        passwordValidate = (EditText) view.findViewById(R.id.password);
        signIn = (Button) view.findViewById(R.id.sign_in);

    }
}