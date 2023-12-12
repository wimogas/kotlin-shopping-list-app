package com.wimogas.shoppinglistapp.feature_shopping_list.domain.use_case

import com.wimogas.shoppinglistapp.feature_shopping_list.domain.model.InvalidItemException
import com.wimogas.shoppinglistapp.feature_shopping_list.domain.model.Item
import com.wimogas.shoppinglistapp.feature_shopping_list.domain.repository.ShoppingListRepository
import kotlin.jvm.Throws

class AddItem(
    private val repository: ShoppingListRepository
) {
    @Throws(InvalidItemException::class)
    suspend operator fun invoke(item: Item) {
        if(item.name.isBlank()) {
            throw InvalidItemException("Name is required")
        }
        repository.insertItem(item)
    }
}