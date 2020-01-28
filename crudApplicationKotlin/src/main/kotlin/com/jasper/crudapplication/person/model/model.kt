package com.jasper.crudapplication.person.model

import com.fasterxml.jackson.annotation.JsonCreator
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity
class Person(
        var password: String = "",
        @Column(unique=true) var username: String = "",
        var firstname: String = "",
        var lastname: String = "",
        var description: String? = null,
        @Id @GeneratedValue var id: Long? = null) : Serializable {
}

// New data class for incoming user
data class NewUserDTO @JsonCreator constructor(
        val username: String = "",
        val password: String = "",
        var firstname: String = "",
        var lastname: String = ""
)