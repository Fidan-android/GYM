package com.example.gym;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gym.ui.lessons.LessonsFragment;
import com.example.gym.ui.plan.PlanFragment;
import com.example.gym.ui.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {




    PlanFragment planFragment = new PlanFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    LessonsFragment lessonsFragment;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RequestGetLessons requestGetLessons = new RequestGetLessons();
        requestGetLessons.execute();
        try {
            JSONArray lessons = requestGetLessons.get();
            System.out.println(lessons);
            lessonsFragment = new LessonsFragment(lessons);
        } catch (Exception ex){
            ex.printStackTrace();
        }

        BottomNavigationView btw = findViewById(R.id.bottom_nav_bar);
        btw.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.plan:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, planFragment).commit();
                        return true;
                    case R.id.lessons:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, lessonsFragment).commit();
                        return true;
                    case R.id.reports:

                        return true;
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, profileFragment).commit();
                        return true;
                    default:
                        return true;
                }
            }
        });

        btw.setSelectedItemId(R.id.plan);
    }

    private long back_pressed;
    @Override
    public void onBackPressed() {
        if (back_pressed + 1000 > System.currentTimeMillis()){
            finish();
        } else {
            Toast.makeText(this, "Press once again to exit!", Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }
}