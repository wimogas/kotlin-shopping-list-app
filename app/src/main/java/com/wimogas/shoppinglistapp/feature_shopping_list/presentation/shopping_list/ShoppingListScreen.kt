package com.wimogas.shoppinglistapp.feature_shopping_list.presentation.shopping_list

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.wimogas.shoppinglistapp.feature_shopping_list.presentation.shopping_list.components.ShoppingListItem
import kotlinx.coroutines.launch
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wimogas.shoppinglistapp.feature_shopping_list.presentation.routes.Router

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ShoppingListScreen(
    navController: NavController,
    viewModel: ShoppingListViewModel = viewModel(factory = ShoppingListViewModel.Factory)
) {
    val state = viewModel.state.value
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold (
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Router.EditItemScreen.route)
                },
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add item"
                )
            }
        }
    ) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 16.dp,
                vertical = 32.dp
            )) {
            items(state.shoppingList) { item ->
                ShoppingListItem(
                    item = item,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable(
                            onClick = {
                                navController.navigate(Router.EditItemScreen.route + "?itemId=${item.id}")
                            }
                        ),
                    onChecked = {
                        viewModel.onEvent(ShoppingListEvent.CheckItem(item))
                    },
                    onDeleteClick = {
                        viewModel.onEvent(ShoppingListEvent.DeleteItem(item))
                        scope.launch {
                            val result = snackbarHostState.showSnackbar(
                                message = "Item Deleted",
                                actionLabel = "Undo",
                                duration = SnackbarDuration.Long
                            )
                            if (result == SnackbarResult.ActionPerformed) {
                                viewModel.onEvent(ShoppingListEvent.RestoreItem)
                            }
                        }
                    }
                )
            }
        }
    }
}