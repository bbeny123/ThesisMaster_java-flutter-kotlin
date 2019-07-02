package kotlin_test

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_collection.toolbar
import kotlinx.android.synthetic.main.activity_db.*

class DBActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_db)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        addOneButton.setOnClickListener { Util.benchmark(addOneResult) { CollectionBenchmark.add10k() } }
        getOneButton.setOnClickListener { Util.benchmark(getOneResult) { CollectionBenchmark.read10k() } }
        getAllButton.setOnClickListener { Util.benchmark(getAllResult) { CollectionBenchmark.read1kRandom() } }
        updateOneButton.setOnClickListener { Util.benchmark(updateOneResult) { CollectionBenchmark.remove1kRandom() } }
        deleteOneButton.setOnClickListener { Util.benchmark(deleteOneResult) { CollectionBenchmark.filter() } }
    }

}