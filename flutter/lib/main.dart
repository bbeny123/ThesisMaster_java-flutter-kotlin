import 'package:flutter/material.dart';

import 'CollectionsBenchmark.dart';
import 'DBBenchmark.dart';
import 'DeserBenchmark.dart';
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
          _buttonNavigate(context, 'COLLECTIONS', Collections()),
          Padding(padding: EdgeInsets.all(8.0)),
          _buttonNavigate(context, 'REST', REST()),
          Padding(padding: EdgeInsets.all(8.0)),
          _buttonNavigate(context, 'DB', DB()),
          Padding(padding: EdgeInsets.all(8.0)),
          _buttonNavigate(context, 'SERIALIZATION\nDESERIALIZATION', Deser()),
          Padding(padding: EdgeInsets.all(8.0)),
          _buttonNavigate(context, 'FILE', File())
        ],
      )),
    );
  }
}

class SecondRoute extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Collections"),
      ),
      body: Center(
        child: RaisedButton(
          onPressed: () {
            Navigator.pop(context);
          },
          child: Text('Go back!'),
        ),
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
          child: Column(mainAxisAlignment: MainAxisAlignment.center, children: [
            _row(
                context,
                RaisedButton(
                    child: _buttonText('ADD ONE'),
                    onPressed: () async => benchmark.addOne(addOne)),
                addOne),
            Padding(padding: EdgeInsets.all(8.0)),
            _row(
                context,
                RaisedButton(
                    child: _buttonText('READ ALL'),
                    onPressed: () async => benchmark.readAll(readAll)),
                readAll),
            Padding(padding: EdgeInsets.all(8.0)),
            _row(
                context,
                RaisedButton(
                    child: _buttonText('READ RANDOM'),
                    onPressed: () async => benchmark.readRandom(readRandom)),
                readRandom),
            Padding(padding: EdgeInsets.all(8.0)),
            _row(
                context,
                RaisedButton(
                    child: _buttonText('REMOVE ONE'),
                    onPressed: () async => benchmark.removeOne(removeOne)),
                removeOne),
            Padding(padding: EdgeInsets.all(8.0)),
            _row(
                context,
                RaisedButton(
                    child: _buttonText('FILTER'),
                    onPressed: () async => benchmark.filter(filter)),
                filter),
            Padding(padding: EdgeInsets.all(8.0)),
            _row(
                context,
                RaisedButton(
                    child: _buttonText('SORT'),
                    onPressed: () async => benchmark.sort(sort)),
                sort)
          ]),
        ));
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
          child: Column(mainAxisAlignment: MainAxisAlignment.center, children: [
            _row(
                context,
                RaisedButton(
                    child: _buttonText('ADD ONE'),
                    onPressed: () async => benchmark.addOneText(addOne)),
                addOne),
            Padding(padding: EdgeInsets.all(8.0)),
            _row(
                context,
                RaisedButton(
                    child: _buttonText('GET ONE'),
                    onPressed: () async => benchmark.getOneText(getOne)),
                getOne),
            Padding(padding: EdgeInsets.all(8.0)),
            _row(
                context,
                RaisedButton(
                    child: _buttonText('GET ALL'),
                    onPressed: () async => benchmark.getAllText(getAll)),
                getAll),
            Padding(padding: EdgeInsets.all(8.0)),
            _row(
                context,
                RaisedButton(
                    child: _buttonText('UPDATE ONE'),
                    onPressed: () async => benchmark.updateOneText(updateOne)),
                updateOne),
            Padding(padding: EdgeInsets.all(8.0)),
            _row(
                context,
                RaisedButton(
                    child: _buttonText('DELETE ONE'),
                    onPressed: () async => benchmark.deleteOneText(deleteOne)),
                deleteOne)
          ]),
        ));
  }
}

class File extends StatelessWidget {
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
          child: Column(mainAxisAlignment: MainAxisAlignment.center, children: [
            _row(
                context,
                RaisedButton(
                    child: _buttonText('SAVE'),
                    onPressed: () => _navigate(context, SecondRoute())),
                save),
            Padding(padding: EdgeInsets.all(8.0)),
            _row(
                context,
                RaisedButton(
                    child: _buttonText('READ'),
                    onPressed: () => _navigate(context, SecondRoute())),
                read),
            Padding(padding: EdgeInsets.all(8.0)),
            _row(
                context,
                RaisedButton(
                    child: _buttonText('DELETE'),
                    onPressed: () => _navigate(context, SecondRoute())),
                delete)
          ]),
        ));
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
          child: Column(mainAxisAlignment: MainAxisAlignment.center, children: [
            _row(
                context,
                RaisedButton(
                    child: _buttonText('SERIALIZATION'),
                    onPressed: () => benchmark.serialize(serialization)),
                serialization),
            Padding(padding: EdgeInsets.all(8.0)),
            _row(
                context,
                RaisedButton(
                    child: _buttonText('DESERIALIZATION'),
                    onPressed: () => benchmark.deserialize(deserialization)),
                deserialization)
          ]),
        ));
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
          child: Column(mainAxisAlignment: MainAxisAlignment.center, children: [
            _row(
                context,
                RaisedButton(
                    child: _buttonText('GET'),
                    onPressed: () => benchmark.get(get)),
                get),
            Padding(padding: EdgeInsets.all(8.0)),
            _row(
                context,
                RaisedButton(
                    child: _buttonText('POST'),
                    onPressed: () => benchmark.post(post)),
                post)
          ]),
        ));
  }
}

Row _row(BuildContext context, RaisedButton button,
    TextEditingController textField) {
  return Row(
    mainAxisAlignment: MainAxisAlignment.center,
    children: [
      ButtonTheme(
          minWidth: 160,
          height: 40,
          buttonColor: Colors.white30,
          child: button),
      Padding(
        padding: EdgeInsets.all(8.0),
      ),
      Container(
          width: 160,
          child: TextField(controller: textField, textAlign: TextAlign.center))
    ],
  );
}

ButtonTheme _buttonNavigate(
    BuildContext context, String buttonText, Widget widget) {
  return ButtonTheme(
      minWidth: 160,
      height: 40,
      buttonColor: Colors.white30,
      child: RaisedButton(
          child: _buttonText(buttonText),
          onPressed: () => _navigate(context, widget)));
}

Text _buttonText(String text) {
  return Text(text, textAlign: TextAlign.center);
}

void _navigate(BuildContext context, StatelessWidget widget) {
  Navigator.push(
    context,
    MaterialPageRoute(builder: (context) => widget),
  );
}
