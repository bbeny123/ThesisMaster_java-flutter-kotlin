import 'dart:math';

import 'package:flutter/material.dart';

import 'CollectionsBenchmark.dart';
import 'DBBenchmark.dart';
import 'DeserBenchmark.dart';
import 'FileBenchmark.dart';
import 'RESTBenchmark.dart';

const String NO_DATA = 'NO DATA';

void main() => runApp(MaterialApp(home: MyApp()));

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Flutter Test'),
      ),
      body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              _menu(context, 'COLLECTIONS', Collections()),
              Padding(padding: EdgeInsets.all(8.0)),
              _menu(context, 'REST', REST()),
              Padding(padding: EdgeInsets.all(8.0)),
              _menu(context, 'DB', DB()),
              Padding(padding: EdgeInsets.all(8.0)),
              _menu(context, 'SERIALIZATION\nDESERIALIZATION', Deser()),
              Padding(padding: EdgeInsets.all(8.0)),
              _menu(context, 'FILE', File())
            ],
          )
      ),
    );
  }
}

class Collections extends StatelessWidget {
  var benchmark = new CollectionsBenchmark();
  var addOne = new TextEditingController(text: NO_DATA);
  var readAll = new TextEditingController(text: NO_DATA);
  var readRandom = new TextEditingController(text: NO_DATA);
  var removeOne = new TextEditingController(text: NO_DATA);
  var filter = new TextEditingController(text: NO_DATA);
  var sort = new TextEditingController(text: NO_DATA);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("Collections"),
        ),
        body: Center(
          child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                _row(addOne, 'ADD ONE', () => benchmark.add10kObjects()),
                Padding(padding: EdgeInsets.all(8.0)),
                _row(readAll, 'READ ALL', () => benchmark.readAll()),
                Padding(padding: EdgeInsets.all(8.0)),
                _row(readRandom, 'READ RANDOM', () => benchmark.read1kRandom()),
                Padding(padding: EdgeInsets.all(8.0)),
                _row(removeOne, 'REMOVE ONE', () => benchmark.remove1kRandom()),
                Padding(padding: EdgeInsets.all(8.0)),
                _row(filter, 'FILTER', () => benchmark.filter()),
                Padding(padding: EdgeInsets.all(8.0)),
                _row(sort, 'SORT', () => benchmark.sort())
              ]
          ),
        )
    );
  }
}

class REST extends StatelessWidget {
  var benchmark = new RESTBenchmark();
  var get = new TextEditingController(text: NO_DATA);
  var post = new TextEditingController(text: NO_DATA);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("REST"),
        ),
        body: Center(
            child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  _row(get, 'GET', () => benchmark.get()),
                  Padding(padding: EdgeInsets.all(8.0)),
                  _row(post, 'POST', () => benchmark.post())
                ]
            )
        )
    );
  }
}

class DB extends StatelessWidget {
  var benchmark = new DBBenchmark();
  var addOne = new TextEditingController(text: NO_DATA);
  var getOne = new TextEditingController(text: NO_DATA);
  var getAll = new TextEditingController(text: NO_DATA);
  var updateOne = new TextEditingController(text: NO_DATA);
  var deleteOne = new TextEditingController(text: NO_DATA);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("DB"),
        ),
        body: Center(
          child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                _row(addOne, 'ADD ONE', () => benchmark.addOneResult()),
                Padding(padding: EdgeInsets.all(8.0)),
                _row(getOne, 'GET ONE', () => benchmark.getOneResult()),
                Padding(padding: EdgeInsets.all(8.0)),
                _row(getAll, 'GET ALL', () => benchmark.getAllResult()),
                Padding(padding: EdgeInsets.all(8.0)),
                _row(updateOne, 'UPDATE ONE', () => benchmark.updateOneResult()),
                Padding(padding: EdgeInsets.all(8.0)),
                _row(deleteOne, 'DELETE ONE', () => benchmark.deleteOneResult())
              ]
          ),
        )
    );
  }
}

class Deser extends StatelessWidget {
  var benchmark = new DeserBenchmark();
  var serialization = new TextEditingController(text: NO_DATA);
  var deserialization = new TextEditingController(text: NO_DATA);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("Serialization & Deserialization"),
        ),
        body: Center(
            child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  _row(serialization, 'SERIALIZATION', () => benchmark.serialize()),
                  Padding(padding: EdgeInsets.all(8.0)),
                  _row(deserialization, 'DESERIALIZATION', () => benchmark.deserialize())
                ]
            )
        )
    );
  }
}

class File extends StatelessWidget {
  var benchmark = new FileBenchmark();
  var save = new TextEditingController(text: NO_DATA);
  var read = new TextEditingController(text: NO_DATA);
  var delete = new TextEditingController(text: NO_DATA);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("File"),
        ),
        body: Center(
          child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                _row(save, 'SAVE', () => benchmark.saveFileTest()),
                Padding(padding: EdgeInsets.all(8.0)),
                _row(read, 'READ', () => benchmark.readFileTest()),
                Padding(padding: EdgeInsets.all(8.0)),
                _row(delete, 'DELETE', () => benchmark.deleteFileTest())
              ]
          ),
        )
    );
  }
}

Row _row(TextEditingController text, String btnText, double Function() method) {
  return Row(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        ButtonTheme(
            minWidth: 160,
            height: 40,
            buttonColor: Colors.white30,
            child: RaisedButton(child: Text(btnText, textAlign: TextAlign.center), onPressed: () async => _benchmark(text, method))),
        Padding(padding: EdgeInsets.all(8.0)),
        Container(
            width: 160,
            child: TextField(controller: text, textAlign: TextAlign.center))
      ]
  );
}

ButtonTheme _menu(BuildContext ctx, String text, Widget widget) {
  return ButtonTheme(
      minWidth: 160,
      height: 40,
      buttonColor: Colors.white30,
      child: RaisedButton(
          child: Text(text, textAlign: TextAlign.center),
          onPressed: () => Navigator.push(ctx, MaterialPageRoute(builder: (ctx) => widget))
      )
  );
}

void _benchmark(TextEditingController text, double Function() method) {
  text.text = 'TESTING';
  double result = method();
  double mod = pow(10.0, 3);
  text.text = ((result * mod).round().toDouble() / mod).toString() + ' µs';
}