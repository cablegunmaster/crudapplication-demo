package com.jasper.crudapplication.person.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.jasper.crudapplication.sha256
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity
class Person(
        pwd: String = "",
        @Column(unique=true) var username: String = "",
        var firstname: String = "",
        var lastname: String = "",
        var description: String? = null,
        @Id @GeneratedValue var id: Long? = null) : Serializable {

    var password: String = pwd
        //Custom Setter
        set(value){
            field = if(value.length > 6) value.sha256() else throw IllegalArgumentException("Password is too small")
        }
}