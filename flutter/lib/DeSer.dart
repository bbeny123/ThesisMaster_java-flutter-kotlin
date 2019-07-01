import 'dart:convert';

import 'helper/User.dart';

class DeSer {

  double serialize(User user) {
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

  double deserialize(User user) {
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