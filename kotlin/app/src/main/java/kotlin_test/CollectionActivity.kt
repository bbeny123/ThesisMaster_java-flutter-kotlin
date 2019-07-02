package kotlin_test

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_collection.*

class CollectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collection)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        addButton.setOnClickListener { Util.benchmark(addResult) { CollectionBenchmark.add10k() } }
        readAllButton.setOnClickListener { Util.benchmark(readAllResult) { CollectionBenchmark.read10k() } }
        readRandomButton.setOnClickListener { Util.benchmark(readRandomResult) { CollectionBenchmark.read1kRandom() } }
        removeRandomButton.setOnClickListener { Util.benchmark(removeRandomResult) { CollectionBenchmark.remove1kRandom() } }
        filterButton.setOnClickListener { Util.benchmark(filterResult) { CollectionBenchmark.filter() } }
        sortButton.setOnClickListener { Util.benchmark(sortResult) { CollectionBenchmark.sort() } }
    }

}