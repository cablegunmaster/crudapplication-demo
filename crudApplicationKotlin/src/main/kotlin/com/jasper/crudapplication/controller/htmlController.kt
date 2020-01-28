package com.jasper.crudapplication.controller

import com.jasper.crudapplication.person.model.Person
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/user")
class HtmlController(var userController: PersonController) {

    @GetMapping("/")
    fun someCrudController(model: Model): String {
        model["title"] = "Example page for Crud applicatie"
        return "index"
    }

    @GetMapping("/create")
    fun createUserOverView(model: Model): String {
        model["title"] = "Create user"
        return "user/create"
    }

    @PostMapping("/create", consumes = ["application/json"])
    fun createPostUser(model: Model, @RequestBody person: Person) {
        userController.createUser(person)
    }

    @GetMapping("/list")
    fun indexOfAllUsers(model: Model): String {
        model["title"] = "List of users"
        model["userList"] = userController.findAll();
        return "user/overview"
    }

    @GetMapping("/update/{id}")
    fun indexUpdateUser(model: Model, @PathVariable id : Long): String {
        model["title"] = "Update user"
        model["userDto"] = userController.findById(id)
        return "user/update"
    }

    @DeleteMapping("/delete/{id}")
    fun deleteUser(model: Model, @PathVariable id: Long): String {
        model["title"] = "Delete user"
        userController.deleteUser(id)
        return "index"
    }

}
