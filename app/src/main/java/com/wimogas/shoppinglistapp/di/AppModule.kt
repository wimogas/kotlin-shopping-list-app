package com.wimogas.shoppinglistapp.di

import android.app.Application
import androidx.room.Room
import com.wimogas.shoppinglistapp.feature_shopping_list.data.data_source.ShoppingListDatabase
import com.wimogas.shoppinglistapp.feature_shopping_list.data.repository.ShoppingListRepositoryImpl
import com.wimogas.shoppinglistapp.feature_shopping_list.domain.repository.ShoppingListRepository
import com.wimogas.shoppinglistapp.feature_shopping_list.domain.use_case.AddItem
import com.wimogas.shoppinglistapp.feature_shopping_list.domain.use_case.DeleteItem
import com.wimogas.shoppinglistapp.feature_shopping_list.domain.use_case.GetItem
import com.wimogas.shoppinglistapp.feature_shopping_list.domain.use_case.GetItems
import com.wimogas.shoppinglistapp.feature_shopping_list.domain.use_case.ShoppingListUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideShoppingListDatabase(app: Application): ShoppingListDatabase {
        return Room.databaseBuilder(
            app,
            ShoppingListDatabase::class.java,
            ShoppingListDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideShoppingListRepository(db: ShoppingListDatabase): ShoppingListRepository {
        return ShoppingListRepositoryImpl(db.shoppingListDao)
    }

    @Provides
    @Singleton
    fun provideShoppingListUseCases(repository: ShoppingListRepository) : ShoppingListUseCases {
        return ShoppingListUseCases(
            getItems = GetItems(repository),
            deleteItem = DeleteItem(repository),
            addItem = AddItem(repository),
            getItem = GetItem(repository)
        )
    }
}