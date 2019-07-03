import 'dart:convert';
import 'dart:io';

import 'package:http/http.dart' as http;
import 'package:http/http.dart';

import 'helper/User.dart';

class RESTBenchmark {
  static const int _times = 500;
  int _timer = 0;

  static const String _url = 'http://10.0.2.2:8080';

  Future<double> get() async {
    int result = 0;
    int dummy = 0;

    for (int i = 0; i < _times; i++) {
      _timer = DateTime.now().microsecondsSinceEpoch;
      dummy += (await _getCall()).id;
      result += DateTime.now().microsecondsSinceEpoch - _timer;
    }

    print(dummy);
    return result / _times;
  }

  Future<double> post() async {
    int result = 0;

    final User user = User(1, 'user', 'user@user', 'user', 30);
    for (int i = 0; i < _times; i++) {
      _timer = DateTime.now().microsecondsSinceEpoch;
      await _postCall(user);
      result += DateTime.now().microsecondsSinceEpoch - _timer;
    }

    return result / _times;
  }

  Future<User> _getCall() async {
    User user;

    Response response = await http.get(_url);
    if (response.statusCode == HttpStatus.ok) {
      user = User.fromJson(jsonDecode(response.body));
    } else {
      print('GET ERROR');
    }

    return user;
  }

  Future<void> _postCall(User user) async {
    Response response = await http.post(_url,
        headers: {HttpHeaders.contentTypeHeader: ContentType.json.value},
        body: jsonEncode(user));
    if (response.statusCode != HttpStatus.ok) {
      print('POST ERROR');
    }
  }
}
