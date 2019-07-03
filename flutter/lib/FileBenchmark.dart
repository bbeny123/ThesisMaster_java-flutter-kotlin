import 'dart:io';

import 'package:path_provider/path_provider.dart';

class FileBenchmark {
  static const int _times = 50;
  int _timer = 0;

  static const String FILE_NAME = "test_file";
  static const String FILE_NAME2 = "test_file2";
  static const String FILE_GENERATED = "test_file_generated";
  String _path;

  FileBenchmark() {
    getApplicationDocumentsDirectory().then((p) => _path = p.path);
  }

  Future<double> saveFile() async {
    int result = 0;

    List<int> file = _readFile(FILE_GENERATED);
    for (int i = 0; i < _times; i++) {
      _timer = DateTime.now().microsecondsSinceEpoch;
      _saveFile(FILE_NAME, file);
      result += DateTime.now().microsecondsSinceEpoch - _timer;
      _deleteFile(FILE_NAME);
    }

    return result / _times;
  }

  Future<double> readFile() async {
    int result = 0;
    int dummy = 0;

    for (int i = 0; i < _times; i++) {
      _timer = DateTime.now().microsecondsSinceEpoch;
      dummy += _readFile(FILE_GENERATED).length;
      result += DateTime.now().microsecondsSinceEpoch - _timer;
    }

    print(dummy);
    return result / _times;
  }

  Future<double> deleteFile() async {
    int result = 0;

    List<int> file = _readFile(FILE_GENERATED);
    for (int i = 0; i < _times; i++) {
      _saveFile(FILE_NAME, file);
      _timer = DateTime.now().microsecondsSinceEpoch;
      _deleteFile(FILE_NAME);
      result += DateTime.now().microsecondsSinceEpoch - _timer;
    }

    return result / _times;
  }

  void _saveFile(String name, List<int> data) {
    return File('$_path/$name').writeAsBytesSync(data);
  }

  List<int> _readFile(String name) {
    return File('$_path/$name').readAsBytesSync();
  }

  void _deleteFile(String name) {
    return File('$_path/$name').deleteSync();
  }
}
