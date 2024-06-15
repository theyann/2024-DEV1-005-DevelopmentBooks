package be.yle.devbooks.service

import be.yle.devbooks.data.BasketRepository
import be.yle.devbooks.model.Basket
import be.yle.devbooks.model.BasketItem
import be.yle.devbooks.model.BasketValidation
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

    fun finalizeCurrentBasket(): BasketValidation {
        val basket = repo.getBasket() ?:return BasketValidation(Basket(), 0.0)

        val totalPrice = calculator.calculateTotalPrice(basket)
        return BasketValidation(basket, totalPrice)
    }
}