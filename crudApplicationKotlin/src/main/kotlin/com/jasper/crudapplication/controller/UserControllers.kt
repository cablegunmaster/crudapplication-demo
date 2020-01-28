package com.jasper.crudapplication.controller

import com.jasper.crudapplication.sha256
import com.jasper.crudapplication.person.model.Person
import com.jasper.crudapplication.person.repository.PersonRepository
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*


@RestController
@RequestMapping("/api/user")
class PersonController(private val personRepository: PersonRepository) {

    @GetMapping("/")
    fun findAll() = personRepository.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) = personRepository.findById(id).get();

    @GetMapping("/login/{username}")
    fun findByUsername(@PathVariable username: String) =
            personRepository.findByUsername(username)
                    ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This person does not exist")

    @PostMapping("/create", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createUser(@RequestBody person: Person): Person {
        if (!person.username.isEmpty()) {
            person.password = person.password.sha256();
            personRepository.save(person);
        } else {
            throw ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Expected username, no username found")
        }
        return person;
    }

    @PostMapping("/update")
    fun updateUser(@RequestBody person: Person): Person {
        if (person.id != null) {
            val personOptional: Optional<Person> = personRepository.findById(person.id!!)
            if (personOptional.isPresent) {
                return personOptional.get()
            }
        }
        throw ResponseStatusException(HttpStatus.NOT_FOUND, "This person does not exist")
    }

    @DeleteMapping("/delete/{id}")
    fun deleteUser(@PathVariable id: Long): String {
        val personOptional: Optional<Person> = personRepository.findById(id)
        if (personOptional.isPresent) {
            personRepository.delete(personOptional.get())
            return "Succesfully deleted user with username : " + personOptional.get().username
        }
        throw ResponseStatusException(HttpStatus.NOT_FOUND, "This person does not exist")
    }
}
