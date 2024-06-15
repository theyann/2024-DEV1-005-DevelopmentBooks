package be.yle.devbooks.utils

import be.yle.devbooks.model.Basket
import be.yle.devbooks.model.BasketItem
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SeriesSplitterTest {

    private lateinit var splitter: SeriesSplitter

    @BeforeEach
    fun setup() {
        splitter = SeriesSplitter()
    }

    @Test
    fun `splitToSeries one copy per title expects only one series`() {
        val given = Basket(setOf(BasketItem(0, 1), BasketItem(1, 1)))
        val expected = listOf(setOf(0, 1))

        splitter.splitToSeries(given) shouldBe expected
    }

    @Test
    fun `splitToSeries kata example`() {
        val given = Basket(setOf(BasketItem(0, 1), BasketItem(1, 1)))
        val expected = listOf(setOf(0, 1))

        splitter.splitToSeries(given) shouldBe expected
    }

}