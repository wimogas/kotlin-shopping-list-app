package com.wimogas.shoppinglistapp.feature_shopping_list.presentation.shopping_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wimogas.shoppinglistapp.feature_shopping_list.domain.model.Item
import com.wimogas.shoppinglistapp.feature_shopping_list.domain.use_case.ShoppingListUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    private val shoppingListUseCases: ShoppingListUseCases
) : ViewModel() {

    private val _state = mutableStateOf(ShoppingListState())
    val state: State<ShoppingListState> = _state

    private var recentlyDeletedItem: Item? = null

    private var getItemsJob: Job? = null

    init {
        getItems()
    }

    fun onEvent(event: ShoppingListEvent) {
        when(event) {
            is ShoppingListEvent.DeleteItem -> {
                viewModelScope.launch {
                    shoppingListUseCases.deleteItem(event.item)
                    recentlyDeletedItem = event.item
                }
            }
            is ShoppingListEvent.RestoreItem -> {
                viewModelScope.launch {
                    shoppingListUseCases.addItem(recentlyDeletedItem ?: return@launch)
                    recentlyDeletedItem = null
                }
            }
            is ShoppingListEvent.CheckItem -> {
                viewModelScope.launch {
                    val updatedItem = Item(
                        id = event.item.id,
                        name = event.item.name,
                        aisle = event.item.aisle,
                        qty = event.item.qty,
                        checked = !event.item.checked,
                    )
                    shoppingListUseCases.addItem(updatedItem)
                }
            }
        }
    }

    private fun getItems() {
        getItemsJob?.cancel()
        getItemsJob = shoppingListUseCases.getItems().onEach { shoppingList ->
            _state.value = state.value.copy(
                shoppingList = shoppingList,
            )
        }
        .launchIn(viewModelScope)
    }

}