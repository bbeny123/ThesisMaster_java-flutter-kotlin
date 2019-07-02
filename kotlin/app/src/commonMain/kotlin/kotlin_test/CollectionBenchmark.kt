package kotlin_test

import kotlin.random.Random

object CollectionBenchmark {

    private const val times = 10000
    private const val timesShort = 100
    private const val listSize = 10000
    private var timer = 0L

    fun add10k(): Double {
        timer = Util.getNanoTime()
        add(times)
        timer = Util.getNanoTime() - timer

        return timer / times.toDouble()
    }

    fun read10k(): Double {
        val list = generateList(times)
        val dummy: Int

        timer = Util.getNanoTime()
        dummy = read(list)
        timer = Util.getNanoTime() - timer

        println(dummy)
        return timer / times.toDouble()
    }

    fun read10PercentRandom(): Double {
        val list = generateList(times * 10)
        val toRead = generateRandomIndexes(times, times * 10)
        val dummy: Int

        timer = Util.getNanoTime()
        dummy = readRandom(list, toRead)
        timer = Util.getNanoTime() - timer

        println(dummy)
        return timer / times.toDouble()
    }

    fun remove10PercentRandom(): Double {
        val list = generateList(times * 10)
        val toRemove = generateRandomIndexes(times, times * 10, true)

        timer = Util.getNanoTime()
        removeRandom(list, toRemove)
        timer = Util.getNanoTime() - timer

        return timer / times.toDouble()
    }

    fun filter(): Double {
        var result = 0L

        val minIndex = listSize / 2
        var list: ArrayList<TestObject>
        repeat(timesShort) {
            list = generateList()
            timer = Util.getNanoTime()
            filter(list, minIndex)
            result += Util.getNanoTime() - timer
        }

        return result / timesShort.toDouble()
    }

    fun sort(): Double {
        var result = 0L

        val list = generateList()
        repeat(timesShort) {
            list.shuffle()
            timer = Util.getNanoTime()
            sort(list)
            result += Util.getNanoTime() - timer
        }

        return result / timesShort.toDouble()
    }

    private fun add(times: Int = 10000): Int {
        val list = ArrayList<TestObject>()
        repeat(times) {
            list.add(TestObject(it, "item$it"))
        }
        return list.size
    }

    private fun read(list: List<TestObject>): Int {
        var dummy = 0
        for (item in list) {
            dummy += item.index
        }
        return dummy
    }

    private fun readRandom(list: List<TestObject>, toRead: List<Int>): Int {
        var dummy = 0
        for (i in toRead) {
            dummy += list[i].index
        }
        return dummy
    }

    private fun removeRandom(list: ArrayList<TestObject>, toRemove: List<Int>): Int {
        for (i in toRemove) {
            list.removeAt(i)
        }
        return list.size
    }

    private fun filter(list: ArrayList<TestObject>, minIndex: Int): Int {
        list.filter { it.index > minIndex }
        return list.last().index
    }

    private fun sort(list: ArrayList<TestObject>): Int {
        list.sortBy { it.index }
        return list.last().index
    }

    private fun generateList(size: Int = listSize): ArrayList<TestObject> {
        val list = ArrayList<TestObject>()
        repeat(size) {
            list.add(TestObject(it, "item$it"))
        }
        return list
    }

    private fun generateRandomIndexes(size: Int, until: Int, reduce: Boolean = false): List<Int> {
        val list = ArrayList<Int>()
        repeat(size) {
            list.add(Random.nextInt(until - (if (reduce) it else 0)))
        }
        return list
    }

}