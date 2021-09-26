package com.kypeli.nitormobilemonthlydemo1.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.kypeli.nitormobilemonthlydemo1.ListItem

class ListModel : ViewModel() {
    private var currentlySelected by mutableStateOf(-1)
    var items = mutableStateListOf<ListItem>()
        private set

    fun addItem() {
        items.add(ListItem("Item #${items.count() + 1}"))
    }

    fun setSelected(item: ListItem) {
        items.getOrNull(currentlySelected)?.also {
            it.isSelected = false
            items[currentlySelected] = it
        }
        currentlySelected = items.indexOfFirst { it == item }
        item.isSelected = true
        items[currentlySelected] = item
    }
}