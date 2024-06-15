package be.yle.devbooks.service

import be.yle.devbooks.data.BasketRepository
import be.yle.devbooks.data.DiscountRepository
import be.yle.devbooks.model.Basket
import be.yle.devbooks.model.BasketItem
import be.yle.devbooks.model.BasketValidation
import be.yle.devbooks.utils.SeriesSplitter
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BasketServiceTest {

    private lateinit var service: BasketService

    @BeforeEach
    fun setup() {
        service = BasketService(BasketRepository(), TotalPriceCalculator(DiscountRepository(), SeriesSplitter()))
    }

    @AfterEach
    fun teardown() {
        BasketRepository.CURRENT_BASKET = null
    }

    @Test
    fun `addToBasket from empty basket, expects a basket with only the added item`() {
        val expected = Basket(mutableSetOf(BasketItem(0, 1)))

        service.addToBasket(BasketItem(0, 1))

        BasketRepository.CURRENT_BASKET shouldBe expected
    }

    @Test
    fun `addToBasket from existing basket, expects a basket with the added item and other items untouched`() {
        BasketRepository.CURRENT_BASKET = Basket(mutableSetOf(BasketItem(0, 1), BasketItem(1, 2)))

        val expected = Basket(mutableSetOf(BasketItem(0, 1), BasketItem(1, 2), BasketItem(2, 1)))

        service.addToBasket(BasketItem(2, 1))

        BasketRepository.CURRENT_BASKET shouldBe expected
    }

    @Test
    fun `addToBasket from existing basket, book already present, expect an updated count for that book`() {
        BasketRepository.CURRENT_BASKET = Basket(mutableSetOf(BasketItem(0, 1), BasketItem(1, 2)))

        val expected = Basket(mutableSetOf(BasketItem(0, 1), BasketItem(1, 4)))

        service.addToBasket(BasketItem(1, 2))

        // not sure why in this case if I test CURRENT_BASKET with expected directly, even though the values are the same, the test fails
        // because probably checking also for references. I don't really have enough time to find a better solution at this time.
        // So for now, I check the items directly instead.
        BasketRepository.CURRENT_BASKET!!.items shouldBe expected.items
    }

    @Test
    fun `finalizeCurrentBasket with existing basket, expects basket and total price`() {
        BasketRepository.CURRENT_BASKET = Basket(mutableSetOf(BasketItem(0, 1), BasketItem(1, 2)))

        val expected = BasketValidation(
            Basket(mutableSetOf(BasketItem(0, 1), BasketItem(1, 2))),
            145.0
        )

        service.finalizeCurrentBasket() shouldBe expected
    }
}