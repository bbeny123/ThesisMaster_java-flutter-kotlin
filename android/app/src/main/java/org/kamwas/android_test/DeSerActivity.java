package org.kamwas.android_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;

import org.kamwas.android_test.helper.User;
import org.kamwas.android_test.helper.Util;

import static java.lang.System.nanoTime;

public class DeSerActivity extends AppCompatActivity {

    private static final double times = 10000;
    private long timer = 0L;
    
    private static final Gson gson = new Gson();
    private static final User user = new User(1L, "user", "user@user", "user", 30);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deser);
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Util.benchmarkListener(findViewById(R.id.serializationButton), findViewById(R.id.serializationResult), this::serialize);
        Util.benchmarkListener(findViewById(R.id.deserializationButton), findViewById(R.id.deserializationResult), this::deserialize);
    }

    public double serialize() {
        long result = 0L;
        String dummy = null;

        for (int i = 0; i < times; i++) {
            timer = nanoTime();
            dummy = serialize(user);
            result += nanoTime() - timer;
        }

        Log.i("DeSerActivity", "Serialization benchmark finished: " + dummy);
        return result / times;
    }

    public double deserialize() {
        long result = 0L;
        long dummy = 0L;

        String json = serialize(user);
        for (int i = 0; i < times; i++) {
            timer = nanoTime();
            dummy = deserialize(json).getId();
            result += nanoTime() - timer;
        }

        Log.i("DeSerActivity", "Deserialization dummy result: " + dummy);
        return result / times;
    }

    private String serialize(User user) {
        return gson.toJson(user);
    }

    private User deserialize(String user) {
        return gson.fromJson(user, User.class);
    }

}

