package com.jasper.crudapplication.user.model

import com.fasterxml.jackson.annotation.JsonCreator
import java.io.Serializable
import java.security.MessageDigest
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class User(
        var password: String = "",
        @Column(unique=true) var username: String = "",
        var firstname: String = "",
        var lastname: String = "",
        var description: String? = null,
        @Id @GeneratedValue var id: Long? = null) : Serializable

// New data class for incoming user
data class NewUserDTO @JsonCreator constructor(
        val username: String = "",
        val password: String = "",
        var firstname: String = "",
        var lastname: String = ""
)

fun String.md5(): String {
    return hashString(this, "MD5")
}

fun String.sha256(): String {
    return hashString(this, "SHA-256")
}

private fun hashString(input: String, algorithm: String): String {
    return MessageDigest
            .getInstance(algorithm)
            .digest(input.toByteArray())
            .fold("", { str, it -> str + "%02x".format(it) })
}