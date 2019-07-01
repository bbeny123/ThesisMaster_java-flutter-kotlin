package org.kamwas.android_test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import org.kamwas.android_test.helper.Utils;

import java.util.List;
import java.util.Random;

import static java.lang.System.nanoTime;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class DBActivity extends AppCompatActivity {

    private DBPerformance db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button addOneButton  = findViewById(R.id.addOneButton);
        Button getOneButton  = findViewById(R.id.getOneButton);
        Button getAllButton  = findViewById(R.id.getAllButton);
        Button updateOneButton  = findViewById(R.id.updateOneButton);
        Button deleteOneButton  = findViewById(R.id.deleteOneButton);
        TextView addOneResult  = findViewById(R.id.addOneResult);
        TextView getOneResult  = findViewById(R.id.getOneResult);
        TextView getAllResult  = findViewById(R.id.getAllResult);
        TextView updateOneResult  = findViewById(R.id.updateOneResult);
        TextView deleteOneResult  = findViewById(R.id.deleteOneResult);

        addOneButton.setOnClickListener(b -> {
            Utils.start(addOneResult);
            AsyncTask.execute(() -> addOne(addOneResult));
        });

        getOneButton.setOnClickListener(b -> {
            Utils.start(getOneResult);
            AsyncTask.execute(() -> getOne(getOneResult));
        });

        getAllButton.setOnClickListener(b -> {
            Utils.start(getAllResult);
            AsyncTask.execute(() -> getAll(getAllResult));
        });

        updateOneButton.setOnClickListener(b -> {
            Utils.start(updateOneResult);
            AsyncTask.execute(() -> updateOne(updateOneResult));
        });

        deleteOneButton.setOnClickListener(b -> {
            Utils.start(deleteOneResult);
            AsyncTask.execute(() -> deleteOne(deleteOneResult));
        });

        db = new DBPerformance(getApplicationContext());
    }

    public void addOne(TextView textView) {
        long result = 0L;
        long timer;

        db.recreateTable();
        for (int i = 0; i < 10; i++) {
            timer = nanoTime();
            db.addOne(i, "login " + i, "email " + i, "name " + i, 30);
            result = nanoTime() - timer;
        }

        Log.i("DBActivity", "DB Add One Benchmark finished");
        Utils.setResult(textView, result, 10);
    }

    public void getOne(TextView textView) {
        long result = 0L;
        long timer;

        db.recreateTableWithData();
        List<Integer> toRead = range(0, 1000).map(i -> new Random().nextInt(1000)).boxed().collect(toList());
        for (int i = 0; i < 1000; i++) {
            timer = nanoTime();
            db.getOne(toRead.get(i));
            result = nanoTime() - timer;
        }

        Log.i("DBActivity", "DB Read One Benchmark finished");
        Utils.setResult(textView, result, 1000);
    }

    public void getAll(TextView textView) {
        long result = 0L;
        long timer;

        db.recreateTableWithData();
        for (int i = 0; i < 1000; i++) {
            timer = nanoTime();
            db.getAll().getCount();
            result = nanoTime() - timer;
        }

        Log.i("DBActivity", "DB Read All Benchmark finished");
        Utils.setResult(textView, result, 1000);
    }

    public void updateOne(TextView textView) {
        long result = 0L;
        long timer;

        db.recreateTableWithData();
        List<Integer> toUpdate = range(0, 1000).map(i -> new Random().nextInt(1000)).boxed().collect(toList());
        for (int i = 0; i < 1000; i++) {
            timer = nanoTime();
            db.updateOne(toUpdate.get(i), i);
            result = nanoTime() - timer;
        }

        Log.i("DBActivity", "DB Update One Benchmark finished");
        Utils.setResult(textView, result, 1000);
    }

    public void deleteOne(TextView textView) {
        long result = 0L;
        long timer;

        List<Integer> toDelete = range(0, 1000).map(i -> new Random().nextInt(1000)).boxed().collect(toList());
        for (int i = 0; i < 10; i++) {
            db.recreateTableWithData();
            timer = nanoTime();
            db.deleteOne(toDelete.get(i));
            result = nanoTime() - timer;
        }

        Log.i("DBActivity", "DB Delete One Benchmark finished");
        Utils.setResult(textView, result, 10);
    }

    public class DBPerformance extends SQLiteOpenHelper {
        public DBPerformance(Context context) {
            super(context, "DATABASE", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB.SQL_CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }

        public void recreateTable() {
            this.getWritableDatabase().execSQL(DB.SQL_DROP_TABLE);
            this.getWritableDatabase().execSQL(DB.SQL_CREATE_TABLE);
        }

        public void recreateTableWithData() {
            this.recreateTable();
            for (int i = 0; i < 1000; i++) {
                db.addOne(i, "login " + i, "email " + i, "name " + i, 30);
            }
        }

        public void addOne(int id, String login, String email, String name, int age) {
            ContentValues values = new ContentValues();
            values.put(DB.COLUMN_ID, id);
            values.put(DB.COLUMN_LOGIN, login);
            values.put(DB.COLUMN_EMAIL, email);
            values.put(DB.COLUMN_NAME, name);
            values.put(DB.COLUMN_AGE, age);
            this.getWritableDatabase().insert(DB.TABLE_NAME, null, values);
        }

        public Cursor getOne(int id) {
            String[] args = { id + "" };
            return getReadableDatabase().query(DB.TABLE_NAME, null, DB.COLUMN_ID + " = ?", args, null, null, null);
        }

        public Cursor getAll() {
            return getReadableDatabase().query(DB.TABLE_NAME, null, null, null, null, null, null);
        }

        public void updateOne(int id, int i) {
            ContentValues values = new ContentValues();
            values.put(DB.COLUMN_NAME, id + i);
            String[] args = { id + "" };
            getWritableDatabase().update(DB.TABLE_NAME, values, DB.COLUMN_ID + " = ?", args);
        }

        public void deleteOne(int id) {
            String[] args = { id + "" };
            getWritableDatabase().delete(DB.TABLE_NAME, DB.COLUMN_ID + " = ?", args);
        }
    }

    public interface DB {
        String TABLE_NAME = "USERS";
        String COLUMN_ID = "ID";
        String COLUMN_LOGIN = "LOGIN";
        String COLUMN_EMAIL = "EMAIL";
        String COLUMN_NAME = "NAME";
        String COLUMN_AGE = "AGE";

        String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_LOGIN + " TEXT UNIQUE, " +
                        COLUMN_EMAIL + " TEXT UNIQUE, " +
                        COLUMN_NAME + " TEXT NOT NULL, " +
                        COLUMN_AGE + " INTEGER NOT NULL CHECK (" + COLUMN_AGE + " > 0 AND " + COLUMN_AGE + " < 150))";

        String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

}
