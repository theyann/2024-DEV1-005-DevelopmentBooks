package be.yle.devbooks.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class InitController {

    @GetMapping("/")
    fun index(): String {
       return "Everything is set ..."
    }
}