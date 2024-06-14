package be.yle.devbooks.model

data class BasketItem(
    val bookId: Int,
    val count: Int = 1
)

data class Basket(
    val items: List<BasketItem>
)

