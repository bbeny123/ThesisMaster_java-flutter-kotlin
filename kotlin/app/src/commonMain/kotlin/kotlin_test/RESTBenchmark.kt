package kotlin_test

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.Url
import io.ktor.http.contentType
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object RESTBenchmark {

    private val URL = Url("http://10.0.2.2:8080")
    private val client = HttpClient {
        install(JsonFeature)
    }

    fun get(callback: (Double) -> Unit) = GlobalScope.launch {
        val times = 100
        var result = 0L
        var dummy = 0L

        repeat(times) {
            val timer = Util.getNanoTime()
            dummy += get().id
            result += Util.getNanoTime() - timer
        }

        println(dummy)
        callback(result / times.toDouble())
    }

    fun post(callback: (Double) -> Unit) = GlobalScope.launch {
        val times = 100
        var result = 0L

        repeat(times) {
            val timer = Util.getNanoTime()
            post()
            result += Util.getNanoTime() - timer
        }

        callback(result / times.toDouble())
    }

    private suspend fun get(): User = client.get {
        url(URL)
    }

    private suspend fun post(user: User = User(1L, "user", "user@user", "user", 30)) = client.post<Any> {
        url(URL)
        contentType(ContentType.Application.Json)
        body = user
    }

}