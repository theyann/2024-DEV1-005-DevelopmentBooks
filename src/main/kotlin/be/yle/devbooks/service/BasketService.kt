package be.yle.devbooks.service

import be.yle.devbooks.data.BasketRepository
import be.yle.devbooks.model.Basket
import be.yle.devbooks.model.BasketItem
import org.springframework.stereotype.Service

@Service
class BasketService(
    private val repo: BasketRepository,
    private val calculator: TotalPriceCalculator
) {

    fun addToBasket(input: BasketItem) {
        val basket = repo.getBasket() ?: Basket()

        val item = basket.items.find { it.bookId == input.bookId }
        if (item == null) {
            basket.items.add(input)
        } else {
            item.count += input.count
        }

        repo.save(basket)
    }

}