package kotlin_test

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_collection.*

class CollectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collection)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        addButton.setOnClickListener {
            Util.start(addResult)
            AsyncTask.execute { Util.setResult(addResult, CollectionBenchmark.add10k()) }
        }

        readAllButton.setOnClickListener {
            Util.start(readAllResult)
            AsyncTask.execute { Util.setResult(readAllResult, CollectionBenchmark.read10k()) }
        }

        readRandomButton.setOnClickListener {
            Util.start(readRandomResult)
            AsyncTask.execute { Util.setResult(readRandomResult, CollectionBenchmark.read1kRandom()) }
        }

        removeRandomButton.setOnClickListener {
            Util.start(removeRandomResult)
            AsyncTask.execute { Util.setResult(removeRandomResult, CollectionBenchmark.remove1kRandom()) }
        }

        filterButton.setOnClickListener {
            Util.start(filterResult)
            AsyncTask.execute { Util.setResult(filterResult, CollectionBenchmark.filter()) }
        }

        sortButton.setOnClickListener {
            Util.start(sortResult)
            AsyncTask.execute { Util.setResult(sortResult, CollectionBenchmark.sort()) }
        }

    }

}