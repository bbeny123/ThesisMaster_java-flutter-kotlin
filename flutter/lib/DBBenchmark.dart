import 'dart:math';

import 'package:flutter/widgets.dart';
import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';

import 'helper/Util.dart';

class DBBenchmark {

  Database _database;

  DBBenchmark() {
    openDb();
  }

  void openDb() async {
    _database =
    await openDatabase(join(await getDatabasesPath(), 'db_flutter.db'),
        onCreate: (db, version) {
          return db.execute(DB.SQL_CREATE_TABLE);
        },
        version: 1);
  }

  void recreateTable() {
    _database.execute(DB.SQL_DROP_TABLE);
    _database.execute(DB.SQL_CREATE_TABLE);
  }

  Future<void> recreateTableWithData() async {
    recreateTable();
    for (int i = 0; i < 1000; i++) {
      await addOne(i, "login $i", "email $i", "name $i", 30);
    }
  }

  void addOneText(TextEditingController text) async {
    text.text = 'TESTING';
    text.text = Util.formatResult(await addOneResult());
  }

  void getOneText(TextEditingController text) async {
    text.text = 'TESTING';
    text.text = Util.formatResult(await getOneResult());
  }

  void getAllText(TextEditingController text) async {
    text.text = 'TESTING';
    text.text = Util.formatResult(await getAllResult());
  }

  void updateOneText(TextEditingController text) async {
    text.text = 'TESTING';
    text.text = Util.formatResult(await updateOneResult());
  }

  void deleteOneText(TextEditingController text) async {
    text.text = 'TESTING';
    text.text = Util.formatResult(await deleteOneResult());
  }

  Future<double> addOneResult() async {
    int result = 0;
    int timer;
    recreateTable();

    for (int i = 0; i < 10; i++) {
      timer = DateTime.now().microsecondsSinceEpoch;
      await addOne(i, "login $i", "email $i", "name $i", 30);
      result += DateTime.now().microsecondsSinceEpoch - timer;
    }

    return result / 10;
  }

  Future<double> getOneResult() async {
    int result = 0;
    int timer;
    await recreateTableWithData();

    for (int i = 0; i < 1000; i++) {
      int j = new Random().nextInt(1000);
      timer = DateTime.now().microsecondsSinceEpoch;
      await getOne(j);
      result += DateTime.now().microsecondsSinceEpoch - timer;
    }

    return result / 1000;
  }

  Future<double> getAllResult() async {
    int result = 0;
    int timer;
    await recreateTableWithData();

    for (int i = 0; i < 1000; i++) {
      timer = DateTime.now().microsecondsSinceEpoch;
      (await getAll()).length;
      result += DateTime.now().microsecondsSinceEpoch - timer;
    }

    return result / 1000;
  }

  Future<double> updateOneResult() async {
    int result = 0;
    int timer;
    await recreateTableWithData();

    for (int i = 0; i < 1000; i++) {
      int j = new Random().nextInt(1000);
      timer = DateTime.now().microsecondsSinceEpoch;
      await updateOne(j, i);
      result += DateTime.now().microsecondsSinceEpoch - timer;
    }

    return result / 1000;
  }

  Future<double> deleteOneResult() async {
    int result = 0;
    int timer;

    for (int i = 0; i < 10; i++) {
      await recreateTableWithData();
      int j = new Random().nextInt(1000);
      timer = DateTime.now().microsecondsSinceEpoch;
      await deleteOne(j);
      result += DateTime.now().microsecondsSinceEpoch - timer;
    }

    return result / 10;
  }

  Future<void> addOne(int id, String login, String email, String name, int age) async {
    await _database.insert(DB.TABLE_NAME,
        {DB.COLUMN_ID: id, DB.COLUMN_LOGIN: login, DB.COLUMN_EMAIL: email, DB.COLUMN_NAME: name, DB.COLUMN_AGE: age});
  }

  Future<List<Map<String, dynamic>>> getOne(int id) async {
    return await _database.query(DB.TABLE_NAME, where: DB.COLUMN_ID + ' = ?', whereArgs: [id]);
  }

  Future<List<Map<String, dynamic>>> getAll() async {
    return await _database.query(DB.TABLE_NAME);
  }

  Future<void> updateOne(int id, int i) async {
    return await _database.update(DB.TABLE_NAME, {DB.COLUMN_NAME: id + i}, where: DB.COLUMN_ID + ' = ?', whereArgs: [id]);
  }

  Future<void> deleteOne(int id) async {
    return await _database.delete(DB.TABLE_NAME, where: DB.COLUMN_ID + ' = ?', whereArgs: [id]);
  }
}

class DB {
  static const String TABLE_NAME = "USERS";
  static const String COLUMN_ID = "ID";
  static const String COLUMN_LOGIN = "LOGIN";
  static const String COLUMN_EMAIL = "EMAIL";
  static const String COLUMN_NAME = "NAME";
  static const String COLUMN_AGE = "AGE";

  static const String SQL_CREATE_TABLE =
      "CREATE TABLE " + TABLE_NAME + " (" +
          COLUMN_ID + " INTEGER PRIMARY KEY, " +
          COLUMN_LOGIN + " TEXT UNIQUE, " +
          COLUMN_EMAIL + " TEXT UNIQUE, " +
          COLUMN_NAME + " TEXT NOT NULL, " +
          COLUMN_AGE + " INTEGER NOT NULL CHECK (" + COLUMN_AGE + " > 0 AND " + COLUMN_AGE + " < 150))";

  static const String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
