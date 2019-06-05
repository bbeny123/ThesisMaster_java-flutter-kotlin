package org.kamwas.android_test;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.System.nanoTime;
import static java.lang.System.nanoTime;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class CollectionPerformance {

    public long add10kObjects() {
        long timer = nanoTime();

        List<TestObject> list = new ArrayList<>();
        range(0, 10000).forEach(i -> list.add(new TestObject(i, "item" + i)));
        timer = nanoTime() - timer;

        Log.d(getClass().getSimpleName(), "add10kObjects: " + timer + ", size: " + list.size());
        return timer;
    }

    public long read10k() {
        List<TestObject> list = range(0, 10000).boxed().map(i -> new TestObject(i, "item" + i)).collect(toList());
        int dummy = 0;

        long timer = nanoTime();
        for (TestObject item : list) {
            dummy += item.getIndex();
        }

        timer = nanoTime() - timer;

        Log.d(getClass().getSimpleName(), "read10kObjects: " + timer + ", dummy: " + dummy);
        return timer;
    }

    public long read1kRandom() {
        List<TestObject> list = range(0, 10000).boxed().map(i -> new TestObject(i, "item" + i)).collect(toList());
        List<Integer> toRead = range(0, 1000).map(i -> new Random().nextInt(10000)).boxed().collect(toList());
        int dummy = 0;

        long timer = nanoTime();
        for (Integer i : toRead) {
            dummy += list.get(i).getIndex();
        }

        timer = nanoTime() - timer;

        Log.d(getClass().getSimpleName(), "read1kObjectsRandom: " + timer + ", dummy: " + dummy);
        return timer;
    }

    public long remove1kRandom() {
        List<TestObject> list = range(0, 10000).boxed().map(i -> new TestObject(i, "item" + i)).collect(toList());
        List<Integer> toRemove = range(0, 1000).map(i -> new Random().nextInt(10000 - i)).boxed().collect(toList());

        long timer = nanoTime();
        for (Integer i : toRemove) {
            list.remove((int) i);
        }

        timer = nanoTime() - timer;

        Log.d(getClass().getSimpleName(), "read1kObjectsRandom: " + timer + ", size: " + list.size());
        return timer;
    }

    public long filter() {
        List<TestObject> list = range(0, 10000).boxed().map(i -> new TestObject(i, "item" + i)).collect(toList());
        Collections.shuffle(list);

        long timer = nanoTime();
        list = list.stream().filter(i -> i.getIndex() > 5000).collect(toList());
        timer = nanoTime() - timer;

        Log.d(getClass().getSimpleName(), "read1kObjectsRandom: " + timer + ", size: " + list.size());
        return timer;
    }

    public long sort() {
        List<TestObject> list = range(0, 10000).boxed().map(i -> new TestObject(i, "item" + i)).collect(toList());
        Collections.shuffle(list);

        long timer = nanoTime();
        list = list.stream().sorted(comparing(TestObject::getIndex)).collect(toList());
        timer = nanoTime() - timer;

        Log.d(getClass().getSimpleName(), "read1kObjectsRandom: " + timer + ", lastItem: " + list.get(list.size() - 1).getIndex());
        return timer;
    }



}
