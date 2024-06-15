package be.yle.devbooks.service

import be.yle.devbooks.data.DiscountRepository
import be.yle.devbooks.model.Basket
import be.yle.devbooks.model.BasketItem
import be.yle.devbooks.utils.SeriesSplitter
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
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
                    Basket(setOf()),
                    0
                ),
                Arguments.of( // No Discount
                    Basket(setOf(BasketItem(0, 1))),
                    50
                ),
                Arguments.of( // No Discount
                    Basket(setOf(BasketItem(0, 2))),
                    100
                ),
                Arguments.of( // 5% Discount
                    Basket(
                        setOf(
                            BasketItem(0, 1),
                            BasketItem(1, 1)
                        )
                    ),
                    95
                ),
                Arguments.of( // 10% Discount
                    Basket(
                        setOf(
                            BasketItem(0, 1),
                            BasketItem(1, 1),
                            BasketItem(2, 1)
                        )
                    ),
                    135
                ),
                Arguments.of( // 20% Discount
                    Basket(
                        setOf(
                            BasketItem(0, 1),
                            BasketItem(1, 1),
                            BasketItem(2, 1),
                            BasketItem(3, 1)
                        )
                    ),
                    160
                ),
                Arguments.of( // 25% Discount
                    Basket(
                        setOf(
                            BasketItem(0, 1),
                            BasketItem(1, 1),
                            BasketItem(2, 1),
                            BasketItem(3, 1),
                            BasketItem(4, 1)
                        )
                    ),
                    187.5
                )
            )
        }
    }

    private lateinit var calculator: TotalPriceCalculator

    @BeforeEach
    fun setup() {
        calculator = TotalPriceCalculator(DiscountRepository(), SeriesSplitter())
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForCalculateTotalPrice")
    fun `calculateTotalPrice simple cases`(given: Basket, expected: Double) {
        calculator.calculateTotalPrice(given) shouldBe expected
    }

    @Test
    fun `calculateTotalPrice 4 books of which 3 are different expect 10pct discount on 3 and 1 full price`() {
        val given = Basket(
            setOf(
                BasketItem(0, 2),
                BasketItem(1, 1),
                BasketItem(2, 1),
            )
        )

        calculator.calculateTotalPrice(given) shouldBe 185
    }

}