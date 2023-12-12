package com.wimogas.shoppinglistapp.feature_shopping_list.domain.use_case

import com.wimogas.shoppinglistapp.feature_shopping_list.domain.model.Item
import com.wimogas.shoppinglistapp.feature_shopping_list.domain.repository.ShoppingListRepository
import kotlinx.coroutines.flow.Flow

class GetItems(
    private val repository: ShoppingListRepository
) {
    operator fun invoke(): Flow<List<Item>> {
        return repository.getItems()
    }
}