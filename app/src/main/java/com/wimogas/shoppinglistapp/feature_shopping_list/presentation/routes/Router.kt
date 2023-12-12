package com.wimogas.shoppinglistapp.feature_shopping_list.presentation.routes

sealed class Router(val route: String) {
    object ShoppingListScreen: Router("shoppìng_list_screen")
    object EditItemScreen: Router("edit_item_screen")
}