package org.kamwas.android_test;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class RESTPerformance {

    public void getAsync() {
        AsyncTask.execute(this::get);
    }

    public void get() {
        User user = new User();
        try {
            URL url = new URL("http://10.0.2.2:8080");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if (connection.getResponseCode() == 200) {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();

                String line;
                while ((line = buffer.readLine()) != null) {
                    sb.append(line);
                }
                JSONObject json = new JSONObject(sb.toString());
                user.setId(json.getLong("id"));
                user.setLogin(json.getString("login"));
                user.setEmail(json.getString("email"));
                user.setName(json.getString("name"));
                user.setAge(json.getInt("age"));
            }
            connection.disconnect();
        } catch (Exception ex) {
            Log.d("RESTPerformance", ex.getMessage());
        }
        Log.i("RESTPerformance", "GET result: " + user.toString());
    }

    public void postAsync() {
        AsyncTask.execute(this::post);
    }

    public void post() {
        User user = new User(1L, "user", "user@user", "user", 30);
        try {
            URL url = new URL("http://10.0.2.2:8080");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", user.getId());
            jsonObject.put("login", user.getLogin());
            jsonObject.put("email", user.getEmail());
            jsonObject.put("name", user.getName());
            jsonObject.put("age", user.getAge());
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(jsonObject.toString());
            out.flush();
            out.close();
            if (connection.getResponseCode() == 200) {
                Log.i("RESTPerformance", "POST success");
            }
            connection.disconnect();
        } catch (Exception ex) {
            Log.d("RESTPerformance", ex.getMessage());
        }
    }


}
