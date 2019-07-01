package org.kamwas.android_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button restButton = findViewById(R.id.restButton);
        Button deserButton = findViewById(R.id.deserButton);

        restButton.setOnClickListener(b -> goToActivity(RestActivity.class));
        deserButton.setOnClickListener(b -> goToActivity(SerializationDeserializationActivity.class));
    }

    private void goToActivity(Class<? extends AppCompatActivity> clz) {
        Intent goToNextActivity = new Intent(getApplicationContext(), clz);
        startActivity(goToNextActivity);
    }

}
