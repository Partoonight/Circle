package com.example.neozhang.circle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class LoginActivity extends AppCompatActivity {

    LoginButton loginButton;
    Button button;//test
    CallbackManager callbackManager;
    EventDB eventDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        eventDB = new EventDB(this);

        View view = findViewById(R.id.root_login_view);

        //test
        button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MapsActivity.class));
            }
        });


        loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setReadPermissions("user_friends");
        // Other app specific specialization

        callbackManager = CallbackManager.Factory.create();

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Log.d("", "successfully logged in");
                //transition to main activity here+++
                startActivity(new Intent(LoginActivity.this, MapsActivity.class));
            }

            @Override
            public void onCancel() {
                // App code
                Log.d("", "cancelled login");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.d("", "failed login");
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void uploadData(View view){
        eventDB.insertData(43.010517,-81.2754176, 9, 8, "Pictures with Santa", "Come to the Natural Science Center to take pictures with santa!", 0, "SSC");
        eventDB.insertData(43.010517,-81.2754176, 9, 8, "Pictures with Santa", "Come to the Natural Science Center to take pictures with santa!", 0, "SSC");
    }
}
