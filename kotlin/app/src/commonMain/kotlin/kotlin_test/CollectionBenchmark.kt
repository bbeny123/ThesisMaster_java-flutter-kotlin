package kotlin_test

import kotlin.random.Random

object CollectionBenchmark {

    fun add10k(): Double {
        val times = 10000

        var timer = Util.getNanoTime()
        add(times)
        timer = Util.getNanoTime() - timer

        return timer / times.toDouble()
    }

    fun read10k(): Double {
        val times = 10000

        val list = generateList(times)

        var timer = Util.getNanoTime()
        read(list)
        timer = Util.getNanoTime() - timer

        return timer / times.toDouble()
    }

    fun read1kRandom(): Double {
        val times = 1000
        val listSize = 10000

        val list = generateList(listSize)
        val toRead = generateRandomIndexes(times)

        var timer = Util.getNanoTime()
        readRandom(list, toRead)
        timer = Util.getNanoTime() - timer

        return timer / times.toDouble()
    }

    fun remove1kRandom(): Double {
        val times = 1000
        val listSize = 10000

        val list = generateList(listSize)
        val toRemove = generateRandomIndexes(times, listSize, true)

        var timer = Util.getNanoTime()
        removeRandom(list, toRemove)
        timer = Util.getNanoTime() - timer

        return timer / times.toDouble()
    }

    fun filter(): Double {
        val times = 100
        val listSize = 10000
        val minIndex = 5000
        var result = 0L

        repeat(times) {
            val list = generateList(listSize)
            val timer = Util.getNanoTime()
            filter(list, minIndex)
            result += Util.getNanoTime() - timer
        }

        return result / times.toDouble()
    }

    fun sort(): Double {
        val times = 100
        val listSize = 10000
        var result = 0L
        val list = generateList(listSize)

        repeat(times) {
            list.shuffle()
            val timer = Util.getNanoTime()
            sort(list)
            result += Util.getNanoTime() - timer
        }

        return result / times.toDouble()
    }

    private fun add(times: Int = 10000): Int {
        val list = ArrayList<TestObject>()
        repeat(times) { i ->
            list.add(TestObject(i, "item$i"))
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

    private fun generateList(size: Int = 10000): ArrayList<TestObject> {
        val list = ArrayList<TestObject>()
        repeat(size) { i ->
            list.add(TestObject(i, "item$i"))
        }
        return list
    }

    private fun generateRandomIndexes(size: Int = 1000, until: Int = 10000, reduce: Boolean = false): List<Int> {
        val list = ArrayList<Int>()
        repeat(size) { i ->
            list.add(Random.nextInt(until - (if (reduce) i else 0)))
        }
        return list
    }

}