import 'dart:convert';

import 'package:flutter/widgets.dart';

import 'helper/User.dart';
import 'helper/Util.dart';

class DeserBenchmark {

  User _user = new User(1, 'user', 'user@user', 'user', 30);

  void serialize(TextEditingController text) async {
    text.text = 'TESTING';
    text.text = Util.formatResult(serializeTest(_user));
  }

  void deserialize(TextEditingController text) async {
    text.text = 'TESTING';
    text.text = Util.formatResult(deserializeTest(_user));
  }

  double serializeTest(User user) {
    int timer;
    int result = 0;
    String json;

    for(int i = 0; i < 1000; i++) {
      timer = DateTime.now().microsecondsSinceEpoch;
      json = jsonEncode(user);
      result += DateTime.now().microsecondsSinceEpoch - timer;
    }

    print(json.toString());
    return result / 1000;
  }

  double deserializeTest(User user) {
    int timer;
    int result = 0;
    String json = jsonEncode(user);
    User deserialized;

    for(int i = 0; i < 1000; i++) {
      timer = DateTime.now().microsecondsSinceEpoch;
      deserialized = User.fromJson(jsonDecode(json));
      result += DateTime.now().microsecondsSinceEpoch - timer;
    }

    print(deserialized.toString());
    return result / 1000;
  }
  
}