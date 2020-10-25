package com.example.gym;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreenActivity extends AppCompatActivity {

    String first_start = "first_start";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        EasySplashScreen easySplashScreen = new EasySplashScreen(this)
                .withSplashTimeOut(5000)
                .withLogo(R.drawable.gym_logo)
                .withFullScreen()
                .withBackgroundResource(R.drawable.splash_screen)
                .withTargetActivity(HelloActivity.class);

        View splashScreen = easySplashScreen.create();
        setContentView(splashScreen);
    }

    public boolean getFirstStart(){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        return sharedPreferences.getBoolean(first_start, false);
    }
}