package kotlin_test

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonMenu(collectionButton, CollectionActivity::class.java)
        buttonMenu(restButton, RESTActivity::class.java)
        buttonMenu(dbButton, DBActivity::class.java)
        buttonMenu(deserButton, DeSerActivity::class.java)
        buttonMenu(fileButton, FileActivity::class.java)
    }

    private fun buttonMenu(button: Button, clz: Class<out AppCompatActivity>) {
        button.setOnClickListener { startActivity(Intent(applicationContext, clz)) }
    }

}