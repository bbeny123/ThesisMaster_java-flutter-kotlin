package kotlin_test

import kotlin_test.data.USERS
import kotlinx.serialization.Serializable

@Serializable
data class User(
    var id: Long,
    var login: String? = null,
    var email: String? = null,
    var name: String,
    var age: Int
)

fun USERS.toUser(): User = User(
    id = ID,
    login = LOGIN,
    email = EMAIL,
    name = NAME,
    age = AGE
)
