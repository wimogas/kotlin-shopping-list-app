package com.wimogas.shoppinglistapp.feature_shopping_list.data.data_source

import androidx.room.*
import com.wimogas.shoppinglistapp.feature_shopping_list.domain.model.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDao {
    @Query("SELECT * FROM item")
    fun getItems(): Flow<List<Item>>

    @Query("SELECT * FROM item WHERE id = :id")
    suspend fun getItemsById(id: Int): Item?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)
}