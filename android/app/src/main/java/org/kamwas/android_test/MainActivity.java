package org.kamwas.android_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.collectionButton).setOnClickListener(b -> goToActivity(CollectionActivity.class));
        findViewById(R.id.restButton).setOnClickListener(b -> goToActivity(RestActivity.class));
        findViewById(R.id.dbButton).setOnClickListener(b -> goToActivity(DBActivity.class));
        findViewById(R.id.deserButton).setOnClickListener(b -> goToActivity(DeSerActivity.class));
        findViewById(R.id.fileButton).setOnClickListener(b -> goToActivity(FileActivity.class));
    }

    private void goToActivity(Class<? extends AppCompatActivity> clz) {
        startActivity(new Intent(getApplicationContext(), clz));
    }

}
