package com.example.gym;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreenActivity extends AppCompatActivity {

    String first_start = "first_start";
    private static int time_splash = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //splash
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isFirstStart()){
                    startActivity(new Intent(SplashScreenActivity.this, HelloActivity.class));
                    finish();
                } else {
                    if (!isToken()){
                        startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                        finish();
                    } else {
                        startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
                        finish();
                    }
                }
            }
        }, time_splash);
        //splash

        /*EasySplashScreen easySplashScreen = new EasySplashScreen(this)
                .withSplashTimeOut(5000)
                .withLogo(R.drawable.gym_logo)
                .withFullScreen()
                .withBackgroundResource(R.drawable.splash_screen)
                .withTargetActivity(HelloActivity.class);

        View splashScreen = easySplashScreen.create();
        setContentView(splashScreen);*/
    }

    public boolean isFirstStart(){
        SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.DEVICE_INFO), 0);
        return sharedPreferences.getBoolean(first_start, false);
    }

    public boolean isToken(){
        SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.DEVICE_INFO), 0);
        return sharedPreferences.getLong("token", 0) != 0;
    }
}