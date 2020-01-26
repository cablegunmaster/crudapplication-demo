package com.jasper.crudapplication.user.repository

import com.jasper.crudapplication.user.model.User

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
class UserRepositoryTests @Autowired constructor(
        val entityManager: TestEntityManager,
        val userRepository: UserRepository) {

    @Test
    fun `When findByLogin then return User`() {
        val juergen = User("password","Jasper", "Lankhorst")
        entityManager.persist(juergen)

        entityManager.flush()
        val user = userRepository.findByPassword(juergen.password)
        assertThat(user).isEqualTo(juergen)
    }

}