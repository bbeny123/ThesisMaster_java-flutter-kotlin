package org.kamwas.android_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.kamwas.android_test.helper.TestObject;
import org.kamwas.android_test.helper.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.lang.System.nanoTime;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class CollectionActivity extends AppCompatActivity {

    private static final double times = 10000;
    private static final double timesShort = 100;
    private static final int listSize = 10000;
    private long timer = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Utils.benchmarkListener(findViewById(R.id.addButton), findViewById(R.id.addResult), this::add10k);
        Utils.benchmarkListener(findViewById(R.id.readAllButton), findViewById(R.id.readAllResult), this::readAll);
        Utils.benchmarkListener(findViewById(R.id.readRandomButton), findViewById(R.id.readRandomResult), this::read10PercentRandom);
        Utils.benchmarkListener(findViewById(R.id.removeRandomButton), findViewById(R.id.removeRandomResult), this::remove10PercentRandom);
        Utils.benchmarkListener(findViewById(R.id.filterButton), findViewById(R.id.filterResult), this::filter);
        Utils.benchmarkListener(findViewById(R.id.sortButton), findViewById(R.id.sortResult), this::sort);
    }

    public double add10k() {
        timer = nanoTime();
        add((int) times);
        timer = nanoTime() - timer;

        Log.i("CollectionActivity", "Collection Add One Benchmark finished");
        return timer / times;
    }

    public double readAll() {
        List<TestObject> list = generateList((int) times);
        int dummy;

        timer = nanoTime();
        dummy = read(list);
        timer = nanoTime() - timer;

        Log.i("CollectionActivity", "Collection Read All Benchmark finished " + dummy);
        return timer / times;
    }

    public double read10PercentRandom() {
        List<TestObject> list = generateList(listSize);
        List<Integer> toRead = generateRandomIndexes((int) times, (int) times * 10, false);
        int dummy;

        timer = nanoTime();
        dummy = readRandom(list, toRead);
        timer = nanoTime() - timer;

        Log.i("CollectionActivity", "Collection Read Random Benchmark finished " + dummy);
        return timer / times;
    }

    public double remove10PercentRandom() {
        List<TestObject> list = generateList(listSize);
        List<Integer> toRemove = generateRandomIndexes((int) times, (int) times * 10, true);

        timer = nanoTime();
        removeRandom(list, toRemove);
        timer = nanoTime() - timer;

        Log.i("CollectionActivity", "Collection 10% Benchmark finished");
        return timer / times;
    }

    public double filter() {
        long result = 0L;

        int minIndex = listSize / 2;
        List<TestObject> list;
        for (int i = 0; i < timesShort; i++) {
            list = generateList(listSize);
            timer = nanoTime();
            filter(list, minIndex);
            result += nanoTime() - timer;
        }

        Log.i("CollectionActivity", "Collection Filter Benchmark finished");
        return result / times;
    }

    public double sort() {
        long result = 0L;

        List<TestObject> list = generateList(listSize);
        for (int i = 0; i < timesShort; i++) {
            Collections.shuffle(list);
            timer = nanoTime();
            sort(list);
            result += nanoTime() - timer;
        }

        Log.i("CollectionActivity", "Collection Sort Benchmark finished");
        return result / times;
    }

    public int add(int times) {
        List<TestObject> list = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            list.add(new TestObject(i, "item" + i));
        }
        return list.size();
    }

    public int read(List<TestObject> list) {
        int dummy = 0;
        for (TestObject item : list) {
            dummy += item.getIndex();
        }
        return dummy;
    }

    public int readRandom(List<TestObject> list, List<Integer> toRead) {
        int dummy = 0;
        for (Integer i : toRead) {
            dummy += list.get(i).getIndex();
        }
        return dummy;
    }

    public int removeRandom(List<TestObject> list, List<Integer> toRemove) {
        for (Integer i : toRemove) {
            list.remove((int) i);
        }
        return list.size();
    }

    public int filter(List<TestObject> list, int minIndex) {
        list.removeIf(i -> i.getIndex() > minIndex);
        return list.size();
    }

    public int sort(List<TestObject> list) {
        list.sort(comparing(TestObject::getIndex));
        return list.get(list.size() - 1).getIndex();
    }

    private List<TestObject> generateList(int size) {
        return range(0, size)
                .boxed()
                .map(i -> new TestObject(i, "item" + i))
                .collect(toList());
    }

    private List<Integer> generateRandomIndexes(int size, int maxIndex, boolean reduce) {
        return range(0, size)
                .map(i -> new Random().nextInt(maxIndex - (reduce ? i : 0)))
                .boxed()
                .collect(toList());
    }

}
