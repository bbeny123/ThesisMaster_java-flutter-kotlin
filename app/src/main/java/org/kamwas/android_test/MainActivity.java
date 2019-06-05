package org.kamwas.android_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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
            textView2.setText(collectionPerformance.sort() + "");
        });

    }
}
