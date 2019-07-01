package org.kamwas.android_test.performance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
                COLUMN_AGE + " INTEGER NOT NULL CHECK (" + COLUMN_AGE + " > 0 && " + COLUMN_AGE + " < 150))";

        String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

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

    public void updateOne(int id, int offset) {
        ContentValues values = new ContentValues();
        values.put(DB.COLUMN_ID, id + offset);
        String[] args = { id + "" };
        getWritableDatabase().update(DB.TABLE_NAME, values, DB.COLUMN_ID + " = ?", args);
    }

    public void deleteOne(int id) {
        String[] args = { id + "" };
        getWritableDatabase().delete(DB.TABLE_NAME, DB.COLUMN_ID + " = ?", args);
    }

}
