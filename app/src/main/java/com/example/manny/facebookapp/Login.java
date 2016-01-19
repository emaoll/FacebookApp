package com.example.manny.facebookapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * Created by manny on 1/4/16.
 */
public class Login extends Activity {

    private Button baack;
    Button bbuuttoonn;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button baack = (Button) findViewById(R.id.bbtna);
        Button loginButton = (Button) findViewById(R.id.login_button);

        baack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
                  @Override
                public void onClick(View view) {
                startActivity(new Intent(Login.this, FbLogin.class));
                  }
            });
    }

           @Override
           public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
           View view = inflater.inflate(R.layout.fb_login, container, false);

            loginButton = (LoginButton) view.findViewById(R.id.login_button);
            loginButton.setReadPermissions("user_friends");
            // If using in a fragment
            loginButton.setFragment(this);
            // Other app specific specialization
            // Callback registration
            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
    }
}
