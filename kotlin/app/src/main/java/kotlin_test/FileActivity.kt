package kotlin_test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_collection.toolbar
import kotlinx.android.synthetic.main.activity_file.*
import java.io.File
import java.io.FileOutputStream
import java.security.SecureRandom

class FileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val benchmark = FileBenchmark(FileHelper(filesDir))

        Util.benchmarkListener(saveButton, saveResult) { benchmark.saveFile() }
        Util.benchmarkListener(readButton, readResult) { benchmark.readFile() }
        Util.benchmarkListener(deleteButton, deleteResult) { benchmark.deleteFile() }
    }

}

actual class FileHelper(private val path: File) {

    actual fun saveFile(name: String, data: ByteArray) = File(path, name).writeBytes(data)

    actual fun readFile(name: String): ByteArray = File(path, name).readBytes()

    actual fun deleteFile(name: String): Boolean = File(path, name).delete()

    actual fun generateFile(name: String, size: Int) {
        val file = FileOutputStream(File(path, name))
        val data = ByteArray(1024 * 1024 * size)
        SecureRandom().nextBytes(data)
        file.write(data)
        file.close()
    }

}