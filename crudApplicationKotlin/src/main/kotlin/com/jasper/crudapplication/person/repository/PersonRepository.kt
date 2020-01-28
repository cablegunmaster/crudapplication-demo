package com.jasper.crudapplication.person.repository

import com.jasper.crudapplication.person.model.Person
import org.springframework.data.repository.CrudRepository

interface PersonRepository : CrudRepository<Person, Long> {
    fun findByUsername(username: String): Person?
}

