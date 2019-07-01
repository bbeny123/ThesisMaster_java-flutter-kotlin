package org.kamwas.android_test;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import org.kamwas.android_test.helper.User;
import org.kamwas.android_test.helper.Utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static java.lang.System.nanoTime;
public class SerializationDeserializationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serialization_deserialization);
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button serializationStart  = findViewById(R.id.serializationStart);
        TextView serializationResult  = findViewById(R.id.serializationResult);
        TextView deserializationResult  = findViewById(R.id.deserializationResult);

        serializationStart.setOnClickListener(b -> {
            Utils.start(serializationResult);
            Utils.start(deserializationResult);
            AsyncTask.execute(() -> benchmark(serializationResult, deserializationResult));
        });

    }

    public void benchmark(TextView sTextView, TextView dTextView) {
        User user = new User(1L, "user", "user@user", "user", 30);
        byte[] serialized;
        long serializationResult = 0L;
        long deserializationResult = 0L;
        long timer = 0L;

        for (int i = 0; i < 1000; i++) {
            try {
                timer = nanoTime();
                serialized = serialize(user);
                serializationResult = nanoTime() - timer;
                timer = nanoTime();
                user = deserialize(serialized);
                deserializationResult = nanoTime() - timer;
            } catch (Exception ex) {
                Log.d("SerializationDeserializationActivity", "Error", ex);
            }

        }
        Log.i("SerializationDeserializationActivity", "Benchmark result: " + user.toString());
        Utils.setResult(sTextView, serializationResult, 1000);
        Utils.setResult(dTextView, deserializationResult, 1000);
    }

    public byte[] serialize(User user) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(user);
        out.close();
        return bos.toByteArray();
    }

    public User deserialize(byte[] user) throws Exception {
        ByteArrayInputStream bis = new ByteArrayInputStream(user);
        ObjectInputStream in = new ObjectInputStream(bis);
        return (User) in.readObject();
    }

}

