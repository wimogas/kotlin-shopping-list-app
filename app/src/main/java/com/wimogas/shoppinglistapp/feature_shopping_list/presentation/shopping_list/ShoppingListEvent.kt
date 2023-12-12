package com.wimogas.shoppinglistapp.feature_shopping_list.presentation.shopping_list

import com.wimogas.shoppinglistapp.feature_shopping_list.domain.model.Item

sealed class ShoppingListEvent {
    data class DeleteItem(val item: Item): ShoppingListEvent()
    data class CheckItem(val item: Item): ShoppingListEvent()
    data object RestoreItem: ShoppingListEvent()
}