import 'dart:convert';
import 'dart:io';

import 'package:flutter/widgets.dart';
import 'package:http/http.dart' as http;
import 'package:http/http.dart';

import 'helper/User.dart';
import 'helper/Util.dart';

class RESTBenchmark {
  void get(TextEditingController text) async {
    text.text = 'TESTING';
    text.text = Util.formatResult(await getTest());
  }

  void post(TextEditingController text) async {
    text.text = 'TESTING';
    text.text = Util.formatResult(await postTest());
  }

  Future<double> getTest() async {
    int result = 0;
    int timer = 0;
    User user;
    for (int i = 0; i < 1000; i++) {
      timer = DateTime.now().microsecondsSinceEpoch;
      Response response = await http.get('http://10.0.2.2:8080');
      if (response.statusCode == 200) {
        user = User.fromJson(jsonDecode(response.body));
      } else {
        print('GET ERROR');
      }
      result += DateTime.now().microsecondsSinceEpoch - timer;
    }
    print(jsonEncode(user));
    return result / 1000;
  }

  Future<double> postTest() async {
    User user = new User(1, 'user', 'user@user', 'user', 30);
    int result = 0;
    int timer = 0;

    for (int i = 0; i < 1000; i++) {
      timer = DateTime.now().microsecondsSinceEpoch;
      Response response = await http.post('http://10.0.2.2:8080',
          headers: {HttpHeaders.contentTypeHeader: 'application/json'},
          body: jsonEncode(user));
      if (response.statusCode != 200) {
        print('GET ERROR');
      }
      result += DateTime.now().microsecondsSinceEpoch - timer;
    }
    return result / 1000;
  }
}
