package com.wimogas.shoppinglistapp.feature_shopping_list.presentation.edit_item

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wimogas.shoppinglistapp.feature_shopping_list.domain.model.InvalidItemException
import com.wimogas.shoppinglistapp.feature_shopping_list.domain.model.Item
import com.wimogas.shoppinglistapp.feature_shopping_list.domain.use_case.ShoppingListUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditItemViewModel @Inject constructor(
    private val itemUseCases: ShoppingListUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _itemName = mutableStateOf("")
    val itemName: State<String> = _itemName

    private val _itemQty = mutableStateOf("1")
    val itemQty: State<String> = _itemQty

    private val _itemColor = mutableStateOf(Item.aisles.random().toArgb())
    val itemColor: State<Int> = _itemColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentItemId: Int? = null

    init {
        savedStateHandle.get<Int>("itemId")?.let { itemId ->
            if (itemId != -1) {
                viewModelScope.launch {
                    itemUseCases.getItem(itemId)?.also {
                        currentItemId = it.id
                        _itemName.value = it.name
                        _itemQty.value = it.qty.toString()
                    }
                }
            }
        }
    }

    fun onEvent(event: EditItemEvent) {
        when(event) {
            is EditItemEvent.EnteredName -> {
                _itemName.value = event.value
            }
            is EditItemEvent.EnteredQty -> {
                _itemQty.value = event.value
            }
            is EditItemEvent.ChangeColor -> {
                _itemColor.value = event.color
            }
            is EditItemEvent.SaveItem -> {
                viewModelScope.launch {
                    try {
                        itemUseCases.addItem(
                            Item(
                                id = currentItemId,
                                name = itemName.value,
                                aisle = itemColor.value,
                                qty = itemQty.value.toInt(),
                                checked = false,
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveItem)
                    } catch(e: InvalidItemException) {
                        _eventFlow.emit(UiEvent.ShowSnackbar(message = e.message ?: "Item not saved"))
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        data object SaveItem: UiEvent()
    }


}