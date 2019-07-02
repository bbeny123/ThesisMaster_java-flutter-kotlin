package kotlin_test

@Serializable
data class User {

    var id: Long? = null
    var login: String? = null
    var email: String? = null
    var name: String? = null
    var age: Int? = null

    constructor()

    constructor(id: Long?, login: String, email: String, name: String, age: Int?) {
        this.id = id
        this.login = login
        this.email = email
        this.name = name
        this.age = age
    }

    override fun toString(): String {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\''.toString() +
                ", email='" + email + '\''.toString() +
                ", name='" + name + '\''.toString() +
                ", age=" + age +
                '}'.toString()
    }
}
