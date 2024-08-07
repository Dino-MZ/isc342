package edu.metrostate.isc342

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]
        val loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        val createAccountViewModel = ViewModelProvider(this)[CreateAccountViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            //TodoScreen(todoViewModel)
            //LoginScreen(viewModel = loginViewModel, navController = navController)
            //CreateAccountScreen(viewModel = createAccountViewModel, navController = navController)

            NavHost(navController = navController, startDestination = NavRoutes.Login.route) {
                composable(NavRoutes.Login.route) {
                    LoginScreen(viewModel = loginViewModel, navController = navController)
                }
                composable(NavRoutes.CreateAccount.route) {
                    CreateAccountScreen(viewModel = createAccountViewModel, navController = navController)
                }
                composable(NavRoutes.Main.route) {
                    TodoScreen(todoViewModel)
                }
            }
        }
    }
}