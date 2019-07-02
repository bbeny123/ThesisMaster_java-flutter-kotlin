package kotlin_test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_collection.toolbar
import kotlinx.android.synthetic.main.activity_rest.*

class RESTActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rest)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getButton.setOnClickListener { RESTBenchmark.get { result -> Util.benchmark(getResult) { result } } }
        postButton.setOnClickListener { RESTBenchmark.post { result -> Util.benchmark(postResult) { result } } }
    }

}