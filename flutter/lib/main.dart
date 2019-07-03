import 'package:flutter/material.dart';

import 'CollectionBenchmark.dart';
import 'DBBenchmark.dart';
import 'DeSerBenchmark.dart';
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
              _menu(context, 'COLLECTIONS', Collection()),
              Padding(padding: EdgeInsets.all(8.0)),
              _menu(context, 'REST', REST()),
              Padding(padding: EdgeInsets.all(8.0)),
              _menu(context, 'DB', DB()),
              Padding(padding: EdgeInsets.all(8.0)),
              _menu(context, 'SERIALIZATION\nDESERIALIZATION', DeSer()),
              Padding(padding: EdgeInsets.all(8.0)),
              _menu(context, 'FILE', File())
            ],
          )
      ),
    );
  }
}

class Collection extends StatelessWidget {
  final benchmark = CollectionBenchmark();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("Collection"),
        ),
        body: Center(
          child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                _row('ADD ONE', () => benchmark.add10k()),
                Padding(padding: EdgeInsets.all(8.0)),
                _row('READ ALL', () => benchmark.readAll()),
                Padding(padding: EdgeInsets.all(8.0)),
                _row('READ RANDOM', () => benchmark.read10PercentRandom()),
                Padding(padding: EdgeInsets.all(8.0)),
                _row('REMOVE RANDOM', () => benchmark.remove10PercentRandom()),
                Padding(padding: EdgeInsets.all(8.0)),
                _row('FILTER', () => benchmark.filter()),
                Padding(padding: EdgeInsets.all(8.0)),
                _row('SORT', () => benchmark.sort())
              ]
          ),
        )
    );
  }
}

class REST extends StatelessWidget {
  final benchmark = RESTBenchmark();

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
                  _row('GET', () => benchmark.get()),
                  Padding(padding: EdgeInsets.all(8.0)),
                  _row('POST', () => benchmark.post())
                ]
            )
        )
    );
  }
}

class DB extends StatelessWidget {
  final benchmark = DBBenchmark();
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
                _row('INSERT', () => benchmark.insert()),
                Padding(padding: EdgeInsets.all(8.0)),
                _row('GET ONE', () => benchmark.get()),
                Padding(padding: EdgeInsets.all(8.0)),
                _row('GET ALL', () => benchmark.getAll()),
                Padding(padding: EdgeInsets.all(8.0)),
                _row('UPDATE ONE', () => benchmark.update()),
                Padding(padding: EdgeInsets.all(8.0)),
                _row('DELETE ONE', () => benchmark.delete())
              ]
          ),
        )
    );
  }
}

class DeSer extends StatelessWidget {
  final benchmark = DeSerBenchmark();

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
                  _row('SERIALIZATION', () => benchmark.serialize()),
                  Padding(padding: EdgeInsets.all(8.0)),
                  _row('DESERIALIZATION', () => benchmark.deserialize())
                ]
            )
        )
    );
  }
}

class File extends StatelessWidget {
  final benchmark = FileBenchmark();

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
                _row('SAVE', () => benchmark.saveFile()),
                Padding(padding: EdgeInsets.all(8.0)),
                _row('READ', () => benchmark.readFile()),
                Padding(padding: EdgeInsets.all(8.0)),
                _row('DELETE', () => benchmark.deleteFile())
              ]
          ),
        )
    );
  }
}

Row _row(String btnText, Future<double> Function() method) {
  final text = TextEditingController(text: 'NO DATA');
  return Row(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        ButtonTheme(
            minWidth: 160,
            height: 40,
            buttonColor: Colors.white30,
            child: RaisedButton(child: Text(btnText, textAlign: TextAlign.center), onPressed: () => _benchmark(text, method))),
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

void _benchmark(TextEditingController text, Future<double> Function() method) async {
  text.text = 'Benchmarking';
  text.text = (await method() / 1000).toStringAsFixed(3) + ' ms';
}