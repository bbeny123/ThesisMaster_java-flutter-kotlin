package kotlin_test

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

actual class Main {
    actual fun checkMe() = 44
}

actual object Platform {
    actual val name: String = "Android"
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Main().checkMe()
        setContentView(R.layout.activity_main)


    }
}