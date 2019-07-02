package org.kamwas.android_test.helper;

import android.widget.TextView;

import java.util.Locale;

public class Utils {

    public static void setResult(TextView textView, long result) {
        setResult(textView, result, 1);
    }

    public static void setResult(TextView textView, long result, int tests) {
        textView.setText(String.format(Locale.UK, "%.3f ms", (double) result / tests / 1000000));
    }

    public static void start(TextView textView) {
        textView.setText("Benchmarking");
    }

}
