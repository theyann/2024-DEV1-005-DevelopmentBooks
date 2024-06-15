package be.yle.devbooks.data

import be.yle.devbooks.model.Book
import org.springframework.stereotype.Repository

@Repository
class BookRepository {
    fun findAll() = ALL_BOOKS

    fun findById(id: Int): Book? {
        return ALL_BOOKS.find { it.id == id }
    }

    companion object {
        val ALL_BOOKS = listOf(
            Book(0, "Clean Code", "Robert Martin", 2008),
            Book(1, "The Clean Coder", "Robert Martin", 2011),
            Book(2, "Clean Architecture", "Robert Martin", 2017),
            Book(3, "Test Driven Development by Example", "Kent Beck", 2003),
            Book(4, "Working Effectively With Legacy Code", "Michael C. Feathers", 2004),
        )
    }
}
