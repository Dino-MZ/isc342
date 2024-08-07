package edu.metrostate.isc342

sealed class NavRoutes(val route: String) {
    object Login : NavRoutes("login")
    object CreateAccount : NavRoutes("create_account")
    object Main : NavRoutes("main")
}
