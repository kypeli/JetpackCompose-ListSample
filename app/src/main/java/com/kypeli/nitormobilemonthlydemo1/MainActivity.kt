package com.kypeli.nitormobilemonthlydemo1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.tooling.preview.Preview
import com.kypeli.nitormobilemonthlydemo1.ui.theme.Model
import com.kypeli.nitormobilemonthlydemo1.ui.theme.NitorMobileMonthlyDemo1Theme
import org.w3c.dom.Text

class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NitorMobileMonthlyDemo1Theme {
                MainView()
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun MainView() {
    val model = remember { Model() }
    val items = remember { mutableStateListOf(model.addItem()) }
    var selectedItem by remember { mutableStateOf<ListItem?>(null) }

    Column {
        List(items, selectedItem) { item ->
            selectedItem = item
        }
        Button(
            modifier = Modifier.padding(start = 12.dp, top = 12.dp),
            onClick = { items.add(model.addItem()) }
        ) {
            Text("More")
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun List(
    rows: List<ListItem>,
    selectedItem: ListItem?,
    onSelect: (ListItem) -> Unit
) {
    LazyColumn {
        items(rows) { item ->
            ListRow(
                item = item,
                selected = item == selectedItem,
                modifier = Modifier.clickable {
                    onSelect(item)
                }
            )
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun ListRow(
    item: ListItem,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    val background: Color by animateColorAsState(if (selected) Color.Magenta else Color.Gray)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(bottom = 8.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(background)
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Text(
            text = item.title,
            color = MaterialTheme.colors.secondary,
            style = MaterialTheme.typography.h1,
            modifier = Modifier.weight(1f)
        )
        AnimatedVisibility(visible = selected) {
            Text(
                text = "Selected",
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.padding(start = 12.dp, end = 12.dp),
            )
        }
    }
}























@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NitorMobileMonthlyDemo1Theme {
        List(
            rows = listOf(
                ListItem("First"),
                ListItem("Second")
            ),
            null,
            {}
        )
    }
}