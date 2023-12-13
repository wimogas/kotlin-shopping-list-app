package com.wimogas.shoppinglistapp.feature_shopping_list.presentation.edit_item

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.collectLatest


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditItemScreen(
    navController: NavController,
    viewModel: EditItemViewModel = viewModel(factory = EditItemViewModel.Factory)
) {

    val nameState = viewModel.itemName.value
    val qtyState = viewModel.itemQty.value

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest {  event ->
            when(event) {
                is EditItemViewModel.UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message,
                        duration = SnackbarDuration.Long
                    )
                }
                is EditItemViewModel.UiEvent.SaveItem -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold (
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(EditItemEvent.SaveItem)
                },
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = "Save item"
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(
                horizontal = 16.dp,
                vertical = 32.dp
            )
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = nameState,
                onValueChange = {
                    viewModel.onEvent(EditItemEvent.EnteredName(it))
                },
            )
            Spacer(Modifier.height(20.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                value = qtyState,
                onValueChange = {
                    viewModel.onEvent(EditItemEvent.EnteredQty(it))
                }
            )
        }

    }
}