package be.yle.devbooks.data

import be.yle.devbooks.model.Basket
import be.yle.devbooks.model.BasketItem
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BasketRepositoryTest {

    private lateinit var repo: BasketRepository

    @BeforeEach
    fun setup() {
        repo = BasketRepository()
    }

    @AfterEach
    fun teardown() {
        BasketRepository.CURRENT_BASKET = null
    }

    @Test
    fun `save expects current basket to be same as given`() {
        val given = Basket(mutableSetOf(BasketItem(0, 2), BasketItem(1, 1)))

        repo.save(given)

        BasketRepository.CURRENT_BASKET shouldBe given
    }

    @Test
    fun `getBasket the basket returned should be null initially`() {
        repo.getBasket() shouldBe null
    }

    @Test
    fun `getBasket the basket returned should be the current basket`() {
        val given = Basket(mutableSetOf(BasketItem(0, 2), BasketItem(1, 1)))
        BasketRepository.CURRENT_BASKET = given

        repo.getBasket() shouldBe given
    }

    @Test
    fun `deleteBasket expects basket to be null`() {
        BasketRepository.CURRENT_BASKET = Basket(mutableSetOf(BasketItem(0, 2), BasketItem(1, 1)))

        repo.deleteBasket()

        repo.getBasket() shouldBe null
    }
}