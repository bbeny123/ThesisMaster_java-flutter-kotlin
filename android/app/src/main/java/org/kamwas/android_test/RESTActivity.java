package org.kamwas.android_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;

import org.kamwas.android_test.helper.User;
import org.kamwas.android_test.helper.Util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.lang.System.nanoTime;
import static java.net.HttpURLConnection.HTTP_OK;

public class RESTActivity extends AppCompatActivity {

    private static final double times = 500;
    private long timer = 0L;

    private static final String URL = "http://10.0.2.2:8080";
    private static final Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Util.benchmarkListener(findViewById(R.id.getButton), findViewById(R.id.getResult), this::get);
        Util.benchmarkListener(findViewById(R.id.postButton), findViewById(R.id.postResult), this::post);
    }

    public double get() {
        long result = 0L;
        long dummy = 0L;

        for (int i = 0; i < times; i++) {
            timer = nanoTime();
            dummy += getCall().getId();
            result += nanoTime() - timer;
        }

        Log.i("RESTActivity", "GET success: " + dummy);
        return result / times;
    }

    public double post() {
        long result = 0L;

        User user = new User(1L, "user", "user@user", "user", 30);
        for (int i = 0; i < times; i++) {
            timer = nanoTime();
            postCall(user);
            result += nanoTime() - timer;
        }

        Log.i("RESTActivity", "POST success");
        return result / times;
    }

    private User getCall() {
        String response = getResponse(getConnection(URL, false));
        return gson.fromJson(response, User.class);
    }

    private void postCall(User user) {
        sendData(getConnection(URL, true), gson.toJson(user));
    }

    private String getResponse(HttpURLConnection con) {
        if (con == null) {
            return null;
        }

        String result = null;
        try (AutoCloseable conc = con::disconnect;
             InputStreamReader in = con.getResponseCode() == HTTP_OK ? new InputStreamReader(con.getInputStream()) : null;
             BufferedReader buf = in != null ? new BufferedReader(in) : null) {

            if (buf != null) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = buf.readLine()) != null) {
                    sb.append(line);
                }
                result = sb.toString();
            }
        } catch (Exception ex) {
            Log.d("RESTActivity", "getResponse error", ex);
        }
        return result;
    }

    private void sendData(HttpURLConnection con, String json) {
        if (con == null) {
            return;
        }
        try (AutoCloseable conc = con::disconnect;
             DataOutputStream out = new DataOutputStream(con.getOutputStream())) {

            out.writeBytes(json);
            con.getResponseCode();
        } catch (Exception ex) {
            Log.d("RESTActivity", "sendData error", ex);
        }
    }

    private HttpURLConnection getConnection(String url, boolean post) {
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) new URL(url).openConnection();
            if (post) con.setRequestMethod("POST");
        } catch (Exception e) {
            Log.d("RESTActivity", "getConnection error", e);
        }

        if (con != null && post) {
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
        }
        return con;
    }

}
