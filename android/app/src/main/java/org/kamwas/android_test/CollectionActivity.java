package org.kamwas.android_test;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button addButton  = findViewById(R.id.addButton);
        Button readAllButton  = findViewById(R.id.readAllButton);
        Button readRandomButton  = findViewById(R.id.readRandomButton);
        Button removeButton  = findViewById(R.id.removeRandomButton);
        Button filterButton  = findViewById(R.id.filterButton);
        Button sortButton  = findViewById(R.id.sortButton);
        TextView addResult  = findViewById(R.id.addResult);
        TextView readAllResult  = findViewById(R.id.readAllResult);
        TextView readRandomResult  = findViewById(R.id.readRandomResult);
        TextView removeResult  = findViewById(R.id.removeRandomResult);
        TextView filterResult  = findViewById(R.id.filterResult);
        TextView sortResult  = findViewById(R.id.sortResult);

        addButton.setOnClickListener(b -> {
            Utils.start(addResult);
            AsyncTask.execute(() -> add(addResult));
        });

        readAllButton.setOnClickListener(b -> {
            Utils.start(readAllResult);
            AsyncTask.execute(() -> readAll(readAllResult));
        });

        readRandomButton.setOnClickListener(b -> {
            Utils.start(readRandomResult);
            AsyncTask.execute(() -> readRandom(readRandomResult));
        });

        removeButton.setOnClickListener(b -> {
            Utils.start(removeResult);
            AsyncTask.execute(() -> removeRandom(removeResult));
        });

        filterButton.setOnClickListener(b -> {
            Utils.start(filterResult);
            AsyncTask.execute(() -> filter(filterResult));
        });

        sortButton.setOnClickListener(b -> {
            Utils.start(sortResult);
            AsyncTask.execute(() -> sort(sortResult));
        });
    }

    public void add(TextView textView) {
        Log.i("CollectionActivity", "Collection Add One Benchmark finished");
        Utils.setResult(textView, add10kObjects(), 10000);
    }

    public void readAll(TextView textView) {
        long result = 0L;

        for (int i = 0; i < 100; i++) {
            result += read10k();
        }

        Log.i("CollectionActivity", "Collection Read All Benchmark finished");
        Utils.setResult(textView, result, 100);
    }

    public void readRandom(TextView textView) {
        Log.i("CollectionActivity", "Collection Read Random Benchmark finished");
        Utils.setResult(textView, read1kRandom(), 1000);
    }

    public void removeRandom(TextView textView) {
        Log.i("CollectionActivity", "Collection Remove Random Benchmark finished");
        Utils.setResult(textView, remove1kRandom(), 1000);
    }

    public void filter(TextView textView) {
        long result = 0L;

        for (int i = 0; i < 100; i++) {
            result += filter();
        }

        Log.i("CollectionActivity", "Collection Filter Benchmark finished");
        Utils.setResult(textView, result, 100);
    }

    public void sort(TextView textView) {
        long result = 0L;

        for (int i = 0; i < 100; i++) {
            result += sort();
        }

        Log.i("CollectionActivity", "Collection Sort Benchmark finished");
        Utils.setResult(textView, result, 100);
    }

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

//        Log.d(getClass().getSimpleName(), "read10kObjects: " + timer + ", dummy: " + dummy);
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

//        Log.d(getClass().getSimpleName(), "read1kObjectsRandom: " + timer + ", size: " + list.size());
        return timer;
    }

    public long sort() {
        List<TestObject> list = range(0, 10000).boxed().map(i -> new TestObject(i, "item" + i)).collect(toList());
        Collections.shuffle(list);

        long timer = nanoTime();
        list = list.stream().sorted(comparing(TestObject::getIndex)).collect(toList());
        timer = nanoTime() - timer;

//        Log.d(getClass().getSimpleName(), "read1kObjectsRandom: " + timer + ", lastItem: " + list.get(list.size() - 1).getIndex());
        return timer;
    }

}
