package be.yle.devbooks.data

import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class BookRepositoryTest {

    private lateinit var repo: BookRepository

    @BeforeEach
    fun setUp() {
        repo = BookRepository()
    }

    @Test
    fun `findAll expect all books`() {
        val actual = repo.findAll()

        actual shouldContainExactly BookRepository.ALL_BOOKS
    }

    @Test
    fun `findById with existing id expect one book`() {
        val actual = repo.findById(0)

        actual shouldBe BookRepository.ALL_BOOKS[0]
    }

    @Test
    fun `findById with wrong id expect null`() {
        val actual = repo.findById(-1)

        actual shouldBe null
    }

    @ParameterizedTest
    @CsvSource(value = ["0, true", "1, true", "9, false"])
    fun `isAvailable expects result corresponding to parameter`(given: Int, expected: Boolean) {
        repo.isAvailable(given) shouldBe expected
    }

}