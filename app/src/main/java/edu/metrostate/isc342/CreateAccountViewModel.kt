package edu.metrostate.isc342

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class CreateAccountViewModel : ViewModel() {
    private val _navigationEvent = MutableLiveData<NavRoutes>()
    val navigationEvent: LiveData<NavRoutes> = _navigationEvent

    private val apiService = RetrofitClient.instance

    var username: String = ""
    var email: String = ""
    var password: String = ""
    var createAccountState: CreateAccountState = CreateAccountState.Idle
        private set

    fun createAccount() {
        if (username.isBlank() || email.isBlank() || password.isBlank()) {
            createAccountState = CreateAccountState.Error("Username, email, and password must not be empty")
            return
        }

        viewModelScope.launch {
            try {
                val response = apiService.register(RegisterRequest(username, email, password))
                if (response.isSuccessful) {
                    val registerResponse = response.body()
                    if (registerResponse != null) {
                        createAccountState = CreateAccountState.Success(registerResponse)
                        _navigationEvent.value = NavRoutes.Login
                    } else {
                        createAccountState = CreateAccountState.Error("Invalid response from server")
                    }
                } else {
                    createAccountState = CreateAccountState.Error("Account creation failed: ${response.message()}")
                    println("Account creation failed: ${response.message()}")
                }
            } catch (e: Exception) {
                createAccountState = CreateAccountState.Error("An error occurred: ${e.message}")
            }
        }
    }
}

sealed class CreateAccountState {
    object Idle : CreateAccountState()
    data class Success(val response: LoginResponse) : CreateAccountState()
    data class Error(val message: String) : CreateAccountState()
}
