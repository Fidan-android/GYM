package com.example.gym.ui.profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gym.MainActivity;
import com.example.gym.R;
import com.example.gym.RequestsSender;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        Button logOut = view.findViewById(R.id.logOut);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getResources().getString(R.string.DEVICE_INFO), 0);

                RequestsSender requestsSender = new RequestsSender(sharedPreferences.getString("username", ""));
                requestsSender.execute();
                try{
                    String response = requestsSender.get();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove("token");
                    editor.remove("username");
                    editor.apply();
                    startActivity(new Intent(getContext(), MainActivity.class));
                    getActivity().finish();
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        return view;
    }
}