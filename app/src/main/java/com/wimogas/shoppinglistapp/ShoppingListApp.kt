package com.wimogas.shoppinglistapp

import android.app.Application
import com.wimogas.shoppinglistapp.di.AppModule
import com.wimogas.shoppinglistapp.di.AppModuleImpl

class ShoppingListApp: Application() {
    companion object {
        lateinit var appModule: AppModule
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl(this)
    }
}