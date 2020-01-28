package com.jasper.crudapplication.person.repository

import com.jasper.crudapplication.person.model.Person
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
class PersonRepositoryTests @Autowired constructor(
        val entityManager: TestEntityManager,
        val personRepository: PersonRepository) {

    @Test
    fun `When findByLogin then return Person`() {
        val person = Person("password", "Jasper",
                "Lankhorst",
                "ok",
                "description",
                null)

        entityManager.persistAndFlush(person)

        assertThat(personRepository.findByUsername(person.username)?.username).isEqualTo("Jasper")
    }

}