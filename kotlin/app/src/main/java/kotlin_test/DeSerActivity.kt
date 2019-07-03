package kotlin_test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_collection.toolbar
import kotlinx.android.synthetic.main.activity_deser.*

class DeSerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deser)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Util.benchmarkListener(serializationButton, serializationResult) { DeSerBenchmark.serialization() }
        Util.benchmarkListener(deserializationButton, deserializationResult) { DeSerBenchmark.deserialization() }
    }

}