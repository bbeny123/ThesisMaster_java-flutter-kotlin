import 'dart:convert';
import 'helper/User.dart';

class DeserBenchmark {

  User _user = new User(1, 'user', 'user@user', 'user', 30);

  Future<double> serialize() async {
    int timer;
    int result = 0;
    String json;

    for(int i = 0; i < 1000; i++) {
      timer = DateTime.now().microsecondsSinceEpoch;
      json = jsonEncode(_user);
      result += DateTime.now().microsecondsSinceEpoch - timer;
    }

    print(json.toString());
    return result / 1000;
  }

  Future<double> deserialize() async {
    int timer;
    int result = 0;
    String json = jsonEncode(_user);
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