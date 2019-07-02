package kotlin_test

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_collection.toolbar
import kotlinx.android.synthetic.main.activity_file.*

class FileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        saveButton.setOnClickListener { Util.benchmark(saveResult) { CollectionBenchmark.add10k() } }
        readButton.setOnClickListener { Util.benchmark(readResult) { CollectionBenchmark.read10k() } }
        deleteButton.setOnClickListener { Util.benchmark(deleteResult) { CollectionBenchmark.read1kRandom() } }
    }

}