package be.yle.devbooks.model

data class BasketItem(
    val bookId: Int,
    var count: Int = 1
)

data class Basket(
    val items: MutableSet<BasketItem> = mutableSetOf()
)

data class BasketValidation(
    val basket: Basket,
    val totalPrice: Double
)
