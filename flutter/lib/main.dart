import 'package:flutter/material.dart';

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
              ButtonTheme(
                  minWidth: 160,
                  height: 40,
                  child: RaisedButton(
                      child: Text('COLLECTIONS', textAlign: TextAlign.center),
                      onPressed: () {
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                              builder: (context) => Collections()),
                        );
                      })
              ),
              Padding(
                padding: EdgeInsets.all(8.0),
              ),
              ButtonTheme(
                  minWidth: 160,
                  height: 40,
                  child: RaisedButton(
                      child: Text('REST', textAlign: TextAlign.center),
                      onPressed: () {
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                              builder: (context) => REST()),
                        );
                      })
              ),
              Padding(
                padding: EdgeInsets.all(8.0),
              ),
              ButtonTheme(
                  minWidth: 160,
                  height: 40,
                  child: RaisedButton(
                      child: Text('DB', textAlign: TextAlign.center),
                      onPressed: () {
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                              builder: (context) => DB()),
                        );
                      })
              ),
              Padding(
                padding: EdgeInsets.all(8.0),
              ),
              ButtonTheme(
                  minWidth: 160,
                  height: 40,
                  child: RaisedButton(
                      child: Text('SERIALIZATION\nDESERIALIZATION',
                          textAlign: TextAlign.center),
                      onPressed: () {
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                              builder: (context) => Deser()),
                        );
                      })
              ),
              Padding(
                padding: EdgeInsets.all(8.0),
              ),
              ButtonTheme(
                  minWidth: 160,
                  height: 40,
                  child: RaisedButton(
                      child: Text('FILE', textAlign: TextAlign.center),
                      onPressed: () {
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                              builder: (context) => File()),
                        );
                      })
              )
            ],
          )
      ),
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
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    ButtonTheme(
                        minWidth: 160,
                        height: 40,
                        buttonColor: Colors.white30,
                        child: RaisedButton(
                            child: Text('ADD ONE', textAlign: TextAlign.center),
                            onPressed: () {
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                    builder: (context) => SecondRoute()),
                              );
                            })
                    ),
                    Padding(
                      padding: EdgeInsets.all(8.0),
                    ),
                    Container(
                        width: 160,
                        child: Text('NO DATA', textAlign: TextAlign.center,)
                    )
                  ],
                ),
                Padding(
                  padding: EdgeInsets.all(8.0),
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    ButtonTheme(
                        minWidth: 160,
                        height: 40,
                        buttonColor: Colors.white30,
                        child: RaisedButton(
                            child: Text('READ ALL', textAlign: TextAlign.center),
                            onPressed: () {
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                    builder: (context) => SecondRoute()),
                              );
                            })
                    ),
                    Padding(
                      padding: EdgeInsets.all(8.0),
                    ),
                    Container(
                        width: 160,
                        child: Text('NO DATA', textAlign: TextAlign.center,)
                    )
                  ],
                ),
                Padding(
                  padding: EdgeInsets.all(8.0),
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    ButtonTheme(
                        minWidth: 160,
                        height: 40,
                        buttonColor: Colors.white30,
                        child: RaisedButton(
                            child: Text('READ RANDOM', textAlign: TextAlign.center),
                            onPressed: () {
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                    builder: (context) => SecondRoute()),
                              );
                            })
                    ),
                    Padding(
                      padding: EdgeInsets.all(8.0),
                    ),
                    Container(
                        width: 160,
                        child: Text('NO DATA', textAlign: TextAlign.center,)
                    )
                  ],
                ),
                Padding(
                  padding: EdgeInsets.all(8.0),
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    ButtonTheme(
                        minWidth: 160,
                        height: 40,
                        buttonColor: Colors.white30,
                        child: RaisedButton(
                            child: Text('REMOVE', textAlign: TextAlign.center),
                            onPressed: () {
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                    builder: (context) => SecondRoute()),
                              );
                            })
                    ),
                    Padding(
                      padding: EdgeInsets.all(8.0),
                    ),
                    Container(
                        width: 160,
                        child: Text('NO DATA', textAlign: TextAlign.center,)
                    )
                  ],
                ),
                Padding(
                  padding: EdgeInsets.all(8.0),
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    ButtonTheme(
                        minWidth: 160,
                        height: 40,
                        buttonColor: Colors.white30,
                        child: RaisedButton(
                            child: Text('FILTER', textAlign: TextAlign.center),
                            onPressed: () {
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                    builder: (context) => SecondRoute()),
                              );
                            })
                    ),
                    Padding(
                      padding: EdgeInsets.all(8.0),
                    ),
                    Container(
                        width: 160,
                        child: Text('NO DATA', textAlign: TextAlign.center,)
                    )
                  ],
                ),
                Padding(
                  padding: EdgeInsets.all(8.0),
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    ButtonTheme(
                        minWidth: 160,
                        height: 40,
                        buttonColor: Colors.white30,
                        child: RaisedButton(
                            child: Text('SORT', textAlign: TextAlign.center),
                            onPressed: () {
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                    builder: (context) => SecondRoute()),
                              );
                            })
                    ),
                    Padding(
                      padding: EdgeInsets.all(8.0),
                    ),
                    Container(
                        width: 160,
                        child: Text('NO DATA', textAlign: TextAlign.center,)
                    )
                  ],
                )
              ]
          ),
        )
    );
  }
}

