package com.example.gym;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

public class RequestsSender extends AsyncTask<String, String, String> {

    private JSONObject registerObject;

    private String METHOD = "GET";

    //Registration constructor
    public RequestsSender(String method, String username, String email, String password, double weight, double height) {
        this.METHOD = method;
        try {
            registerObject.put("username", username);
            registerObject.put("email", email);
            registerObject.put("password", password);
            registerObject.put("weight", weight);
            registerObject.put("height", height);
        } catch (JSONException ex){
            ex.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        return "TODO";
    }
}
