package be.yle.devbooks.data

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class DiscountRepositoryTest {

    companion object {
        @JvmStatic
        fun provideArgumentsForFindDiscountForSeries(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(emptySet<Int>(), .0),
                Arguments.of(setOf(0), .0),
                Arguments.of(setOf(0, 1), .05),
                Arguments.of(setOf(0, 1, 2), .10),
                Arguments.of(setOf(0, 1, 2, 3), .20),
                Arguments.of(setOf(0, 1, 2, 3, 4), .25),
            )
        }
    }

    private lateinit var repo: DiscountRepository

    @BeforeEach
    fun setup() {
        repo = DiscountRepository()
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForFindDiscountForSeries")
    fun `findDiscountForSeries for each case their discount`(givenSeries: Set<Int>, expected: Double) {
        repo.findDiscountForSeries(givenSeries) shouldBe expected
    }
}