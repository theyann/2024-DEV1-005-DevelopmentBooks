package be.yle.devbooks.service

import be.yle.devbooks.data.BookRepository
import be.yle.devbooks.model.Book
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BookServiceTest {

    private lateinit var repo: BookRepository
    private lateinit var service: BookService

    @BeforeEach
    fun setup() {
        repo = mockk<BookRepository>()
        service = BookService(repo)
    }

    @Test
    fun `getAllBooks expects to get all books from repository`() {
        val books = listOf(Book(0, "Book 1", "Author 1", 2001), Book(1, "Book 2", "Author 2", 2002))

        every { repo.findAll() } returns books

        service.getAllBooks() shouldBe books
    }

    @Test
    fun `getBookById expects to get the book with the given id`() {
        val book = Book(0, "Book 1", "Author 1", 2001)

        every { repo.findById(0) } returns book

        service.getBookById(0) shouldBe book
    }

    @Test
    fun `getBookById expects to get the null when no books found`() {
        every { repo.findById(0) } returns null

        service.getBookById(0) shouldBe null
    }
}