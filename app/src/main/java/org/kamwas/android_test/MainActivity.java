package org.kamwas.android_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

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
