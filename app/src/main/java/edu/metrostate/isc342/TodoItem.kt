package edu.metrostate.isc342

data class TodoItem(
    val id: Int,
    val task: String,
    var isCompleted: Boolean = false
)