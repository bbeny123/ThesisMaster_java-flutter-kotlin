package kotlin_test

import android.widget.TextView

actual object Util {

    actual fun getNanoTime(): Long {
        return System.nanoTime()
    }

    fun setResult(textView: TextView, result: Double) {
        textView.text = String.format("%.3f ms", result / 1000000)
    }

    fun start(textView: TextView) {
        textView.text = "Benchmarking"
    }

}