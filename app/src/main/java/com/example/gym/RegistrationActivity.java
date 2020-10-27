package com.example.gym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class RegistrationActivity extends AppCompatActivity {

    TextView back;
    EditText login;
    EditText email;
    EditText psw;
    EditText doublePsw;
    double w;
    double h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        back = (TextView) findViewById(R.id.back);
        SpannableString content = new SpannableString(back.getText());
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        back.setText(content);

        login = findViewById(R.id.login);
        email = findViewById(R.id.email);
        psw = findViewById(R.id.password);
        doublePsw = findViewById(R.id.doublePassword);

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        w = Double.parseDouble(String.valueOf(sharedPreferences.getFloat("weight", 0)));
        h = Double.parseDouble(String.valueOf(sharedPreferences.getFloat("height", 0)));
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

    public void goBack(View view){
        startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
        finish();
    }

    public void regUp(View view){
        if (psw.getText().toString().equals(doublePsw.getText().toString())){
            RequestsSender requestsSender = new RequestsSender(login.getText().toString(),
                    email.getText().toString(), psw.getText().toString(), w, h);
            requestsSender.execute();
            try {
                String response = requestsSender.get();
                System.out.println(response);
                Toast.makeText(this, response, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                finish();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}