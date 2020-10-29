package com.example.gym;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

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
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), registerObject.toString());
        System.out.println(registerObject.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("User-Agent", "Mozilla/5.0")
                .addHeader("Content-Type", "application/json")
                .build();

        System.out.println(request.url());
        System.out.println(request.body());
        Call call = new OkHttpClient().newCall(request);

        try {
            Response response = call.execute();
            assert response.body() != null;
            String jsonString = response.body().string();
            JSONObject responseJSON = new JSONObject(jsonString);
            if (type.equals("reg") || type.equals("out")){
                return responseJSON.getString("notice");
            } else {
                return responseJSON.getString("notice");
            }
        } catch (IOException | JSONException ex) {
            return ex.toString();
        }
        /*try {
            URL url = new URL(this.url);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            try(OutputStream os = con.getOutputStream()) {
                byte[] input = registerObject.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                return response.toString();
            }
        } catch (IOException e) {
            return e.toString();
        }*/
    }
}
