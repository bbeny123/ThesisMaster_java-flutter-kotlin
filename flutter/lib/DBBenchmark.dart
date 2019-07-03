import 'dart:math';

import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';

import 'helper/User.dart';

class DBBenchmark {
  static const int _times = 1000;
  static const int _tableSize = 10000;
  int _timer = 0;

  Database _db;

  DBBenchmark() {
    _openDb();
  }

  void _openDb() async {
    _db = await openDatabase(join(await getDatabasesPath(), 'db_flutter.db'),
        onCreate: (db, version) {
      return db.execute(DB.SQL_CREATE_TABLE);
    }, version: 1);
  }

  Future<double> insert() async {
    int result = 0;

    await _clearTable();
    User user;
    for (int i = 0; i < _times; i++) {
      user = _dummyUser(i);
      _timer = DateTime.now().microsecondsSinceEpoch;
      await _insert(user);
      result += DateTime.now().microsecondsSinceEpoch - _timer;
    }

    return result / _times;
  }

  Future<double> get() async {
    int result = 0;

    await _prepareData(_tableSize);
    int dummy = 0;
    int id;
    for (int i = 0; i < _times; i++) {
      id = Random().nextInt(_tableSize);
      _timer = DateTime.now().microsecondsSinceEpoch;
      dummy += (await _get(id)).id;
      result += DateTime.now().microsecondsSinceEpoch - _timer;
    }

    print(dummy);
    return result / _times;
  }

  Future<double> getAll() async {
    int result = 0;

    await _prepareData(_tableSize);
    int dummy = 0;
    for (int i = 0; i < _times / 20; i++) {
      _timer = DateTime.now().microsecondsSinceEpoch;
      dummy += (await _getAll()).length;
      result += DateTime.now().microsecondsSinceEpoch - _timer;
    }

    print(dummy);
    return result / (_times / 20);
  }

  Future<double> update() async {
    int result = 0;

    await _prepareData(_tableSize);
    int id;
    for (int i = 0; i < _times; i++) {
      id = Random().nextInt(_tableSize);
      _timer = DateTime.now().microsecondsSinceEpoch;
      await _update(id, i);
      result += DateTime.now().microsecondsSinceEpoch - _timer;
    }

    return result / _times;
  }

  Future<double> delete() async {
    int result = 0;

    await _prepareData(_tableSize + _times);
    int id;
    for (int i = 0; i < _times; i++) {
      id = Random().nextInt(_tableSize);
      _timer = DateTime.now().microsecondsSinceEpoch;
      await _delete(id);
      result += DateTime.now().microsecondsSinceEpoch - _timer;
    }

    return result / _times;
  }

  Future<void> _insert(User user) async {
    await _db.insert(DB.TABLE_NAME, _toMap(user));
  }

  Future<User> _get(int id) async {
    var query = await _db.query(DB.TABLE_NAME, where: DB.COLUMN_ID + ' = ?', whereArgs: [id]);
    return query.isNotEmpty ? _fromMap(query.first) : null;
  }

  Future<List<User>> _getAll() async {
    return (await _db.query(DB.TABLE_NAME)).map((q) => _fromMap(q)).toList();
  }

  Future<void> _update(int id, int i) async {
    return await _db.update(DB.TABLE_NAME, {DB.COLUMN_NAME: id + i}, where: DB.COLUMN_ID + ' = ?', whereArgs: [id]);
  }

  Future<void> _delete(int id) async {
    return await _db.delete(DB.TABLE_NAME, where: DB.COLUMN_ID + ' = ?', whereArgs: [id]);
  }

  Future<void> _clearTable() async {
    await _db.execute(DB.SQL_CLEAR_TABLE);
  }

  Future<void> _prepareData(int size) async {
    await _clearTable();

    var batch = _db.batch();
    for (int i = 0; i < size; i++) {
      batch.insert(DB.TABLE_NAME, _toMap(_dummyUser(i)));
    }
    await batch.commit(noResult: true);
  }

  User _dummyUser(int i) {
    return User(i, "login $i", "email $i", "name $i", 30);
  }

  User _fromMap(Map<String, dynamic> query) {
    return User(query[DB.COLUMN_ID], query[DB.COLUMN_LOGIN], query[DB.COLUMN_EMAIL], query[DB.COLUMN_NAME], query[DB.COLUMN_AGE]);
  }

  Map<String, dynamic> _toMap(User user) {
    return <String, dynamic>{
      DB.COLUMN_ID: user.id,
      DB.COLUMN_LOGIN: user.login,
      DB.COLUMN_EMAIL: user.email,
      DB.COLUMN_NAME: user.name,
      DB.COLUMN_AGE: user.age
    };
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
      "CREATE TABLE $TABLE_NAME (" +
          "$COLUMN_ID INTEGER PRIMARY KEY, " +
          "$COLUMN_LOGIN TEXT UNIQUE, " +
          "$COLUMN_EMAIL TEXT UNIQUE, " +
          "$COLUMN_NAME TEXT NOT NULL, " +
          "$COLUMN_AGE INTEGER NOT NULL CHECK ($COLUMN_AGE > 0 AND $COLUMN_AGE < 150))";

  static const String SQL_CLEAR_TABLE = "DELETE FROM $TABLE_NAME";
}
