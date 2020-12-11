package com.example.gym;

import android.os.AsyncTask;

import org.json.JSONArray;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RequestGetLessons extends AsyncTask<JSONArray, JSONArray, JSONArray> {

    private String url = "http://gym.areas.su/";

    public RequestGetLessons(){
        url += "lessons";
    }

    @Override
    protected JSONArray doInBackground(JSONArray... jsonArrays) {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", "Mozilla/5.0")
                .addHeader("Content-Type", "application/json")
                .build();

        Call call = new OkHttpClient().newCall(request);
        try {

            Response response = call.execute();
            return new JSONArray(response.body().string());
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
