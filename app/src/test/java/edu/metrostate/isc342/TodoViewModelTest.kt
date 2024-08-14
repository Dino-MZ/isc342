package edu.metrostate.isc342

import org.junit.Before
import org.junit.Test

class TodoViewModelTest {

    private lateinit var viewModel: TodoViewModel

    @Before
    fun setup() {
        viewModel = TodoViewModel()
    }

    @Test
    fun `addTodoItem should add a new item to the list when task is not empty`() {
        viewModel.addTodoItem("New Task")
        assert(viewModel.todoItems.size == 1)
        assert(viewModel.todoItems[0].task == "New Task")
    }

    @Test
    fun `addTodoItem should not add a new item to the list when task is empty`() {
        viewModel.addTodoItem("")
        assert(viewModel.todoItems.isEmpty())
    }

    @Test
    fun `toggleCompletion should change the completion status of the given item`() {
        viewModel.addTodoItem("Task")
        val item = viewModel.todoItems[0]
        assert(!item.isCompleted)

        viewModel.toggleCompletion(item)
        assert(item.isCompleted)

        viewModel.toggleCompletion(item)
        assert(!item.isCompleted)
    }

    @Test
    fun `clearTodoItems should remove all items from the list`() {
        viewModel.addTodoItem("Task 1")
        viewModel.addTodoItem("Task 2")
        assert(viewModel.todoItems.size == 2)

        viewModel.clearTodoItems()
        assert(viewModel.todoItems.isEmpty())
    }
}