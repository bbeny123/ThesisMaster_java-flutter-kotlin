package kotlin_test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_collection.*

class CollectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collection)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        addButton.setOnClickListener { Util.benchmark(addResult) { CollectionBenchmark.add10k() } }
        readAllButton.setOnClickListener { Util.benchmark(readAllResult) { CollectionBenchmark.read10k() } }
        readRandomButton.setOnClickListener { Util.benchmark(readRandomResult) { CollectionBenchmark.read10PercentRandom() } }
        removeRandomButton.setOnClickListener { Util.benchmark(removeRandomResult) { CollectionBenchmark.remove10PercentRandom() } }
        filterButton.setOnClickListener { Util.benchmark(filterResult) { CollectionBenchmark.filter() } }
        sortButton.setOnClickListener { Util.benchmark(sortResult) { CollectionBenchmark.sort() } }
    }

}