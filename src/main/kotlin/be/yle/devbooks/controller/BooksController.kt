package be.yle.devbooks.controller

import be.yle.devbooks.model.Book
import be.yle.devbooks.service.BookService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class BooksController(
    private val service: BookService,
) {

    @GetMapping("/books")
    fun getAllBooks(): List<Book> {
       return service.getAllBooks()
    }

    @GetMapping("/books/{id}")
    fun getBook(@PathVariable("id") id: Int): ResponseEntity<Book> {
        val book = service.getBookById(id) ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(book)
    }
}