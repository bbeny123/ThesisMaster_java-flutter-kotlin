package kotlin_test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_collection.toolbar
import kotlinx.android.synthetic.main.activity_file.*
import java.io.File
import java.io.RandomAccessFile

class FileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val benchmark = FileBenchmark(FileHelper(filesDir))

        saveButton.setOnClickListener { Util.benchmark(saveResult) { benchmark.saveFile() } }
        readButton.setOnClickListener { Util.benchmark(readResult) { benchmark.readFile() } }
        deleteButton.setOnClickListener { Util.benchmark(deleteResult) { benchmark.deleteFile() } }
    }

}

actual class FileHelper(private val path: File) {

    actual fun saveFile(name: String, data: ByteArray) = File(path, name).writeBytes(data)

    actual fun readFile(name: String): ByteArray = File(path, name).readBytes()

    actual fun deleteFile(name: String): Boolean = File(path, name).delete()

    actual fun generateFile(name: String, size: Int) {
        val file = RandomAccessFile(File(path, name), "rw")
        file.setLength((1024 * 1024 * size).toLong())
    }

}