package org.kamwas.android_test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.kamwas.android_test.helper.User;
import org.kamwas.android_test.helper.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.System.nanoTime;

public class DBActivity extends AppCompatActivity {

    private static final double times = 1000;
    private static final int tableSize = 10000;
    private long timer = 0L;
    private DBPerformance db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DBPerformance(getApplicationContext());

        Util.benchmarkListener(findViewById(R.id.insertButton), findViewById(R.id.insertResult), this::insert);
        Util.benchmarkListener(findViewById(R.id.getOneButton), findViewById(R.id.getOneResult), this::get);
        Util.benchmarkListener(findViewById(R.id.getAllButton), findViewById(R.id.getAllResult), this::getAll);
        Util.benchmarkListener(findViewById(R.id.updateOneButton), findViewById(R.id.updateOneResult), this::update);
        Util.benchmarkListener(findViewById(R.id.deleteOneButton), findViewById(R.id.deleteOneResult), this::delete);
    }

    public double insert() {
        long result = 0L;

        db.clearTable();
        User user;
        for (int i = 0; i < times; i++) {
            user = db.dummyUser(i);
            timer = nanoTime();
            db.insert(user);
            result += nanoTime() - timer;
        }

        Log.i("DBActivity", "DB Add One Benchmark finished");
        return result / times;
    }

    public double get() {
        long result = 0L;

        db.prepareData(tableSize);
        long dummy = 0;
        int id;
        for (int i = 0; i < times; i++) {
            id = new Random().nextInt(tableSize);
            timer = nanoTime();
            dummy += db.get(id).getId();
            result += nanoTime() - timer;
        }

        Log.i("DBActivity", "DB Read One Benchmark finished " + dummy);
        return result / times;
    }

    public double getAll() {
        long result = 0L;

        db.prepareData(tableSize);
        int dummy = 0;
        for (int i = 0; i < times / 20; i++) {
            timer = nanoTime();
            dummy += db.findAll().size();
            result += nanoTime() - timer;
        }

        Log.i("DBActivity", "DB Read All Benchmark finished " + dummy);
        return result / (times / 20);
    }

    public double update() {
        long result = 0L;

        db.prepareData(tableSize);
        int id;
        for (int i = 0; i < times; i++) {
            id = new Random().nextInt(tableSize);
            timer = nanoTime();
            db.update(id, i);
            result += nanoTime() - timer;
        }

        Log.i("DBActivity", "DB Update One Benchmark finished");
        return result / times;
    }

    public double delete() {
        long result = 0L;

        db.prepareData(tableSize + (int) times);
        int id;
        for (int i = 0; i < times; i++) {
            id = new Random().nextInt(tableSize);
            timer = nanoTime();
            db.delete(id);
            result += nanoTime() - timer;
        }

        Log.i("DBActivity", "DB Delete One Benchmark finished");
        return result / times;
    }

    private class DBPerformance extends SQLiteOpenHelper {
        DBPerformance(Context context) {
            super(context, "DB_TEST", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB.SQL_CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }

        private void insert(User user) {
            ContentValues values = new ContentValues();
            values.put(DB.COLUMN_ID, user.getId());
            values.put(DB.COLUMN_LOGIN, user.getLogin());
            values.put(DB.COLUMN_EMAIL, user.getEmail());
            values.put(DB.COLUMN_NAME, user.getName());
            values.put(DB.COLUMN_AGE, user.getAge());
            this.getWritableDatabase().insert(DB.TABLE_NAME, null, values);
        }

        private User get(long id) {
            String[] args = {id + ""};
            List<User> result = mapCursor(getReadableDatabase().query(DB.TABLE_NAME, null, DB.COLUMN_ID + " = ?", args, null, null, null));
            return !result.isEmpty() ? result.get(0) : null;
        }

        private List<User> findAll() {
            return mapCursor(getReadableDatabase().query(DB.TABLE_NAME, null, null, null, null, null, null));
        }

        private void update(long id, int i) {
            ContentValues values = new ContentValues();
            values.put(DB.COLUMN_NAME, id + i);
            String[] args = {id + ""};
            getWritableDatabase().update(DB.TABLE_NAME, values, DB.COLUMN_ID + " = ?", args);
        }

        private void delete(long id) {
            String[] args = {id + ""};
            getWritableDatabase().delete(DB.TABLE_NAME, DB.COLUMN_ID + " = ?", args);
        }

        private void clearTable() {
            this.getWritableDatabase().execSQL(DB.SQL_CLEAR_TABLE);
        }

        private void prepareData(int size) {
            this.clearTable();

            db.getWritableDatabase().beginTransaction();
            for (int i = 0; i < size; i++) {
                insert(dummyUser(i));
            }
            db.getWritableDatabase().setTransactionSuccessful();
            db.getWritableDatabase().endTransaction();
        }

        private User dummyUser(int i) {
            return new User((long) i, "login " + i, "email " + i, "name " + i, 30);
        }

        private List<User> mapCursor(Cursor cursor) {
            List<User> users = new ArrayList<>();
            if (cursor.moveToFirst()) {
                do {
                    users.add(new User(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4)));
                } while (cursor.moveToNext());
            }
            cursor.close();
            return users;
        }
    }

    private interface DB {
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

        String SQL_CLEAR_TABLE = "DELETE FROM " + TABLE_NAME;
    }

}
