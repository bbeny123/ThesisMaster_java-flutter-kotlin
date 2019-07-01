import 'package:json_annotation/json_annotation.dart';

part 'User.g.dart';

@JsonSerializable()
class User {
  int id;
  String login;
  String email;
  String name;
  int age;

  User.name();

  User(this.id, this.login, this.email, this.name, this.age);

  factory User.fromJson(Map<String, dynamic> json) => _$UserFromJson(json);

  Map<String, dynamic> toJson() => _$UserToJson(this);
}
