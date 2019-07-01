package org.kamwas.android_test;

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
        Button serializationButton  = findViewById(R.id.serializationButton);
        Button deserializationButton  = findViewById(R.id.deserializationButton);
        TextView serializationResult  = findViewById(R.id.serializationResult);
        TextView deserializationResult  = findViewById(R.id.deserializationResult);

        serializationStart.setOnClickListener(b -> {
            Utils.start(serializationResult);
            Utils.start(deserializationResult);
            serialize(serializationResult);
            deserialize(deserializationResult);
        });

        serializationButton.setOnClickListener(b -> {
            Utils.start(serializationResult);
            serialize(serializationResult);
        });

        deserializationButton.setOnClickListener(b -> {
            Utils.start(deserializationResult);
            deserialize(deserializationResult);
        });

    }

    public void serialize(TextView textView) {
        User user = new User(1L, "user", "user@user", "user", 30);
        byte[] serialized = null;
        long result = 0L;
        long timer;

        for (int i = 0; i < 1000; i++) {
            timer = nanoTime();
            serialized = serialize(user);
            result += nanoTime() - timer;
        }

        Log.i("SerializationDeserializationActivity", "Serialization benchmark finished");
        Utils.setResult(textView, result, 1000);
    }

    public void deserialize(TextView textView) {
        byte[] serialized = null;
        serialized = serialize(new User(1L, "user", "user@user", "user", 30));

        User user = null;
        long result = 0L;
        long timer = 0L;

        for (int i = 0; i < 1000; i++) {
            timer = nanoTime();
            user = deserialize(serialized);
            result += nanoTime() - timer;
        }

        Log.i("SerializationDeserializationActivity", "Serialization result: " + user.toString());
        Utils.setResult(textView, result, 1000);
    }

    public byte[] serialize(User user) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(user);
        } catch (Exception ex) {
            Log.d("SerializationDeserializationActivity", "Serialization Error", ex);
        }
        return bos.toByteArray();
    }

    public User deserialize(byte[] user) {
        User result = null;
        ByteArrayInputStream bis = new ByteArrayInputStream(user);
        try (ObjectInputStream in = new ObjectInputStream(bis)) {
            result = (User) in.readObject();
        } catch (Exception ex) {
            Log.d("SerializationDeserializationActivity", "Deserialization Error", ex);
        }
        return result;
    }

}

