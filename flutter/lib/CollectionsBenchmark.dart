import 'dart:math';
import 'helper/TestObject.dart';

class CollectionsBenchmark {

  Future<double> readAll() async {
    double result = 0;
    for (int i = 0; i < 100; i++) {
      result += read10k();
    }
    return result / 100;
  }

  Future<double> filter() async {
    double result = 0;
    for (int i = 0; i < 100; i++) {
      result += filterTest();
    }
    return result / 100;
  }

  Future<double> sort() async {
    double result = 0;
    for (int i = 0; i < 100; i++) {
      result += sortTest();
    }
    return result / 100;
  }

  Future<double> add10kObjects() async {
    int timer = DateTime.now().microsecondsSinceEpoch;
    List<TestObject> list = new List();
    for(int i = 0; i < 10000; i++) {
      list.add(new TestObject(i, 'item $i'));
    }
    timer = DateTime.now().microsecondsSinceEpoch - timer;

    print(list.length);
    return timer / 10000;
  }

  double read10k() {
    int dummy = 0;
    List<TestObject> list = new List();
    for (int i = 0; i < 10000; i++) {
      list.add(new TestObject(i, 'item $i'));
    }

    int timer = DateTime.now().microsecondsSinceEpoch;
    for (TestObject item in list) {
      dummy += item.index;
    }
    timer = DateTime.now().microsecondsSinceEpoch - timer;

//    print(dummy);
    return timer / 1.0;
  }

  Future<double> read1kRandom() async {
    int dummy = 0;
    List<TestObject> list = new List();
    List<int> toRead = new List();
    for (int i = 0; i < 10000; i++) {
      list.add(new TestObject(i, 'item $i'));
    }
    for (int i = 0; i < 1000; i++) {
      toRead.add(new Random().nextInt(10000));
    }

    int timer = DateTime.now().microsecondsSinceEpoch;
    for (int i in toRead) {
      dummy += list[i].index;
    }
    timer = DateTime.now().microsecondsSinceEpoch - timer;

    print(dummy);
    return timer / 1000;
  }

  Future<double> remove1kRandom() async {
    List<TestObject> list = new List();
    List<int> toRemove = new List();
    for (int i = 0; i < 10000; i++) {
      list.add(new TestObject(i, 'item $i'));
    }
    for (int i = 0; i < 1000; i++) {
      toRemove.add(new Random().nextInt(10000 - i));
    }

    int timer = DateTime.now().microsecondsSinceEpoch;
    for (int i in toRemove) {
      list.removeAt(i);
    }
    timer = DateTime.now().microsecondsSinceEpoch - timer;

    print(list.length);
    return timer / 1000;
  }

  double filterTest() {
    List<TestObject> list = new List();
    for (int i = 0; i < 10000; i++) {
      list.add(new TestObject(i, 'item $i'));
    }
    list.shuffle();

    int timer = DateTime.now().microsecondsSinceEpoch;
    list = list.where((i) => i.index > 5000).toList();
    timer = DateTime.now().microsecondsSinceEpoch - timer;

//    print(list.length);
    return timer / 1.0;
  }

  double sortTest() {
    List<TestObject> list = new List();
    for (int i = 0; i < 10000; i++) {
      list.add(new TestObject(i, 'item $i'));
    }
    list.shuffle();

    int timer = DateTime.now().microsecondsSinceEpoch;
    list.sort((a, b) => a.index.compareTo(b.index));
    timer = DateTime.now().microsecondsSinceEpoch - timer;

//    print(list.length);
    return timer / 1.0;
  }

}