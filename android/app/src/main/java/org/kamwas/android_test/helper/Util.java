package org.kamwas.android_test.helper;

import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;
import java.util.function.Supplier;

public class Util {

    public static void benchmarkListener(Button button, TextView textView, Supplier<Double> benchmark) {
        button.setOnClickListener(b -> benchmark(textView, benchmark));
    }

    public static void benchmark(TextView textView, Supplier<Double> benchmark) {
        textView.setText("Benchmarking");
        AsyncTask.execute(() -> textView.setText(String.format(Locale.UK, "%.3f ms", benchmark.get() / 1000000)));
    }

}
