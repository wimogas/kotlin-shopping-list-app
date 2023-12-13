package com.wimogas.shoppinglistapp.di

import android.content.Context
import androidx.room.Room
import com.wimogas.shoppinglistapp.feature_shopping_list.data.data_source.ShoppingListDatabase
import com.wimogas.shoppinglistapp.feature_shopping_list.data.repository.ShoppingListRepositoryImpl
import com.wimogas.shoppinglistapp.feature_shopping_list.domain.repository.ShoppingListRepository
import com.wimogas.shoppinglistapp.feature_shopping_list.domain.use_case.AddItem
import com.wimogas.shoppinglistapp.feature_shopping_list.domain.use_case.DeleteItem
import com.wimogas.shoppinglistapp.feature_shopping_list.domain.use_case.GetItem
import com.wimogas.shoppinglistapp.feature_shopping_list.domain.use_case.GetItems
import com.wimogas.shoppinglistapp.feature_shopping_list.domain.use_case.ShoppingListUseCases

interface AppModule {
    val shoppingListDatabase: ShoppingListDatabase
    val shoppingListRepository: ShoppingListRepository
    val shoppingListUseCases: ShoppingListUseCases
}

class AppModuleImpl (private val appContext: Context): AppModule {

    override val shoppingListDatabase: ShoppingListDatabase by lazy {
        Room.databaseBuilder(
            appContext,
            ShoppingListDatabase::class.java,
            ShoppingListDatabase.DATABASE_NAME
        ).build()
    }

    override val shoppingListRepository: ShoppingListRepository by lazy {
        ShoppingListRepositoryImpl(shoppingListDatabase.shoppingListDao)
    }

    override val shoppingListUseCases: ShoppingListUseCases by lazy {
        ShoppingListUseCases(
            getItems = GetItems(shoppingListRepository),
            deleteItem = DeleteItem(shoppingListRepository),
            addItem = AddItem(shoppingListRepository),
            getItem = GetItem(shoppingListRepository)
        )
    }
}