package edu.metrostate.isc342

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TodoListPage(viewModel: TodoViewModel) {
    val todoList by viewModel.todoList.observeAsState()

    Column (
        modifier = Modifier
            .fillMaxHeight()
    ) {
        todoList?.let {
            LazyColumn (
                content =  {
                    itemsIndexed(it) {index: Int, item: Todo ->
                        TodoItem(item = item)
                    }
                }
            )
        }
    }
    Column (
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Button(onClick = { viewModel.addTodo() },
            modifier = Modifier
                .padding(vertical = 30.dp, horizontal = 15.dp)
        ) {
            Text(text = "+", fontSize = 40.sp)
        }
    }
}

@Composable
fun TodoItem(item: Todo) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .background(color = Color.White)
    ){
        Column {
            Text(text = item.name, fontSize = 20.sp)
        }
    }
}