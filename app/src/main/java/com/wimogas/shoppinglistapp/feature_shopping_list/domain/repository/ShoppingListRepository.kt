package com.wimogas.shoppinglistapp.feature_shopping_list.domain.repository

import com.wimogas.shoppinglistapp.feature_shopping_list.domain.model.Item
import kotlinx.coroutines.flow.Flow

interface ShoppingListRepository {

    fun getItems(): Flow<List<Item>>

    suspend fun getItemsById(id: Int): Item?

    suspend fun insertItem(item: Item)

    suspend fun deleteItem(item: Item)
}