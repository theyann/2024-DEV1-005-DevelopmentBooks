package be.yle.devbooks.controller

import be.yle.devbooks.model.BasketItem
import be.yle.devbooks.model.BasketValidation
import be.yle.devbooks.service.BasketService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BasketController(
    private val service: BasketService
) {

    @PostMapping("/basket")
    fun addToBasket(@RequestBody input: BasketItem): ResponseEntity<Void> {
        val result = service.addToBasket(input)
        return if (result) ResponseEntity.accepted().build() else ResponseEntity.badRequest().build()
    }

    @GetMapping("/basket/finalize")
    fun finalizeBasket(): ResponseEntity<BasketValidation> {
        val validation = service.finalizeCurrentBasket()
        return ResponseEntity.ok(validation)
    }
}