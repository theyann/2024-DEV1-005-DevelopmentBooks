package be.yle.devbooks.data

import be.yle.devbooks.model.Basket
import org.springframework.stereotype.Repository

@Repository
class BasketRepository {

    fun save(basket: Basket) {
        CURRENT_BASKET = basket
    }

    fun getBasket(): Basket? = CURRENT_BASKET

    companion object {
        var CURRENT_BASKET: Basket? = null
    }
}