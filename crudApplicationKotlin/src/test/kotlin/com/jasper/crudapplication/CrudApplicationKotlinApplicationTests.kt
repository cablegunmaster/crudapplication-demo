package com.jasper.crudapplication

import com.jasper.crudapplication.person.model.Person
import com.jasper.crudapplication.person.repository.PersonRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
class CrudApplicationKotlinApplicationTests @Autowired constructor(
        val entityManager: TestEntityManager,
        val personRepository: PersonRepository) {

    @Test
    fun givenPerson_whenSaved_thenFound() {
        val personToSave = Person("5000", "John")
        entityManager.persistAndFlush(personToSave)
        val personFound = personRepository.findByUsername(personToSave.username)
        entityManager.refresh(personFound)

        Assertions.assertThat(personToSave.equals(personFound))
    }
}
