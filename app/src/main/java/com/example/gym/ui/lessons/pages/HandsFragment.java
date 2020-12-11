package com.example.gym.ui.lessons.pages;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gym.LessonsAdapter;
import com.example.gym.R;

import org.json.JSONArray;

public class HandsFragment extends Fragment {

    private JSONArray hands;

    public HandsFragment(JSONArray hands){
        this.hands = hands;
        System.out.println(hands);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hands, container, false);

        Toast.makeText(this.getContext(), String.valueOf(hands.length()), Toast.LENGTH_SHORT).show();

        RecyclerView recyclerView = view.findViewById(R.id.hands_recycler);
        recyclerView.setAdapter(new LessonsAdapter(hands, this.getContext()));
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }
}