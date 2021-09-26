package com.kypeli.nitormobilemonthlydemo1.ui.theme

import com.kypeli.nitormobilemonthlydemo1.ListItem

class Model(){
    private var model = listOf<ListItem>()

    fun getItems(): List<ListItem> = model

    fun addItem(): ListItem =
        model.let {
            model = it + ListItem("Item #${model.size + 1}")
            model.last()
        }

    fun itemSelected(item: ListItem) {
         model = model
             .mapIndexed { index, listItem ->
                 ListItem(
                     title = "Item #${index + 1}",
                     selected = listItem == item
                 )
             }
    }
}