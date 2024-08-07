package edu.metrostate.isc342

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TodoScreen(viewModel: TodoViewModel) {
    val todoItems by remember { mutableStateOf(viewModel.todoItems) }
    var showBottomSheet by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Scaffold (
        floatingActionButton = {
            FloatingActionButton(onClick = { showBottomSheet = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add Todo")
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxSize()
            ) {
                todoItems.forEach { todo ->
                    TodoItemRow(todo) {
                        viewModel.toggleCompletion(it)
                    }
                }
            }
        }
    )

    if (showBottomSheet) {
        BottomSheet(
            onSave = { task ->
                viewModel.addTodoItem(task)
                showBottomSheet = false
            },
            onCancel = {
                showBottomSheet = false
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun TodoScreenPreview() {
    val viewModel = TodoViewModel()
    TodoScreen(viewModel)
}
