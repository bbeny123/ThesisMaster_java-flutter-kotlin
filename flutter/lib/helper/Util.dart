import 'dart:math';

class Util {

  static const String TESTING = "TESTING";

  static String formatResult(double result){
    double mod = pow(10.0, 3);
    return ((result * mod).round().toDouble() / mod).toString();
  }

}