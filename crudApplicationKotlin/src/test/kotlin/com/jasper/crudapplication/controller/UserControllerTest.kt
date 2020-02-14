package com.jasper.crudapplication.controller

import org.assertj.core.api.Assertions
import org.json.JSONArray
import org.json.JSONObject
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

//JDK 11 httpclient.building.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest() {

    @LocalServerPort
    var port: Int? = null

    @Test
    fun `check endpoint retrieve userList returns a list of persons resource`() {
        val client = HttpClient.newBuilder().build();
        val request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + port + "/api/user/"))
                .build();
        val response = client.send(request, HttpResponse.BodyHandlers.ofString());
        val jsonObject: JSONObject = JSONArray(response.body()).get(0) as JSONObject

        Assertions.assertThat(response.statusCode() == 200)
        Assertions.assertThat(response.body().length == 1)
        checkSingleLineFromUserConfiguration(jsonObject)
    }

    @Test
    fun `check filter resources of user ID = 1 is given back a correct response`() {
        val client = HttpClient.newBuilder().build();
        val request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + port + "/api/user/1"))
                .build();
        val response = client.send(request, HttpResponse.BodyHandlers.ofString());
        val jsonObject = JSONObject(response.body())

        Assertions.assertThat(response.statusCode() == 200)
        Assertions.assertThat(response.body().length == 1)
        checkSingleLineFromUserConfiguration(jsonObject)
    }

    @Test
    fun `check if invalid ID requested gives back a not found page`() {
        val client = HttpClient.newBuilder().build();
        val request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + port + "/api/user/2"))
                .build();
        val response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Assertions.assertThat(response.statusCode() == 404)
        Assertions.assertThat(response.body().isEmpty())
    }

    @Test
    fun `check if password is retrieved by username gets correct password`() {
        val client = HttpClient.newBuilder().build();
        val request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + port + "/api/user/login/smaldini"))
                .build();
        val response = client.send(request, HttpResponse.BodyHandlers.ofString());
        val jsonObject = JSONObject(response.body())
        Assertions.assertThat(response.statusCode() == 200)
        Assertions.assertThat(
                "9a512706b709101c8924bbac477670d76aee49d26648420897e7f3f6f9cabe96".equals(jsonObject.get("password")))
    }

    @Test
    fun `check if password is retrieved by invalid username returns 404`() {
        val client = HttpClient.newBuilder().build();
        val request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + port + "/api/user/login/uioyhiouygoouygu"))
                .build();
        val response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Assertions.assertThat(response.statusCode() == 404)
    }

    fun checkSingleLineFromUserConfiguration(jsonObject: JSONObject) {
        Assertions.assertThat("Stéphane" == jsonObject.get("firstname"))
        Assertions.assertThat(
                "9a512706b709101c8924bbac477670d76aee49d26648420897e7f3f6f9cabe96".equals(jsonObject.get("password")))
        Assertions.assertThat(null == jsonObject.get("description"))
        Assertions.assertThat("id" == jsonObject.get("id"))
        Assertions.assertThat("smaldini" == jsonObject.get("userName"))
        Assertions.assertThat("Maldini" == jsonObject.get("lastName"))
    }
}