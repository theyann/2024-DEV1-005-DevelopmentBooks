package be.yle.devbooks.data

import org.springframework.stereotype.Repository

@Repository
class DiscountRepository {

    fun findDiscountForSeries(series: Set<Int>) = when (series.size) {
        2 -> .05
        3 -> .10
        4 -> .20
        5 -> .25
        else -> .0
    }

}