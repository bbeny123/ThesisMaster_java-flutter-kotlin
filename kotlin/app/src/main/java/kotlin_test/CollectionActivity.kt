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

        Util.benchmarkListener(addButton, addResult) { CollectionBenchmark.add10k() }
        Util.benchmarkListener(readAllButton, readAllResult) { CollectionBenchmark.readAll() }
        Util.benchmarkListener(readRandomButton, readRandomResult) { CollectionBenchmark.read10PercentRandom() }
        Util.benchmarkListener(removeRandomButton, removeRandomResult) { CollectionBenchmark.remove10PercentRandom() }
        Util.benchmarkListener(filterButton, filterResult) { CollectionBenchmark.filter() }
        Util.benchmarkListener(sortButton, sortResult) { CollectionBenchmark.sort() }
    }

}