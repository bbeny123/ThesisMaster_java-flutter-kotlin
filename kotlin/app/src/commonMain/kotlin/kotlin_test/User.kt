package kotlin_test

import kotlinx.serialization.Serializable

@Serializable
data class User(
    var id: Long,
    var login: String? = null,
    var email: String? = null,
    var name: String? = null,
    var age: Int? = null
)
