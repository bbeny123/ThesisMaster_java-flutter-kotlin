import 'dart:math';

import 'helper/TestObject.dart';

class CollectionBenchmark {
  static const int _times = 10000;
  static const int _timesShort = 100;
  static const int _listSize = 10000;
  int _timer = 0;

  Future<double> add10k() async {
    _timer = DateTime.now().microsecondsSinceEpoch;
    _add(_times);
    _timer = DateTime.now().microsecondsSinceEpoch - _timer;

    return _timer / _times;
  }

  Future<double> readAll() async {
    List<TestObject> list = _generateList(_times);
    int dummy;

    _timer = DateTime.now().microsecondsSinceEpoch;
    dummy = _read(list);
    _timer = DateTime.now().microsecondsSinceEpoch - _timer;

    print(dummy);
    return _timer / _times;
  }

  Future<double> read10PercentRandom() async {
    List<TestObject> list = _generateList(_times * 10);
    List<int> toRead = _generateRandomIndexes(_times, _times * 10, false);
    int dummy;

    _timer = DateTime.now().microsecondsSinceEpoch;
    dummy = _readRandom(list, toRead);
    _timer = DateTime.now().microsecondsSinceEpoch - _timer;

    print(dummy);
    return _timer / _times;
  }

  Future<double> remove10PercentRandom() async {
    List<TestObject> list = _generateList(_times * 10);
    List<int> toRemove = _generateRandomIndexes(_times, _times * 10, true);

    _timer = DateTime.now().microsecondsSinceEpoch;
    _removeRandom(list, toRemove);
    _timer = DateTime.now().microsecondsSinceEpoch - _timer;

    return _timer / _times;
  }

  Future<double> filter() async {
    int result = 0;

    int minIndex = _listSize ~/ 2;
    List<TestObject> list;
    for (int i = 0; i < _timesShort; i++) {
      list = _generateList(_listSize);
      _timer = DateTime.now().microsecondsSinceEpoch;
      _filter(list, minIndex);
      result += DateTime.now().microsecondsSinceEpoch - _timer;
    }

    return result / _timesShort;
  }

  Future<double> sort() async {
    int result = 0;

    List<TestObject> list = _generateList(_listSize);
    for (int i = 0; i < _timesShort; i++) {
      list.shuffle();
      _timer = DateTime.now().microsecondsSinceEpoch;
      _sort(list);
      result += DateTime.now().microsecondsSinceEpoch - _timer;
    }
    return result / _timesShort;
  }

  int _add(int times) {
    List<TestObject> list = List();
    for(int i = 0; i < times; i++) {
      list.add(TestObject(i, 'item $i'));
    }
    return list.length;
  }

  int _read(List<TestObject> list) {
    int dummy = 0;
    for (TestObject i in list) {
      dummy += i.index;
    }
    return dummy;
  }

  int _readRandom(List<TestObject> list, List<int> toRead) {
    int dummy = 0;
    for (int i in toRead) {
      dummy += list[i].index;
    }
    return dummy;
  }

  int _removeRandom(List<TestObject> list, List<int> toRemove) {
    for (int i in toRemove) {
      list.removeAt(i);
    }
    return list.length;
  }

  int _filter(List<TestObject> list, int minIndex) {
    list.removeWhere((i) => i.index > minIndex);
    return list.length;
  }

  int _sort(List<TestObject> list) {
    list.sort((a, b) => a.index.compareTo(b.index));
    return list.last.index;
  }

  List<TestObject> _generateList(int size) {
    return List.generate(size, (i) => new TestObject(i, 'item $i'));
  }

  List<int> _generateRandomIndexes(int size, int maxIndex, bool reduce) {
    return List.generate(size, (i) => Random().nextInt(maxIndex - (reduce ? i : 0)));
  }

}