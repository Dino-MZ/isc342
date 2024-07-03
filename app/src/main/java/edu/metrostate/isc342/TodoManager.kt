package edu.metrostate.isc342

import androidx.lifecycle.LiveData

object TodoManager {
    private val todoList = mutableListOf<Todo>()

    fun getAllTodo() : List<Todo> {
        return todoList
    }

    fun addTodo() {
        todoList.add(Todo("List Item"))
    }
}