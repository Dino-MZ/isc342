package edu.metrostate.isc342

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TodoItemRow(item: TodoItem, onToggleComplete: (TodoItem) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Checkbox(
            checked = item.isCompleted,
            onCheckedChange = { onToggleComplete(item) }
        )
        Text(
            text = item.task,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}
