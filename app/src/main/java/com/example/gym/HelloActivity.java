package com.example.gym;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

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
    private TextInputLayout eWeight;
    private TextInputLayout eHeight;
    private EditText weight;
    private EditText height;

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
        eWeight = findViewById(R.id.eWeight);
        eHeight = findViewById(R.id.eHeight);
        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
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
    }

    public void fifthStep(View view){
        fourth.setVisibility(View.INVISIBLE);
        fifth.setVisibility(View.VISIBLE);
    }

    public void endStep(View view){
        if (!(weight.getText().toString().equals("")) && Double.parseDouble(weight.getText().toString()) > 0){
            double w = Double.parseDouble(weight.getText().toString());
            if (!(height.getText().toString().equals("")) && Double.parseDouble(height.getText().toString()) > 0){
                double h = Double.parseDouble(height.getText().toString());
                eHeight.setError(null);
                eWeight.setError(null);
                setFirstStart(w, h);
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        startActivity(new Intent(HelloActivity.this, MainActivity.class));
                        finish();
                    }
                }, 500);
            } else {
                eHeight.setError("Неверное значение");
            }
        } else {
            eWeight.setError("Неверное значение");
        }
    }

    public void setFirstStart(double w, double h){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();

        ed.putBoolean("first_start", true);
        ed.putFloat("weight", Float.parseFloat(String.valueOf(w)));
        ed.putFloat("height", Float.parseFloat(String.valueOf(h)));
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