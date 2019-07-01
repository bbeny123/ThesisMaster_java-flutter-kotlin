package org.kamwas.android_test;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;
import org.kamwas.android_test.R;
import org.kamwas.android_test.helper.User;
import org.kamwas.android_test.helper.Utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.lang.System.nanoTime;

public class RestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button getButton  = findViewById(R.id.getButton);
        Button postButton  = findViewById(R.id.postButton);
        TextView getResult  = findViewById(R.id.getResult);
        TextView postResult  = findViewById(R.id.postResult);

        getButton.setOnClickListener(b -> {
            Utils.start(getResult);
            AsyncTask.execute(() -> get(getResult));
        });

        postButton.setOnClickListener(b -> {
            Utils.start(postResult);
            AsyncTask.execute(() -> post(postResult));
        });
    }

    public void get(TextView textView) {
        long result = 0L;
        long timer = 0L;
        for (int i = 0; i < 1000; i++) {
            User user = new User();
            timer = nanoTime();
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
                    connection.disconnect();
                    result += nanoTime() - timer;
                    Log.i("RestActivity", "GET result: " + user.toString());
                }
            } catch (Exception ex) {
                Log.d("RestActivity", "GET error", ex);
                break;
            }
        }
        Utils.setResult(textView, result, 1000);
    }

    public void post(TextView textView) {
        long result = 0L;
        long timer = 0L;
        for (int i = 0; i < 1000; i++) {
            User user = new User(1L, "user", "user@user", "user", 30);
            timer = nanoTime();
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
                    connection.disconnect();
                    result += nanoTime() - timer;
                    Log.i("RestActivity", "POST success");
                }
            } catch (Exception ex) {
                Log.d("RestActivity", "POST error", ex);
            }
        }
        Utils.setResult(textView, result, 1000);
    }

}
