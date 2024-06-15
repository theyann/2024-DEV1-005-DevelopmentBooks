package be.yle.devbooks.service

import be.yle.devbooks.model.Basket
import be.yle.devbooks.model.BasketItem
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class TotalPriceCalculatorTest {
    companion object {
        @JvmStatic
        fun provideArgumentsForCalculateTotalPrice(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(Basket(listOf()), 0),
                Arguments.of(Basket(listOf(BasketItem(0, 1))), 50)
            )
        }
    }

    private lateinit var calculator: TotalPriceCalculator

    @BeforeEach
    fun setup() {
        calculator = TotalPriceCalculator()
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForCalculateTotalPrice")
    fun calculateTotalPrice(given: Basket, expected: Int) {
        calculator.calculateTotalPrice(given) shouldBe expected
    }
}