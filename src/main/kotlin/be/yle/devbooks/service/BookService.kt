package be.yle.devbooks.service

import be.yle.devbooks.data.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    private val repo: BookRepository
) {

    fun getAllBooks() = repo.findAll()

    fun getBookById(id: Int) = repo.findById(id)

    fun bookIsAvailable(id: Int) = repo.isAvailable(id)
}