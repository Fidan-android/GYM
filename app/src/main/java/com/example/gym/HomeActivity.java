package com.example.gym;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    ArrayList<ImageView> imageViews = new ArrayList<>();
    ArrayList<TextView> textViews = new ArrayList<>();

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imageViews.add((ImageView) findViewById((R.drawable.lesson1)));
        imageViews.add((ImageView) findViewById((R.drawable.lesson2)));
        imageViews.add((ImageView) findViewById((R.drawable.lesson3)));
        imageViews.add((ImageView) findViewById((R.drawable.lesson4)));

        textViews.add((TextView) findViewById((R.string.hands)));
        textViews.add((TextView) findViewById((R.string.spine)));
        textViews.add((TextView) findViewById((R.string.torso)));
        textViews.add((TextView) findViewById((R.string.legs)));

        BottomNavigationView btw = findViewById(R.id.bottom_nav_bar);
        btw.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.plan:
                        return true;
                    case R.id.lessons:

                        return true;
                    case R.id.reports:

                        return true;
                    case R.id.profile:

                        return true;
                    default:
                        return true;
                }
            }
        });

        btw.setSelectedItemId(R.id.plan);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(imageViews, textViews, this, HomeActivity.this);
        RecyclerView recyclerView = findViewById(R.id.rvItems);
        recyclerView.setAdapter(recyclerAdapter);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
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