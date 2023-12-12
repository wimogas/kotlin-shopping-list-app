package com.wimogas.shoppinglistapp.feature_shopping_list.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wimogas.shoppinglistapp.common.presentation.ui.theme.BabyBlue
import com.wimogas.shoppinglistapp.common.presentation.ui.theme.LightGreen
import com.wimogas.shoppinglistapp.common.presentation.ui.theme.RedOrange
import com.wimogas.shoppinglistapp.common.presentation.ui.theme.Violet

@Entity
data class Item(
    var name: String,
    var qty: Int,
    var aisle: Int,
    var checked: Boolean,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val aisles = listOf(RedOrange, LightGreen, Violet, BabyBlue)
    }
}

class InvalidItemException(message: String): Exception(message)
