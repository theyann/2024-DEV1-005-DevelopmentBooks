package be.yle.devbooks.data

import be.yle.devbooks.model.Book
import org.springframework.stereotype.Repository

@Repository
class BookRepository {
    fun findAll(): List<Book> {
        return ALL_BOOKS
    }

    fun findById(id: Int): Book? {
        return ALL_BOOKS.find { it.id == id }
    }

    companion object {
        val ALL_BOOKS = listOf(
            Book(1, "Clean Code", "Robert Martin", 2008),
            Book(2, "The Clean Coder", "Robert Martin", 2011),
            Book(3, "Clean Architecture", "Robert Martin", 2017),
            Book(4, "Test Driven Development by Example", "Kent Beck", 2003),
            Book(5, "Working Effectively With Legacy Code", "Michael C. Feathers", 2004),
        )
    }
}
