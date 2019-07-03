package kotlin_test

class FileBenchmark(private val fileHelper: FileHelper) {

    private val times = 50
    private var timer = 0L

    private val fileSize = 100
    private val fileName = "test_file"
    private val fileName2 = "test_file2"

    fun saveFile(): Double {
        var result = 0L

        fileHelper.generateFile(fileName2, fileSize)
        val file = fileHelper.readFile(fileName2)
        repeat(times) {
            timer = Util.getNanoTime()
            fileHelper.saveFile(fileName, file)
            result += Util.getNanoTime() - timer
            fileHelper.deleteFile(fileName)
        }

        fileHelper.deleteFile(fileName2)
        return result / times.toDouble()
    }

    fun readFile(): Double {
        var result = 0L
        var dummy = 0

        fileHelper.generateFile(fileName, fileSize)
        repeat(times) {
            timer = Util.getNanoTime()
            dummy += fileHelper.readFile(fileName).size
            result += Util.getNanoTime() - timer
        }

        println(dummy)
        fileHelper.deleteFile(fileName)
        return result / times.toDouble()
    }

    fun deleteFile(): Double {
        var result = 0L
        var dummy = false

        fileHelper.generateFile(fileName2, fileSize)
        val file = fileHelper.readFile(fileName2)
        repeat(times) {
            fileHelper.saveFile(fileName, file)
            timer = Util.getNanoTime()
            dummy = fileHelper.deleteFile(fileName)
            result += Util.getNanoTime() - timer
        }

        println(dummy)
        fileHelper.deleteFile(fileName2)
        return result / times.toDouble()
    }

}

expect class FileHelper {

    fun saveFile(name: String, data: ByteArray)

    fun readFile(name: String): ByteArray

    fun deleteFile(name: String): Boolean

    fun generateFile(name: String, size: Int)

}