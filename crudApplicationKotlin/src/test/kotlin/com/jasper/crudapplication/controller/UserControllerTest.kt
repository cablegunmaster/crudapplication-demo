package com.jasper.crudapplication.controller

import com.jasper.crudapplication.JsonBuilder
import com.jasper.crudapplication.httpUtil.httpSendDelete
import com.jasper.crudapplication.httpUtil.httpSendGET
import com.jasper.crudapplication.httpUtil.httpSendPost
import org.json.JSONArray
import org.json.JSONObject
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort

//JDK 11 httpclient.building.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest() {

    @LocalServerPort
    var port: Int = 0;
    var baseUrl: String = "http://localhost";

    @Test
    fun `check endpoint retrieve userList returns a list of persons resource`() {
        val response = httpSendGET(baseUrl + ":" + port + "/api/user/")
        val jsonObject: JSONObject = JSONArray(response?.body()).get(0) as JSONObject

        assert(response?.statusCode() == 200)
        assert(jsonObject.length() == 6)
        checkSingleUserConfiguration(jsonObject)
    }

    @Test
    fun `check filter resources of user ID = 1 is given back a correct response`() {
        val response = httpSendGET(baseUrl + ":" + port + "/api/user/1")
        val jsonObject = JSONObject(response?.body())

        assert(response?.statusCode() == 200)
        assert(jsonObject.length() == 6)
        checkSingleUserConfiguration(jsonObject)
    }

    @Test
    fun `check if invalid ID requested gives back a 404 page`() {
        val response = httpSendGET(baseUrl + ":" + port + "/api/user/245675457")
        if (response != null) {
            assert(response.statusCode() == 404)
        }
    }

    @Test
    fun `check if password is retrieved by username gets correct password`() {
        val response = httpSendGET(baseUrl + ":" + port + "/api/user/login/smaldini")
        val jsonObject = JSONObject(response?.body())
        assert(response?.statusCode() == 200)
        assert(
                "9a512706b709101c8924bbac477670d76aee49d26648420897e7f3f6f9cabe96".equals(jsonObject.get("password")))
    }

    @Test
    fun `check if password is retrieved by invalid username returns 404`() {
        val response = httpSendGET(baseUrl + ":" + port + "/api/user/login/uioyhiouygoouygu")
        assert(response?.statusCode() == 404)
    }

    @Test
    fun `check create user with invalid data is not allowed`() {
        val userToCreate = JsonBuilder(
                "firstName" to "test56879",
                "lastName" to "iriene",
                "description" to "the one who just needed a nickname",
                "pwd" to "1234567"
        ).toString()

        val response = httpSendPost(baseUrl + ":" + port + "/api/user/create", userToCreate)
        if (response != null) {
            assert(response.statusCode().equals(417))
        } else {
            assert(false)
        }
    }

    @Test
    fun `check create user with full valid data is correct`() {
        val userToCreate = JsonBuilder(
                "firstName" to "henk",
                "lastName" to "schouten",
                "description" to "the one who just needed a nickname",
                "pwd" to "1234567",
                "username" to "Henkie"
        ).toString()

        val response = httpSendPost(baseUrl + ":" + port + "/api/user/create", userToCreate)
        if (response != null) {
            assert(response.statusCode().equals(201))
        }
    }

    @Test
    fun `check create and delete user is successful by using user CRUD`() {
        val userToCreate = JsonBuilder(
                "firstName" to "create1",
                "lastName" to "create1",
                "description" to "the one who just needed a nickname",
                "pwd" to "1234567",
                "username" to "Henkie5786"
        ).toString()

        val responseCreate = httpSendPost(baseUrl + ":" + port + "/api/user/create", userToCreate)
        if (responseCreate != null) {
            assert(responseCreate.statusCode().equals(201))

            val jsonObject = JSONObject(responseCreate?.body())
            assert(jsonObject.get("id") != null)
            val IdNumber: Int = jsonObject.get("id") as Int

            val response = httpSendDelete(baseUrl + ":" + port + "/api/user/delete/" + IdNumber)
            val response404 = httpSendGET(baseUrl + ":" + port + "/api/user/" + IdNumber)
            if (response != null && response404 != null) {
                assert(response.statusCode() == 204)
                assert(response404.statusCode() == 404)
            }
        }
    }

    fun checkSingleUserConfiguration(jsonObject: JSONObject) {
        assert("Stéphane" == jsonObject.get("firstName"))
        assert(
                "9a512706b709101c8924bbac477670d76aee49d26648420897e7f3f6f9cabe96".equals(jsonObject.get("password")))
        assert(jsonObject.get("description").equals(null))
        assert(1 == jsonObject.get("id"))
        assert("smaldini" == jsonObject.get("username"))
        assert("Maldini" == jsonObject.get("lastName"))
    }

    fun checkUserConfigurationByAllConfiguration(jsonObject: JSONObject) {
        assert("Stéphane" == jsonObject.get("firstName"))
        assert(
                "9a512706b709101c8924bbac477670d76aee49d26648420897e7f3f6f9cabe96".equals(jsonObject.get("password")))
        assert(null == jsonObject.get("description"))
        assert("id" == jsonObject.get("id"))
        assert("smaldini" == jsonObject.get("username"))
        assert("Maldini" == jsonObject.get("lastName"))
    }
}