class DB extends StatelessWidget {
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
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    ButtonTheme(
                        minWidth: 160,
                        height: 40,
                        buttonColor: Colors.white30,
                        child: RaisedButton(
                            child: Text('ADD ONE', textAlign: TextAlign.center),
                            onPressed: () {
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                    builder: (context) => SecondRoute()),
                              );
                            })
                    ),
                    Padding(
                      padding: EdgeInsets.all(8.0),
                    ),
                    Container(
                        width: 160,
                        child: Text('NO DATA', textAlign: TextAlign.center,)
                    )
                  ],
                ),
                Padding(
                  padding: EdgeInsets.all(8.0),
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    ButtonTheme(
                        minWidth: 160,
                        height: 40,
                        buttonColor: Colors.white30,
                        child: RaisedButton(
                            child: Text('GET ONE', textAlign: TextAlign.center),
                            onPressed: () {
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                    builder: (context) => SecondRoute()),
                              );
                            })
                    ),
                    Padding(
                      padding: EdgeInsets.all(8.0),
                    ),
                    Container(
                        width: 160,
                        child: Text('NO DATA', textAlign: TextAlign.center,)
                    )
                  ],
                ),
                Padding(
                  padding: EdgeInsets.all(8.0),
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    ButtonTheme(
                        minWidth: 160,
                        height: 40,
                        buttonColor: Colors.white30,
                        child: RaisedButton(
                            child: Text('GET ALL', textAlign: TextAlign.center),
                            onPressed: () {
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                    builder: (context) => SecondRoute()),
                              );
                            })
                    ),
                    Padding(
                      padding: EdgeInsets.all(8.0),
                    ),
                    Container(
                        width: 160,
                        child: Text('NO DATA', textAlign: TextAlign.center,)
                    )
                  ],
                ),
                Padding(
                  padding: EdgeInsets.all(8.0),
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    ButtonTheme(
                        minWidth: 160,
                        height: 40,
                        buttonColor: Colors.white30,
                        child: RaisedButton(
                            child: Text('UPDATE ONE', textAlign: TextAlign.center),
                            onPressed: () {
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                    builder: (context) => SecondRoute()),
                              );
                            })
                    ),
                    Padding(
                      padding: EdgeInsets.all(8.0),
                    ),
                    Container(
                        width: 160,
                        child: Text('NO DATA', textAlign: TextAlign.center,)
                    )
                  ],
                ),
                Padding(
                  padding: EdgeInsets.all(8.0),
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    ButtonTheme(
                        minWidth: 160,
                        height: 40,
                        buttonColor: Colors.white30,
                        child: RaisedButton(
                            child: Text('DELETE ONE', textAlign: TextAlign.center),
                            onPressed: () {
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                    builder: (context) => SecondRoute()),
                              );
                            })
                    ),
                    Padding(
                      padding: EdgeInsets.all(8.0),
                    ),
                    Container(
                        width: 160,
                        child: Text('NO DATA', textAlign: TextAlign.center,)
                    )
                  ],
                )
              ]
          ),
        )
    );
  }
}

