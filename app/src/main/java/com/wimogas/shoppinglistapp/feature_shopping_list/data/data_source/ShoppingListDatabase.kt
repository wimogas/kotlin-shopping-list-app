package com.wimogas.shoppinglistapp.feature_shopping_list.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wimogas.shoppinglistapp.feature_shopping_list.domain.model.Item

@Database(
    entities = [Item::class],
    version = 1
)
abstract class ShoppingListDatabase: RoomDatabase() {
    abstract val shoppingListDao: ShoppingListDao

    companion object {
        const val DATABASE_NAME = "shopping_list_db"
    }
}