package be.yle.devbooks.service

import be.yle.devbooks.model.Basket
import be.yle.devbooks.model.BasketItem
import io.kotest.matchers.shouldBe
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
                Arguments.of( // No Discount
                    Basket(listOf()),
                    0
                ),
                Arguments.of( // No Discount
                    Basket(listOf(BasketItem(0, 1))),
                    50
                ),
                Arguments.of( // No Discount
                    Basket(listOf(BasketItem(0, 2))),
                    100
                ),
                Arguments.of( // 5% Discount
                    Basket(listOf(
                        BasketItem(0, 1),
                        BasketItem(1, 1)
                    )),
                    95
                ),
                Arguments.of( // 10% Discount
                    Basket(listOf(
                        BasketItem(0, 1),
                        BasketItem(1, 1),
                        BasketItem(2, 1)
                    )),
                    135
                ),
                Arguments.of( // 20% Discount
                    Basket(listOf(
                        BasketItem(0, 1),
                        BasketItem(1, 1),
                        BasketItem(2, 1),
                        BasketItem(3, 1)
                    )),
                    160
                ),
                Arguments.of( // 25% Discount
                    Basket(listOf(
                        BasketItem(0, 1),
                        BasketItem(1, 1),
                        BasketItem(2, 1),
                        BasketItem(3, 1),
                        BasketItem(4, 1)
                    )),
                    187.5
                )
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
    fun calculateTotalPriceTest(given: Basket, expected: Double) {
        calculator.calculateTotalPrice(given) shouldBe expected
    }
}