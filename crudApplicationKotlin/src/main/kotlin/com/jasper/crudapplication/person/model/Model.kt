package com.jasper.crudapplication.person.model

import com.jasper.crudapplication.sha256
import java.io.Serializable
import javax.persistence.*


@Entity
class Person(
        pwd: String = "",
        @Column(unique = true) var username: String = "",
        var firstName: String = "",
        var lastName: String = "",
        var description: String? = null,
        @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long? = null) : Serializable {

    var password: String = pwd
        //Custom Setter
        set(value) {
            field = if (value.length > 6) value.sha256() else throw IllegalArgumentException("Password is too small")
        }
}