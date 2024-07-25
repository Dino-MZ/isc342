package edu.metrostate.isc342

import androidx.compose.foundation.layout.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BottomSheet(onSave: (String) -> Unit, onCancel: () -> Unit) {
    var task by remember { mutableStateOf("") }
    val isError = task.isEmpty()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = task,
            onValueChange = { task = it },
            label = { Text("New Todo") },
            isError = isError,
            trailingIcon = {
                if (task.isNotEmpty()) {
                    IconButton(onClick = { task = "" }) {
                        Icon(Icons.Default.Clear, contentDescription = "Clear Text")
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedButton(onClick = onCancel) {
                Text("Cancel")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    if (task.isNotEmpty()) {
                        onSave(task)
                    }
                },
                colors = ButtonDefaults.buttonColors(Color.Blue)
            ) {
                Text("Save")
            }
        }
    }
}
