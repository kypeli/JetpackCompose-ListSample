package com.kypeli.nitormobilemonthlydemo1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kypeli.nitormobilemonthlydemo1.ui.theme.ListModel
import com.kypeli.nitormobilemonthlydemo1.ui.theme.NitorMobileMonthlyDemo1Theme

class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val model = ListModel()
        model.addItem()

        setContent {
            NitorMobileMonthlyDemo1Theme {
                MainView(model)
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun MainView(listModel: ListModel) {
    val items = listModel.items
    val onSelect = { item: ListItem -> listModel.setSelected(item) }
    val onAddMore = { listModel.addItem() }
    val onAdd100 = { repeat(100) { listModel.addItem() } }

    LazyColumn {
        items(items) { item ->
            ListRow(
                item = item,
                selected = item.isSelected,
                modifier = Modifier.clickable { onSelect(item) }
            )
        }
        item {
            Row {
                Button(
                    modifier = Modifier.padding(start = 12.dp, top = 12.dp),
                    onClick = { onAddMore() }
                ) {
                    Text("More")
                }

                Button(
                    modifier = Modifier.padding(start = 12.dp, top = 12.dp),
                    onClick = { onAdd100() }
                ) {
                    Text("Lots more")
                }
            }
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
            Box(modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(Color.Gray)
            ) {
                Text(
                    text = "Selected",
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier.padding(start = 12.dp, end = 12.dp)
                )
            }
        }
    }
}























@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val model by remember { mutableStateOf(ListModel().also { it.addItem() })}
    NitorMobileMonthlyDemo1Theme {
        MainView(model)
    }
}