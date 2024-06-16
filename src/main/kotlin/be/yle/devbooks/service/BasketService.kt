package be.yle.devbooks.service

import be.yle.devbooks.data.BasketRepository
import be.yle.devbooks.model.Basket
import be.yle.devbooks.model.BasketItem
import be.yle.devbooks.model.BasketValidation
import org.springframework.stereotype.Service

@Service
class BasketService(
    private val basketRepo: BasketRepository,
    private val bookService: BookService,
    private val calculator: TotalPriceCalculator
) {

    fun addToBasket(input: BasketItem): Boolean {
        if (!bookService.bookIsAvailable(input.bookId)) return false

        val basket = basketRepo.getBasket() ?: Basket()

        val item = basket.items.find { it.bookId == input.bookId }
        if (item == null) {
            basket.items.add(input)
        } else {
            item.count += input.count
        }

        basketRepo.save(basket)

        return true
    }

    fun finalizeCurrentBasket(): BasketValidation {
        val basket = basketRepo.getBasket() ?:return BasketValidation(Basket(), 0.0)
        basketRepo.deleteBasket()

        val totalPrice = calculator.calculateTotalPrice(basket)
        return BasketValidation(basket, totalPrice)
    }
}