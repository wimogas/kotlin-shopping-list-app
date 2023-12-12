package com.wimogas.shoppinglistapp.feature_shopping_list.domain.use_case

data class ShoppingListUseCases(
    val getItems: GetItems,
    val deleteItem: DeleteItem,
    val addItem: AddItem,
    val getItem: GetItem,
) {
}