package com.example.navthreedemo.data




class ProductRepository {
    private val products: List<Product> = listOf(
        Product("1", "iPhone", 999.99, Category.ELECTRONICS),
        Product("2", "iPad", 499.99, Category.ELECTRONICS),
        Product("3", "T-shirt", 19.99, Category.CLOTHING),
        Product("4", "Jeans", 49.99, Category.CLOTHING),
        Product("5", "Spaghetti", 1.99, Category.FOOD),
        Product("6", "Pizza", 2.99, Category.FOOD),
        Product("7", "Morning Star", 9.99, Category.BOOKS),
        Product("8", "River of Life", 14.99, Category.BOOKS)
    )
    fun getProductsByCategory(category: Category): List<Product> {
        return products.filter { it.category == category }
    }
    fun getProductById(id: String): Product? {
        return products.find { it.id == id }
    }
}