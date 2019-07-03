import 'dart:convert';

import 'helper/User.dart';

class DeSerBenchmark {

  static const int _times = 10000;
  int _timer = 0;

  static User _user = User(1, 'user', 'user@user', 'user', 30);

  Future<double> serialize() async {
    int result = 0;
    String dummy;

    for(int i = 0; i < _times; i++) {
      _timer = DateTime.now().microsecondsSinceEpoch;
      dummy = _serialize(_user);
      result += DateTime.now().microsecondsSinceEpoch - _timer;
    }

    print(dummy);
    return result / _times;
  }

  Future<double> deserialize() async {
    int result = 0;
    int dummy = 0;

    String json = _serialize(_user);
    for(int i = 0; i < _times; i++) {
      _timer = DateTime.now().microsecondsSinceEpoch;
      dummy += _deserialize(json).id;
      result += DateTime.now().microsecondsSinceEpoch - _timer;
    }

    print(dummy);
    return result / _times;
  }

  String _serialize(User user) {
    return jsonEncode(user);
  }

  User _deserialize(String json) {
    return User.fromJson(jsonDecode(json));
  }
  
}