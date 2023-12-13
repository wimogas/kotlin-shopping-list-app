package com.wimogas.shoppinglistapp.feature_shopping_list.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.wimogas.shoppinglistapp.common.presentation.ui.theme.ShoppingListAppTheme
import com.wimogas.shoppinglistapp.feature_shopping_list.presentation.edit_item.EditItemScreen
import com.wimogas.shoppinglistapp.feature_shopping_list.presentation.routes.Router
import com.wimogas.shoppinglistapp.feature_shopping_list.presentation.shopping_list.ShoppingListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingListAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Router.ShoppingListScreen.route
                    ) {
                        composable(route = Router.ShoppingListScreen.route) {
                            ShoppingListScreen(navController = navController)
                        }
                        composable(
                            route = Router.EditItemScreen.route + "?itemId={itemId}",
                            arguments = listOf(
                                navArgument(
                                    name = "itemId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            EditItemScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}
