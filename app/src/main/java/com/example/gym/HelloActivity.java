package com.example.gym;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class HelloActivity extends AppCompatActivity {

    private String personMale = "";
    RelativeLayout first;
    RelativeLayout second;
    RelativeLayout third_female;
    RelativeLayout third_male;
    RelativeLayout fourth;
    RelativeLayout fifth;
    Button newbie;
    Button keep_on;
    Button advanced;
    Button nextButton;
    Button nextLastStep;
    ArrayList<Button> buttons;
    private String ofthen = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        first = findViewById(R.id.step_first);
        second = findViewById(R.id.step_second);
        nextButton = findViewById(R.id.next_step);
        third_female = findViewById(R.id.step_third_female);
        third_male = findViewById(R.id.step_third_male);
        fourth = findViewById(R.id.step_fourth);
        fifth = findViewById(R.id.step_fifth);
        newbie = (Button) findViewById(R.id.newbie);
        keep_on = (Button) findViewById(R.id.keep_on);
        advanced = (Button) findViewById(R.id.advanced);
        nextLastStep = findViewById(R.id.next_last_step);
    }

    public void secondStep(View view){
        first.setVisibility(View.INVISIBLE);
        second.setVisibility(View.VISIBLE);
    }

    @SuppressLint("ResourceAsColor")
    public void changeMale(View view){
        personMale = "male";
        nextButton.setEnabled(true);
        nextButton.setTextColor(R.color.blue69);
    }

    @SuppressLint("ResourceAsColor")
    public void changeFemale(View view){
        personMale = "female";
        nextButton.setEnabled(true);
        nextButton.setTextColor(R.color.blue69);
    }

    public void thirdStep(View view){
        switch (personMale){
            case "female":
                second.setVisibility(View.INVISIBLE);
                third_female.setVisibility(View.VISIBLE);
                break;
            case "male":
                second.setVisibility(View.INVISIBLE);
                third_male.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void afterFemaleStep(View view){
        third_female.setVisibility(View.INVISIBLE);
        fourth.setVisibility(View.VISIBLE);
    }

    public void afterMaleStep(View view){
        third_male.setVisibility(View.INVISIBLE);
        fourth.setVisibility(View.VISIBLE);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void setOften(View view){
        Button button = (Button) view;
        nextLastStep.setEnabled(true);
        ofthen = button.getTag().toString();
        /*if (Objects.equals(button.getBackground(), getDrawable(R.drawable.button_rounded))){
            button.setBackground(getDrawable(R.drawable.button_gray_rounded));
            if (!(button.getTag().equals(newbie.getTag()))){
                newbie.setBackground(getDrawable(R.drawable.button_rounded));
            }
            if (!(button.getTag().equals(keep_on.getTag()))){
                keep_on.setBackground(getDrawable(R.drawable.button_rounded));
            }
            if (!(button.getTag().equals(advanced.getTag()))){
                advanced.setBackground(getDrawable(R.drawable.button_rounded));
            }
            nextLastStep.setEnabled(true);
            ofthen = button.getTag().toString();
        } else {
            newbie.setBackground(getDrawable(R.drawable.button_rounded));
            keep_on.setBackground(getDrawable(R.drawable.button_rounded));
            advanced.setBackground(getDrawable(R.drawable.button_rounded));
            nextLastStep.setEnabled(false);
            ofthen = "";
        }*/
    }

    public void fifthStep(View view){
        fourth.setVisibility(View.INVISIBLE);
        fifth.setVisibility(View.VISIBLE);
    }

    public void endStep(View view){
        setFirstStart();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(HelloActivity.this, MainActivity.class));
                finish();
            }
        }, 500);
    }

    public void setFirstStart(){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();

        ed.putBoolean("first_start", true);
        ed.apply();
    }

    private static long back_pressed;

    @Override
    public void onBackPressed() {
        if (back_pressed + 1000 > System.currentTimeMillis()){
            finish();
        } else {
            Toast.makeText(getBaseContext(), "Press once again to exit!",
                    Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }
}