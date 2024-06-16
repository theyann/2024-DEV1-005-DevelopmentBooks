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