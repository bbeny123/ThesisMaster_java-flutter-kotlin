package org.kamwas.android_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import org.kamwas.android_test.performance.CollectionPerformance;
import org.kamwas.android_test.rest.RestActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textView2;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView2 = findViewById(R.id.textView2);
        button = findViewById(R.id.button);

        CollectionPerformance collectionPerformance = new CollectionPerformance();
        button.setOnClickListener(b -> {
            Intent goToNextActivity = new Intent(getApplicationContext(), RestActivity.class);
            startActivity(goToNextActivity);
//            restPerformance.postAsync();
//            textView2.setText(collectionPerformance.sort() + "");
        });

    }
}