class File extends StatelessWidget {
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
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    ButtonTheme(
                        minWidth: 160,
                        height: 40,
                        buttonColor: Colors.white30,
                        child: RaisedButton(
                            child: Text('SAVE', textAlign: TextAlign.center),
                            onPressed: () {
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                    builder: (context) => SecondRoute()),
                              );
                            })
                    ),
                    Padding(
                      padding: EdgeInsets.all(8.0),
                    ),
                    Container(
                        width: 160,
                        child: Text('NO DATA', textAlign: TextAlign.center,)
                    )
                  ],
                ),
                Padding(
                  padding: EdgeInsets.all(8.0),
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    ButtonTheme(
                        minWidth: 160,
                        height: 40,
                        buttonColor: Colors.white30,
                        child: RaisedButton(
                            child: Text('READ', textAlign: TextAlign.center),
                            onPressed: () {
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                    builder: (context) => SecondRoute()),
                              );
                            })
                    ),
                    Padding(
                      padding: EdgeInsets.all(8.0),
                    ),
                    Container(
                        width: 160,
                        child: Text('NO DATA', textAlign: TextAlign.center,)
                    )
                  ],
                ),
                Padding(
                  padding: EdgeInsets.all(8.0),
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    ButtonTheme(
                        minWidth: 160,
                        height: 40,
                        buttonColor: Colors.white30,
                        child: RaisedButton(
                            child: Text('DELETE', textAlign: TextAlign.center),
                            onPressed: () {
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                    builder: (context) => SecondRoute()),
                              );
                            })
                    ),
                    Padding(
                      padding: EdgeInsets.all(8.0),
                    ),
                    Container(
                        width: 160,
                        child: Text('NO DATA', textAlign: TextAlign.center,)
                    )
                  ],
                )
              ]
          ),
        )
    );
  }
}

class Deser extends StatelessWidget {
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
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    ButtonTheme(
                        minWidth: 160,
                        height: 40,
                        buttonColor: Colors.white30,
                        child: RaisedButton(
                            child: Text('SERIALIZATION', textAlign: TextAlign.center),
                            onPressed: () {
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                    builder: (context) => SecondRoute()),
                              );
                            })
                    ),
                    Padding(
                      padding: EdgeInsets.all(8.0),
                    ),
                    Container(
                        width: 160,
                        child: Text('NO DATA', textAlign: TextAlign.center,)
                    )
                  ],
                ),
                Padding(
                  padding: EdgeInsets.all(8.0),
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    ButtonTheme(
                        minWidth: 160,
                        height: 40,
                        buttonColor: Colors.white30,
                        child: RaisedButton(
                            child: Text('DESERIALIZATION', textAlign: TextAlign.center),
                            onPressed: () {
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                    builder: (context) => SecondRoute()),
                              );
                            })
                    ),
                    Padding(
                      padding: EdgeInsets.all(8.0),
                    ),
                    Container(
                        width: 160,
                        child: Text('NO DATA', textAlign: TextAlign.center,)
                    )
                  ],
                )
              ]
          ),
        )
    );
  }
}

class REST extends StatelessWidget {
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
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    ButtonTheme(
                        minWidth: 160,
                        height: 40,
                        buttonColor: Colors.white30,
                        child: RaisedButton(
                            child: Text('GET', textAlign: TextAlign.center),
                            onPressed: () {
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                    builder: (context) => SecondRoute()),
                              );
                            })
                    ),
                    Padding(
                      padding: EdgeInsets.all(8.0),
                    ),
                    Container(
                        width: 160,
                        child: Text('NO DATA', textAlign: TextAlign.center,)
                    )
                  ],
                ),
                Padding(
                  padding: EdgeInsets.all(8.0),
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    ButtonTheme(
                        minWidth: 160,
                        height: 40,
                        buttonColor: Colors.white30,
                        child: RaisedButton(
                            child: Text('POST', textAlign: TextAlign.center),
                            onPressed: () {
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                    builder: (context) => SecondRoute()),
                              );
                            })
                    ),
                    Padding(
                      padding: EdgeInsets.all(8.0),
                    ),
                    Container(
                        width: 160,
                        child: Text('NO DATA', textAlign: TextAlign.center,)
                    )
                  ],
                )
              ]
          ),
        )
    );
  }
}