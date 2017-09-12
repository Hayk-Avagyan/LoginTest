package com.hayk.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginFragment extends FragmentActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button signIn = (Button) findViewById(R.id.sign_in);
        final EditText emailValidate = (EditText) findViewById(R.id.username);
        final EditText passwordValidate = (EditText) findViewById(R.id.password);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String passwordPattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
                String pass = passwordValidate.getText().toString().trim();
                String email = emailValidate.getText().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (email.matches(emailPattern) && pass.matches(passwordPattern)) {
                    Toast.makeText(getApplicationContext(), "Valid email address", Toast.LENGTH_SHORT).show();

                    String username = emailValidate.getText().toString();
                    String password = passwordValidate.getText().toString();

                    Bundle bundle = new Bundle();
                    bundle.putString("user", username);
                    bundle.putString("pass", password);
                    SuccessfulLoginFragment successfulLoginFragment = new SuccessfulLoginFragment();
                    successfulLoginFragment.setArguments(bundle);

                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, successfulLoginFragment)
                            .addToBackStack(null)
                            .commit();

                } else {
                    Toast.makeText(getApplicationContext(), "Invalid email address or password", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}