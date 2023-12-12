package com.wimogas.shoppinglistapp.feature_shopping_list.domain.use_case

import com.wimogas.shoppinglistapp.feature_shopping_list.domain.model.Item
import com.wimogas.shoppinglistapp.feature_shopping_list.domain.repository.ShoppingListRepository

class DeleteItem(
    private val repository: ShoppingListRepository
) {
    suspend operator fun invoke(item: Item) {
        repository.deleteItem(item)
    }
}