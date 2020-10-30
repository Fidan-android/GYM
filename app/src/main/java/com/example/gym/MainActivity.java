package com.example.gym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TextView sign_up;
    EditText login;
    EditText psw;
    TextView errorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sign_up = (TextView) findViewById(R.id.sign_up_text);
        SpannableString content = new SpannableString(sign_up.getText());
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        sign_up.setText(content);

        login = findViewById(R.id.login);
        psw = findViewById(R.id.password);
        errorLayout = findViewById(R.id.errorLayout);
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

    public void signUp(View view){
        startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
        finish();
    }

    public void signIn(View view){
        String username = login.getText().toString();
        String password = psw.getText().toString();
        RequestsSender requestsSender = new RequestsSender(username, password);
        requestsSender.execute();
        try {
            String response = requestsSender.get();
            System.out.println(response);
            Toast.makeText(this, response, Toast.LENGTH_SHORT).show();
            if (!response.equals("User is active")) {
                SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.DEVICE_INFO), 0);
                SharedPreferences.Editor ed = sharedPreferences.edit();
                ed.putLong("token", Long.parseLong(response));
                ed.apply();
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                finish();
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}