package com.jasper.crudapplication.controller

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationHTMLTestIsPageLoading(@Autowired val restTemplate: TestRestTemplate) {

    @Test
    fun `Assert page title, content and status code`() {
        val entity = restTemplate.getForEntity<String>("/")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains("Crudapplication")
    }

    @Test
    fun `Assert page is containing create`() {
        val entity = restTemplate.getForEntity<String>("/user/create")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains("Create user")
    }

    @Test
    fun `Assert page contains List of users`() {
        val entity = restTemplate.getForEntity<String>("/user/list")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains("List of users")
    }
}