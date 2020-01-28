package com.jasper.crudapplication.configuration

import com.jasper.crudapplication.sha256
import com.jasper.crudapplication.person.model.Person
import com.jasper.crudapplication.person.repository.PersonRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.dao.DataIntegrityViolationException


@Configuration
class UserConfiguration {

    private val LOG: Logger = LoggerFactory.getLogger(UserConfiguration::class.java)

    /**
     * https://docs.spring.io/spring-boot/docs/current/reference/html/howto-database-initialization.html#howto-initialize-a-database-using-spring-jdbc
     * Database initialising provided by Spring Boot is meant to be used only with embedded databases. So it more or less assumes that you have to initialise the database on every start of the application.
     *
     * Basically replace this with liquibase in the future?
     */
    @Bean
    fun databaseInitializer(userRepository: PersonRepository) = ApplicationRunner {

        //add basic data, on every startup.
        try {
            userRepository.save(Person("test".sha256(), "smaldini", "Stéphane", "Maldini"))
        } catch (ex: DataIntegrityViolationException) {
            LOG.debug("Primary key is already inserted {} ", ex);
        }
    }
}