package com.example.gym;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RequestsSender extends AsyncTask<String, String, String> {

    private JSONObject registerObject = new JSONObject();

    private String type;
    private String url = "http://gym.areas.su/";

    //Registration constructor
    public RequestsSender(String username, String email, String password, double weight, double height) {
        url += "signup";
        type = "reg";
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

    public RequestsSender(String username, String password){
        url += "signin";
        type = "login";
        try {
            registerObject.put("username", username);
            registerObject.put("password", password);
        } catch (JSONException ex){
            ex.printStackTrace();
        }
    }

    public RequestsSender(String username){
        url += "signout";
        type = "out";
        try {
            registerObject.put("username", username);
        } catch (JSONException ex){
            ex.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        Request request;
        if (type.equals("lessons")){
            request = new Request.Builder()
                    .url(url)
                    .addHeader("User-Agent", "Mozilla/5.0")
                    .addHeader("Content-Type", "application/json")
                    .build();
        } else{
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), registerObject.toString());
            request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .addHeader("User-Agent", "Mozilla/5.0")
                    .addHeader("Content-Type", "application/json")
                    .build();
        }

        Call call = new OkHttpClient().newCall(request);

        try {
            Response response = call.execute();
            assert response.body() != null;
            String jsonString = response.body().string();
            JSONObject responseJSON = new JSONObject(jsonString);
            if (type.equals("reg")){
                return jsonString;
            } else if (type.equals("out")){
                return new JSONObject(responseJSON.getString("notice")).getString("text");
            } else {
                return new JSONObject(responseJSON.getString("notice")).getString("token");
            }
        } catch (IOException | JSONException ex) {
            return ex.toString();
        }
    }
}
