package edu.metrostate.isc342

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf

class TodoViewModel : ViewModel() {
    private var nextId = 0
    val todoItems = mutableStateListOf<TodoItem>()

    fun addTodoItem(task: String) {
        if (task.isNotEmpty()) {
            todoItems.add(TodoItem(nextId++, task))
        }
    }

    fun toggleCompletion(item: TodoItem) {
        item.isCompleted = !item.isCompleted
    }

    fun clearTodoItems() {
        todoItems.clear()
    }
}