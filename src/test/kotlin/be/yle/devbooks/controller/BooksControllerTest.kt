package be.yle.devbooks.controller

import be.yle.devbooks.model.Book
import be.yle.devbooks.service.BookService
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(BooksController::class)
class BooksControllerTest {

    @TestConfiguration
    class TestConfig {
        @Bean
        fun service() = mockk<BookService>()
    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var service: BookService

    @Test
    fun `Spring context loaded`() {
    }

    @Test
    fun `getAllBooks with 2 books expects full json response`() {
        val expected = """
           [{"id":0,"title":"Book 1","author":"Author 1","publicationDate":2001},{"id":1,"title":"Book 2","author":"Author 2","publicationDate":2002}]
        """.trimIndent()

        every { service.getAllBooks() } returns listOf(
            Book(0, "Book 1", "Author 1", 2001),
            Book(1, "Book 2", "Author 2", 2002),
        )

        val actual = mockMvc.perform(get("/books"))
            .andExpect(status().isOk)
            .andDo(print())
            .andReturn().response.contentAsString

        actual shouldBe expected
    }

    @Test
    fun `getAllBooks with no books expects empty array json response`() {
        val expected = "[]"

        every { service.getAllBooks() } returns emptyList()

        val actual = mockMvc.perform(get("/books"))
            .andExpect(status().isOk)
            .andDo(print())
            .andReturn().response.contentAsString

        actual shouldBe expected
    }

    @Test
    fun `getBook with existing id expects proper json response`() {
        val expected = """
           {"id":0,"title":"Book 1","author":"Author 1","publicationDate":2001}
        """.trimIndent()

        every { service.getBookById(0) } returns Book(0, "Book 1", "Author 1", 2001)

        val actual = mockMvc.perform(get("/books/0"))
            .andExpect(status().isOk)
            .andDo(print())
            .andReturn().response.contentAsString

        actual shouldBe expected
    }

    @Test
    fun `getBook with non existing id expects 404`() {
        every { service.getBookById(0) } returns null

        mockMvc.perform(get("/books/0"))
            .andExpect(status().isNotFound)
    }
}