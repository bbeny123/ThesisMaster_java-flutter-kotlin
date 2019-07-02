package kotlin_test

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

object DeSerBenchmark {

    fun serialization(): Double {
        val times = 10000
        var result = 0L
        var dummy = ""

        repeat(times) {
            val timer = Util.getNanoTime()
            dummy = serialize()
            result += Util.getNanoTime() - timer
        }

        println(dummy)
        return result / times.toDouble()
    }

    fun deserialization(): Double {
        val times = 10000
        var result = 0L
        var dummy = 0L

        val user = serialize()
        repeat(times) {
            val timer = Util.getNanoTime()
            dummy += deserialize(user).id
            result += Util.getNanoTime() - timer
        }

        println(dummy)
        return result / times.toDouble()
    }

    private fun serialize(user: User = User(1L, "user", "user@user", "user", 30)): String =
        Json(JsonConfiguration.Stable).stringify(User.serializer(), user)

    private fun deserialize(user: String): User =
        Json(JsonConfiguration.Stable).parse(User.serializer(), user)
}