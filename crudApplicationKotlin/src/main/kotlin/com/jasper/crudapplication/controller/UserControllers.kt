package com.jasper.crudapplication.controller

import com.jasper.crudapplication.user.model.User
import com.jasper.crudapplication.user.model.sha256
import com.jasper.crudapplication.user.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*


@RestController
@RequestMapping("/api/user")
class UserController(private val userRepository: UserRepository) {

    @GetMapping("/")
    fun findAll() = userRepository.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id : Long) = userRepository.findById(id).get();

    @GetMapping("/login/{password}")
    fun findByPassword(@PathVariable password: String) =
            userRepository.findByPassword(password)
                    ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This user does not exist")

    @PostMapping("/create", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createUser(@RequestBody user: User): User {
        if (!user.username.isEmpty()) {
            user.password = user.password.sha256();
            userRepository.save(user);
        }else{
            throw ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Expected username, no username found")
        }
        return user;
    }

    @PostMapping("/update")
    fun updateUser(@RequestBody user: User): User {
        if (user.id != null) {
            return userRepository.findById(user.id!!).get()
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "This user does not exist")
        }
    }

    @DeleteMapping("/delete/{id}")
    fun deleteUser(@PathVariable id: Long): String {
        val userOptional: Optional<User> = userRepository.findById(id)
        userRepository.delete(userOptional.get())
        return "Succesfully deleted user with username : " + userOptional.get().username
    }
}
