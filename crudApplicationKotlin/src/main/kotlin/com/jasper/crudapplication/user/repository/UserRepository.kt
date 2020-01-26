package com.jasper.crudapplication.user.repository

import com.jasper.crudapplication.user.model.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findByPassword(password: String): User?
}

