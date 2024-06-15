package be.yle.devbooks.service

import be.yle.devbooks.data.DiscountRepository
import be.yle.devbooks.model.Basket
import be.yle.devbooks.utils.SeriesSplitter
import org.springframework.stereotype.Service

@Service
class TotalPriceCalculator(
    private val discountRepo: DiscountRepository,
    private val splitter: SeriesSplitter
) {

    /**
     * Discount Rules:
     * - 2 different books: 5%
     * - 3 different books: 10%
     * - 4 different books: 20%
     * - 5 different books: 25%
     * Note that if you buy, say, 4 books, of which 3 are different titles, you get a 10% discount on the 3 that form part of a set, but the 4th book still costs 50 EUR.
     *
     * Example:
     * - 2 x Clean Code + 2 x Clean Coder + 2 x Clean Architecture + 1 x TDD By Example + 1 x Legacy Code = 320
     * Reason : you have 2 times 4 different books --> 2 x ((4 x 50) - 20%) = 320
     */

    fun calculateTotalPrice(basket: Basket): Double {
        val series = splitter.splitToSeries(basket)

        var totalPrice = 0.0

        series.forEach {
            val discount = discountRepo.findDiscountForSeries(it)

            val totalBookCount = it.size
            val totalWithoutDiscount = totalBookCount * SINGLE_BOOK_PRICE
            totalPrice += totalWithoutDiscount - (totalWithoutDiscount * discount)
        }

        return totalPrice
    }

    companion object {
        const val SINGLE_BOOK_PRICE = 50.0
    }
}