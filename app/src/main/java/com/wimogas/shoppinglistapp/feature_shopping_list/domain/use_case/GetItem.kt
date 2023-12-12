package com.wimogas.shoppinglistapp.feature_shopping_list.domain.use_case

import com.wimogas.shoppinglistapp.feature_shopping_list.domain.model.Item
import com.wimogas.shoppinglistapp.feature_shopping_list.domain.repository.ShoppingListRepository

class GetItem(
    private val repository: ShoppingListRepository
) {

    suspend operator fun invoke(id: Int): Item? {
        return repository.getItemsById(id)
    }
}