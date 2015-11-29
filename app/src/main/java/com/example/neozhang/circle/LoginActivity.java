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
        uploadData();
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

    public void uploadData(){
        eventDB.insertData(43.010517,-81.2754176, 9, 8, "Pictures with Santa", "Come to the Natural Science Center to take pictures with santa!", 0, "SSC");
        eventDB.insertData(42.9942735,-81.2548803, 17, 5, "Aroma Grand Opening!", "Amazing food, excellent company, and a career-changing lesson on Dining Etiquette by Jana Seijts, how can the night get better? Answer - it can't.", 0, "Aroma Restaurant");
        eventDB.insertData(42.0089911,-81.279889, 17, 3, "Rwanda Recovering?", "Come join the Huron Political Science Club for our inaugural event! Dr. Scorgie-Porter will be giving a talk on the politics of Rwanda, drawing on her experience at a conference in Rwanda that she recently attended.", 0, "Cat Phelphs");
        eventDB.insertData(43.009601,-81.2759223, 10, 6, "Rotaract Holiday Polaroid Booth", "All proceeds from the event will go to our Juvenille Diabetes Camp. JDC is a weekend camp for families with young kids diagnosed with juvenile diabetes. The camp is free of charge for these families and all proceeds from this event will help fund the initiative!", 0, "UWO Rotaract");
    }
}
