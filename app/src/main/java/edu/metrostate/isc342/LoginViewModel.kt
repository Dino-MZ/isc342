package edu.metrostate.isc342

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class LoginViewModel : ViewModel() {
    private val _navigationEvent = MutableLiveData<NavRoutes>()
    val navigationEvent: LiveData<NavRoutes> = _navigationEvent

    private val apiService = RetrofitClient.instance

    var email: String = ""
    var password: String = ""
    var loginState: LoginState = LoginState.Idle
        private set

    fun login() {
        if (email.isBlank() || password.isBlank()) {
            loginState = LoginState.Error("Email and password must not be empty")
            return
        }

        viewModelScope.launch {
            try {
                val response = apiService.login(LoginRequest(email, password))
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        loginState = LoginState.Success(loginResponse)
                        _navigationEvent.value = NavRoutes.Main
                    } else {
                        loginState = LoginState.Error("Invalid response from server")
                    }
                } else {
                    loginState = LoginState.Error("Login failed: ${response.message()}")
                }
            } catch (e: Exception) {
                loginState = LoginState.Error("An error occurred: ${e.message}")
            }
        }
    }
}


sealed class LoginState {
    object Idle : LoginState()
    data class Success(val response: LoginResponse) : LoginState()
    data class Error(val message: String) : LoginState()
}
