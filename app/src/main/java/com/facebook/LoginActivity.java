package com.facebook;

import android.app.PendingIntent;
import android.content.pm.PackageInstaller.Session;
import android.app.Activity;
import android.content.Intent;
import android.net.sip.SipManager;
import android.net.sip.SipProfile;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.manny.facebookapp.Login;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.example.manny.facebookapp.FbLogin;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * Created by manny on 1/14/16.
 */
public class LoginActivity extends Activity {

    /**
     * Created by manny on 1/4/16.
     */
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

        Session.openActiveSession(this, true, new Session.StatusCallback() {
        // callback when session changes state
        @Override
        public void call(Session session, SessionState state, Exception exception) {
        }
        });

    public SipManager mSipManager = null;
            if(mSipManager == null) {
        mSipManager = SipManager.newInstance(this);
    }

    public SipProfile mSipProfile = null;
     SipProfile.Builder builder = new SipProfile.Builder(username, domain);
    builder.setPassword(password);
    mSipProfile = builder.build();

    Intent intent = new Intent();
    intent.setAction("android.SipDemo.INCOMING_CALL");
    PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, Intent.FILL_IN_DATA);
    mSipManager.open(mSipProfile, pendingIntent, null);

    mSipManager.setRegistrationListener(mSipProfile.getUriString(), new SipRegistrationListener() {

        public void onRegistering(String localProfileUri) {
            updateStatus("Registering with SIP Server...");
        }

    public void onRegistrationDone(String localProfileUri, long expiryTime) {
        updateStatus("Ready");
    }

    public void onRegistrationFailed(String localProfileUri, int errorCode, String errorMessage) {
        updateStatus("Registration failed.  Please check settings.");
    }

    public void closeLocalProfile() {
        if (mSipManager == null) {
            return;
        }
        try {
            if (mSipProfile != null) {
                mSipManager.close(mSipProfile.getUriString());
            }
        } catch (Exception ee) {
            Log.d("WalkieTalkieActivity/onDestroy", "Failed to close local profile.", ee);
        }
    }

}