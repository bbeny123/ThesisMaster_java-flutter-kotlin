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

        val db = DBBenchmark(AndroidSqliteDriver(TestDB.Schema, applicationContext, "test.db"))

        Util.benchmarkListener(insertButton, insertResult) { db.insert() }
        Util.benchmarkListener(getOneButton, getOneResult) { db.get() }
        Util.benchmarkListener(getAllButton, getAllResult) { db.getAll() }
        Util.benchmarkListener(updateOneButton, updateOneResult) { db.update() }
        Util.benchmarkListener(deleteOneButton, deleteOneResult) { db.delete() }
    }

}