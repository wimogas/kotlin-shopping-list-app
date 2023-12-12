package com.wimogas.shoppinglistapp.feature_shopping_list.presentation.shopping_list

import com.wimogas.shoppinglistapp.feature_shopping_list.domain.model.Item

data class ShoppingListState(
    val shoppingList: List<Item> = emptyList()
)
