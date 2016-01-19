package com.example.manny.facebookapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Arrays;
import java.util.Random;


/**
 * Created by manny on 12/31/15.
 */


public class AnActivity extends Activity {

    CharSequence text;
    private ImageButton button2;
    private ImageButton button1;
    private Button bback;
    private Button fbbutton;
    public static CallbackManager callbackmanager;
    // Creating Facebook CallbackManager Value

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.an_activity);

        text = "";

        button1 = (ImageButton) this.findViewById(R.id.img1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random randomnum1 = new Random();
                int numToast = randomnum1.nextInt(5);
                numToast++;

                switch (numToast) {
                    case 1:
                        text = getString(R.string.aanswer1);
                        break;

                    case 2:
                        text = getString(R.string.aanswer2);
                        break;

                    case 3:
                        text = getString(R.string.aanswer3);
                        break;

                    case 4:
                        text = getString(R.string.aanswer4);
                        break;

                    case 5:
                        text = getString(R.string.aanswer5);
                        break;
                }

                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });


        button2 = (ImageButton) this.findViewById(R.id.img2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random randomnum = new Random();
                int numToast = randomnum.nextInt(6);
                numToast++;

                switch (numToast) {
                    case 1:
                        text = getString(R.string.answer1);
                        break;

                    case 2:
                        text = getString(R.string.answer2);
                        break;

                    case 3:
                        text = getString(R.string.answer3);
                        break;

                    case 4:
                        text = getString(R.string.answer4);
                        break;

                    case 5:
                        text = getString(R.string.answer5);
                        break;

                    case 6:
                        text = getString(R.string.answer6);
                        break;
                }

                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });


        // Initialize layout button
            fbbutton = (Button) findViewById(R.id.btnb);
            fbbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Call private method
                    onFblogin();
                }
            });

        bback = (Button) this.findViewById(R.id.btna);
        bback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


// Private method to handle Facebook login and callback
        private void onFblogin() {
            callbackmanager = CallbackManager.Factory.create();

            // Set permissions
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "user_photos", "public_profile"));
            LoginManager.getInstance().registerCallback(callbackmanager,
                    new FacebookCallback<LoginResult>() {
                        @Override
                        public void onSuccess(LoginResult loginResult) {

                            System.out.println("Success");
                            GraphRequest.newMeRequest(
                                    loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                                        @Override
                                        public void onCompleted(JSONObject json, GraphResponse response) {
                                            if (response.getError() != null) {
                                                // handle error
                                                System.out.println("ERROR");
                                            } else {
                                                System.out.println("Success");
                                                try {

                                                    String jsonresult = String.valueOf(json);
                                                    System.out.println("JSON Result"+jsonresult);
                                                    String str_email = json.getString("email");
                                                    String str_id = json.getString("id");
                                                    String str_firstname = json.getString("first_name");
                                                    String str_lastname = json.getString("last_name");

                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
                                    }).executeAsync();
                        }

                        @Override
                        public void onCancel() {
                            Log.d("string","On cancel");
                        }

                        @Override
                        public void onError(FacebookException error) {
                            Log.d("stringa", error.toString());
                        }
                    });
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            callbackmanager.onActivityResult(requestCode, resultCode, data);
        }
    }