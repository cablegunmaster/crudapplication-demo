package com.jasper.crudapplication.controller

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
    fun findById(@PathVariable id: Long): Person {
        val optional = personRepository.findById(id)
        if(optional.isPresent){
            return optional.get();
        }
        throw ResponseStatusException(HttpStatus.NOT_FOUND, "This person does not exist")
    }

    @GetMapping("/login/{username}")
    fun findByUsername(@PathVariable username: String) = personRepository.findByUsername(username)
                    ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This person does not exist")

    @PostMapping("/create", consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody person: Person): Person {
        if (!person.username.isEmpty()) {
            personRepository.save(person);

        } else {
            throw ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Expected username, no username found")
        }
        return person;
    }

    @PatchMapping("/update")
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@PathVariable id: Long) {
        val personOptional: Optional<Person> = personRepository.findById(id)
        if (personOptional.isPresent) {
            personRepository.delete(personOptional.get())
        }
    }
}
