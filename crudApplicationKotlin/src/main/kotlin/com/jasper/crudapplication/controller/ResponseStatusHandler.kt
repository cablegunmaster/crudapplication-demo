package com.jasper.crudapplication.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ErrorController() {

    //todo expand to not only have 404 but also 500 and appropiate status codes, depending on what statuscode is actually given instead of forcing it 404.
    @GetMapping("/error")
    fun someCrudController(model: Model): String {
        model["title"] = "404 - Example page for Crud applicatie"
        return "error"
    }
}

