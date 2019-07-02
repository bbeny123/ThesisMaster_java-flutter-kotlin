import 'dart:io';
import 'package:path_provider/path_provider.dart';

class FileBenchmark {

  String _path;

  FileBenchmark() {
    setPath();
  }

  void setPath() async {
    _path = (await getApplicationDocumentsDirectory()).path;
  }

  Future<double> saveFileTest() async {
    int result = 0;
    int timer;
    List<int> file = readFile();

    for (int i = 0; i < 10; i++) {
      timer = DateTime.now().microsecondsSinceEpoch;
      saveFile(file);
      result += DateTime.now().microsecondsSinceEpoch - timer;
      deleteFile();
    }

    return result / 10;
  }

  Future<double> readFileTest() async {
    int result = 0;
    int timer;

    for (int i = 0; i < 10; i++) {
      timer = DateTime.now().microsecondsSinceEpoch;
      readFile();
      result += DateTime.now().microsecondsSinceEpoch - timer;
    }

    return result / 10;
  }

  Future<double> deleteFileTest() async {
    int result = 0;
    int timer;
    List<int> file = readFile();

    for (int i = 0; i < 10; i++) {
      saveFile(file);
      timer = DateTime.now().microsecondsSinceEpoch;
      deleteFile();
      result += DateTime.now().microsecondsSinceEpoch - timer;
    }

    return result / 10;
  }

  void saveFile(List<int> data) async {
    File file = File('$_path/test_file');
    file.writeAsBytesSync(data);
  }

  List<int> readFile() {
    File file = File('$_path/test_file2');
    return file.readAsBytesSync();
  }

  void deleteFile() {
    File file = File('$_path/test_file');
    return file.deleteSync();
  }

}