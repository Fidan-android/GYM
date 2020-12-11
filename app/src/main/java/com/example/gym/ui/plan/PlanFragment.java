package com.example.gym.ui.plan;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gym.HomeActivity;
import com.example.gym.R;
import com.example.gym.RecyclerAdapter;

import java.util.ArrayList;

public class PlanFragment extends Fragment {

    ArrayList<Integer> array_image = new ArrayList<Integer>();
    ArrayList<Integer> textViews = new ArrayList<>();

    public PlanFragment(){
        array_image.add(R.drawable.lesson1);
        array_image.add(R.drawable.lesson2);
        array_image.add(R.drawable.lesson3);
        array_image.add(R.drawable.lesson4);

        textViews.add(R.string.hands);
        textViews.add(R.string.spine);
        textViews.add(R.string.torso);
        textViews.add(R.string.legs);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //recycler -view/-adapter/layoutManager
        View view = inflater.inflate(R.layout.fragment_plan, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rvItems);
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(array_image, textViews, this.getContext(), this.getActivity());
        recyclerView.setAdapter(recyclerAdapter);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        //recycler -view/-adapter/layoutManager


        return view;
    }
}