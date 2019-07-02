package kotlin_test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.sqldelight.android.AndroidSqliteDriver
import kotlinx.android.synthetic.main.activity_collection.toolbar
import kotlinx.android.synthetic.main.activity_db.*

class DBActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_db)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val DB = DBBenchmark(AndroidSqliteDriver(TestDB.Schema, applicationContext, "test.db"))

        addOneButton.setOnClickListener { Util.benchmark(addOneResult) { DB.insert() } }
        getOneButton.setOnClickListener { Util.benchmark(getOneResult) { DB.get() } }
        getAllButton.setOnClickListener { Util.benchmark(getAllResult) { DB.getAll() } }
        updateOneButton.setOnClickListener { Util.benchmark(updateOneResult) { DB.update() } }
        deleteOneButton.setOnClickListener { Util.benchmark(deleteOneResult) { DB.delete() } }
    }

}