package be.yle.devbooks.utils

import be.yle.devbooks.model.Basket
import org.springframework.stereotype.Component

@Component
class SeriesSplitter {

    fun splitToSeries(basket: Basket): List<Set<Int>> {
        val series = mutableListOf<MutableSet<Int>>()

        basket.items.forEach { item ->
            for (i in 0 until item.count) {
                putIdInSet(item.bookId, series)
            }
        }

        return series
    }

    private fun putIdInSet(bookId: Int, series: MutableList<MutableSet<Int>>) {
        val foundSet: MutableSet<Int>? = series.findFirstSetThatDoesNotContainId(bookId)

        if (foundSet == null) {
            series.add(mutableSetOf(bookId))
        } else {
            foundSet.add(bookId)
        }
    }

    private fun MutableList<MutableSet<Int>>.findFirstSetThatDoesNotContainId(id: Int): MutableSet<Int>? =
        this.firstOrNull { !it.contains(id) }?.let { return it }
}