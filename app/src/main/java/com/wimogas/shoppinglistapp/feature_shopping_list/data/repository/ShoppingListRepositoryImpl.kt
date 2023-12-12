package com.wimogas.shoppinglistapp.feature_shopping_list.data.repository

import com.wimogas.shoppinglistapp.feature_shopping_list.data.data_source.ShoppingListDao
import com.wimogas.shoppinglistapp.feature_shopping_list.domain.model.Item
import com.wimogas.shoppinglistapp.feature_shopping_list.domain.repository.ShoppingListRepository
import kotlinx.coroutines.flow.Flow

class ShoppingListRepositoryImpl(
    private val dao: ShoppingListDao
): ShoppingListRepository {

    override fun getItems(): Flow<List<Item>> {
        return dao.getItems()
    }

    override suspend fun getItemsById(id: Int): Item? {
        return dao.getItemsById(id)
    }

    override suspend fun insertItem(item: Item) {
        return dao.insertItem(item)
    }

    override suspend fun deleteItem(item: Item) {
        return dao.deleteItem(item)
    }

}