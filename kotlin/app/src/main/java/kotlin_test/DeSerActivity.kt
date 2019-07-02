package kotlin_test

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_collection.toolbar
import kotlinx.android.synthetic.main.activity_deser.*

class DeSerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deser)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        serializationButton.setOnClickListener { Util.benchmark(serializationResult) { CollectionBenchmark.add10k() } }
        deserializationButton.setOnClickListener { Util.benchmark(deserializationResult) { CollectionBenchmark.read10k() } }
    }

}