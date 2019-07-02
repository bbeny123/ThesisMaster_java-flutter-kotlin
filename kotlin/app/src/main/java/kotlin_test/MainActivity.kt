package kotlin_test

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        collectionButton.setOnClickListener { goToActivity(CollectionActivity::class.java) }

    }

    private fun goToActivity(clz: Class<out AppCompatActivity>) {
        startActivity(Intent(applicationContext, clz))
    }

}