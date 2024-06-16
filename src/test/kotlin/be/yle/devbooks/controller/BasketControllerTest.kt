package be.yle.devbooks.controller

import be.yle.devbooks.model.Basket
import be.yle.devbooks.model.BasketItem
import be.yle.devbooks.model.BasketValidation
import be.yle.devbooks.service.BasketService
import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(BasketController::class)
class BasketControllerTest {
    @TestConfiguration
    class TestConfig {
        @Bean
        fun service() = mockk<BasketService>()
    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var service: BasketService

    @Test
    fun `Spring context loaded`() {
    }

    @Test
    fun `addToBasket successful, expects accepted`() {
        val given = BasketItem(0, 1)

        every { service.addToBasket(given) } returns true

        mockMvc.perform(
            post("/basket")
                .contentType(MediaType.APPLICATION_JSON)
                .content(given.toJson())
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isAccepted)
    }

    @Test
    fun `addToBasket failed, expects bad request`() {
        val given = BasketItem(0, 1)

        every { service.addToBasket(given) } returns false

        mockMvc.perform(
            post("/basket")
                .contentType(MediaType.APPLICATION_JSON)
                .content(given.toJson())
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest)
    }


    @Test
    fun `finalizeBasket, expects json response`() {
        val result = BasketValidation(
            Basket(mutableSetOf(BasketItem(0, 1), BasketItem(1, 2))),
            145.0
        )

        val expected = """
            {"basket":{"items":[{"bookId":0,"count":1},{"bookId":1,"count":2}]},"totalPrice":145.0}
        """.trimIndent()

        every { service.finalizeCurrentBasket() } returns result

        val actual = mockMvc.perform(get("/basket/finalize"))
            .andExpect(status().isOk)
            .andDo(MockMvcResultHandlers.print())
            .andReturn().response.contentAsString

        actual shouldBe expected
    }

    private fun BasketItem.toJson(): String = try {
        ObjectMapper().writeValueAsString(this)
    } catch (e: Exception) {
        throw RuntimeException(e)
    }
}