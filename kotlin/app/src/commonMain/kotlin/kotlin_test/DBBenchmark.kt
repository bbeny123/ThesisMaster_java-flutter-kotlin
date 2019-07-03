package kotlin_test

import com.squareup.sqldelight.db.SqlDriver
import kotlin_test.data.UserQueries
import kotlin.random.Random

class DBBenchmark(driver: SqlDriver) {

    private val times = 1000
    private val tableSize = 10000
    private var timer = 0L

    private val userQueries: UserQueries = TestDB(driver).userQueries

    fun insert(): Double {
        var result = 0L

        clearTable()
        var user: User
        repeat(times) {
            user = dummyUser(it)
            timer = Util.getNanoTime()
            insert(user)
            result += Util.getNanoTime() - timer
        }

        return result / times.toDouble()
    }

    fun get(): Double {
        var result = 0L

        prepareData(tableSize)
        var dummy = 0L
        var id: Long
        repeat(times) {
            id = Random.nextLong(tableSize.toLong())
            timer = Util.getNanoTime()
            dummy += get(id).id
            result += Util.getNanoTime() - timer
        }

        println(dummy)
        return result / times.toDouble()
    }

    fun getAll(): Double {
        var result = 0L

        prepareData(tableSize)
        var dummy = 0
        repeat(times / 20) {
            timer = Util.getNanoTime()
            dummy += findAll().size
            result += Util.getNanoTime() - timer
        }

        println(dummy)
        return result / (times / 20.0)
    }

    fun update(): Double {
        var result = 0L

        prepareData(tableSize)
        var id: Long
        repeat(times) {
            id = Random.nextLong(tableSize.toLong())
            timer = Util.getNanoTime()
            update(id, it)
            result += Util.getNanoTime() - timer
        }

        return result / times.toDouble()
    }

    fun delete(): Double {
        var result = 0L

        prepareData(tableSize + times)
        var id: Long
        repeat(times) {
            id = Random.nextLong(tableSize.toLong())
            timer = Util.getNanoTime()
            delete(id)
            result += Util.getNanoTime() - timer
        }

        return result / times.toDouble()
    }

    private fun insert(user: User) = userQueries.insert(user.id, user.login, user.email, user.name, user.age)

    private fun get(id: Long): User = userQueries.selectOne(id).executeAsOne().toUser()

    private fun findAll(): List<User> = userQueries.selectAll().executeAsList().map { it.toUser() }

    private fun update(id: Long, i: Int) = userQueries.updateName((id + i).toString(), id)

    private fun delete(id: Long) = userQueries.delete(id)

    private fun clearTable() = userQueries.clearTable()

    private fun prepareData(size: Int) {
        clearTable()
        repeat(size) {
            insert(dummyUser(it))
        }
    }

    private fun dummyUser(i: Int) = User(i.toLong(), "login $i", "email $i", "name &$i", 30)

}