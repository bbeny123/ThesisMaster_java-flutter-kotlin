package kotlin_test

import android.os.AsyncTask
import android.widget.Button
import android.widget.TextView

actual object Util {

    actual fun getNanoTime(): Long {
        return System.nanoTime()
    }

    fun benchmark(textView: TextView, benchmark: () -> Double) {
        textView.text = "Benchmarking"
        AsyncTask.execute { textView.text = String.format("%.3f ms", benchmark.invoke() / 1000000) }
    }

    fun benchmarkListener(button: Button, textView: TextView, benchmark: () -> Double) {
        button.setOnClickListener { benchmark(textView, benchmark) }
    }
}