package com.example.gym.ui.lessons;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gym.R;
import com.example.gym.RequestGetLessons;
import com.example.gym.SliderPages;
import com.example.gym.ui.lessons.pages.HandsFragment;
import com.example.gym.ui.lessons.pages.LegsFragment;
import com.example.gym.ui.lessons.pages.SpineFragment;
import com.example.gym.ui.lessons.pages.TorsoFragment;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class LessonsFragment extends Fragment {

    private ViewPager viewPager;
    private SliderPages sliderPages;
    private JSONArray lessons;

    public LessonsFragment(JSONArray lessons){
        this.lessons = lessons;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lessons, container, false);

        //Initialize lesson-arrays
        final JSONArray hands = new JSONArray();
        final JSONArray spine = new JSONArray();
        final JSONArray torso = new JSONArray();
        final JSONArray legs = new JSONArray();
        for (int i = 0; i < lessons.length(); i++){
            try {
                JSONObject jsonObject = lessons.getJSONObject(i);
                switch (jsonObject.getString("category")) {
                    case "hands":
                        hands.put(jsonObject);
                        break;
                    case "spine":
                        spine.put(jsonObject);
                        break;
                    case "torso":
                        torso.put(jsonObject);
                        break;
                    case "legs":
                        legs.put(jsonObject);
                        break;
                }
            } catch (JSONException ex){
                ex.printStackTrace();
            }
        }


        ArrayList<Fragment> arrayList = new ArrayList<>();
        System.out.println(hands);
        arrayList.add(new HandsFragment(hands));
        arrayList.add(new SpineFragment());
        arrayList.add(new TorsoFragment());
        arrayList.add(new LegsFragment());

        ArrayList<String> tabs = new ArrayList<>();
        tabs.add("Hands");
        tabs.add("Spine");
        tabs.add("Torso");
        tabs.add("Legs");

        sliderPages = new SliderPages(getFragmentManager(), arrayList, tabs);
        viewPager = view.findViewById(R.id.view_pager);
        viewPager.setAdapter(sliderPages);

        //TabLayout
        TabLayout tabLayout = view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}