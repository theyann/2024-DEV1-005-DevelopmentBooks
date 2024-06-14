package be.yle.devbooks.data

import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BookRepositoryTest {

    private lateinit var repo: BookRepository

    @BeforeEach
    fun setUp() {
        repo = BookRepository()
    }

    @Test
    fun findAll_expect_all_books() {
        val actual = repo.findAll()

        actual shouldContainExactly BookRepository.ALL_BOOKS
    }

    @Test
    fun findById_with_existing_id_expect_one_book() {
        val actual = repo.findById(1)

        actual shouldBe BookRepository.ALL_BOOKS[0]
    }

    @Test
    fun findById_with_wrong_id_expect_null() {
        val actual = repo.findById(-1)

        actual shouldBe null
    }
}