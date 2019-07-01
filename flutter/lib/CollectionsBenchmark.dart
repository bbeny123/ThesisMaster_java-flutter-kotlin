import 'dart:math';
import 'package:flutter/widgets.dart';
import 'helper/TestObject.dart';

class CollectionsBenchmark {

  double readAll() {
    double result = 0;
    for (int i = 0; i < 100; i++) {
      result += read10k();
    }
    return result / 100;
  }

  double filter() {
    double result = 0;
    for (int i = 0; i < 100; i++) {
      result += filterTest();
    }
    return result / 100;
  }

  double sort() {
    double result = 0;
    for (int i = 0; i < 100; i++) {
      result += sortTest();
    }
    return result / 100;
  }

  double add10kObjects() {
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

  double read1kRandom() {
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

  double remove1kRandom() {
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