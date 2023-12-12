package com.wimogas.shoppinglistapp.feature_shopping_list.presentation.edit_item

sealed class EditItemEvent {
    data class EnteredName(val value: String): EditItemEvent()
    data class EnteredQty(val value: String): EditItemEvent()
    data class ChangeColor(val color: Int): EditItemEvent()
    object SaveItem: EditItemEvent()
}