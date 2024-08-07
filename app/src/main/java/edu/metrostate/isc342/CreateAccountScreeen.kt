package edu.metrostate.isc342

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CreateAccountScreen(viewModel: CreateAccountViewModel, navController: NavController) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var createAccountState by remember { mutableStateOf<CreateAccountState>(CreateAccountState.Idle) }

    viewModel.email = email
    viewModel.password = password
    viewModel.username = username

    LaunchedEffect(viewModel.createAccountState) {
        createAccountState = viewModel.createAccountState
        if (createAccountState is CreateAccountState.Success) {
            navController.navigate(NavRoutes.Login.route)
        }
    }

    LaunchedEffect(viewModel.navigationEvent) {
        viewModel.navigationEvent.observeForever { event ->
            event?.let {
                navController.navigate(it.route)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.createAccount() }) {
            Text("Create Account")
        }
        if (createAccountState is CreateAccountState.Error) {
            Spacer(modifier = Modifier.height(16.dp))
            Text((createAccountState as CreateAccountState.Error).message, color = MaterialTheme.colorScheme.error)
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = { navController.navigate(NavRoutes.Login.route) }) {
            Text("Log In")
        }
    }
}
