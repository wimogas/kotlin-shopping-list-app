package com.wimogas.shoppinglistapp.feature_shopping_list.presentation.shopping_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wimogas.shoppinglistapp.feature_shopping_list.domain.model.Item

@Composable
fun ShoppingListItem(
    item: Item,
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit,
    onChecked: (Boolean) -> Unit,
) {
    var color = if (item.checked) Color.DarkGray.copy(alpha = 0.5f) else Color.DarkGray
    var style = if (item.checked) TextStyle(textDecoration = TextDecoration.LineThrough) else TextStyle(textDecoration = TextDecoration.None)
    Box(
        modifier = modifier
    ) {
        Row(
        modifier = Modifier.fillMaxSize().padding(bottom = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,

        ){
            Checkbox(
                checked = item.checked,
                onCheckedChange = onChecked
            )
            Spacer(Modifier.width(20.dp))
            Text(
                text = item.qty.toString() + " " + item.name,
                color = color,
                style = style,
                fontSize = 18.sp
            )
        }
        IconButton(
            onClick = onDeleteClick
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete item"
            )
        }
    }
    }

}