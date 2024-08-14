package edu.metrostate.isc342

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import retrofit2.Response

@ExperimentalCoroutinesApi
class CreateAccountViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CreateAccountViewModel
    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var apiService: ApiService

    @Mock
    private lateinit var navigationObserver: Observer<NavRoutes>

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = CreateAccountViewModel()
        viewModel.navigationEvent.observeForever(navigationObserver)
    }

    @Test
    fun `createAccount should set error state when any field is blank`() {
        viewModel.username = "user"
        viewModel.email = ""
        viewModel.password = "password"
        viewModel.createAccount()

        assert(viewModel.createAccountState is CreateAccountState.Error)
        assert((viewModel.createAccountState as CreateAccountState.Error).message == "Username, email, and password must not be empty")
    }

    @Test
    fun `createAccount should set success state and navigate to login when account creation is successful`() = runBlockingTest {
        viewModel.username = "user"
        viewModel.email = "test@example.com"
        viewModel.password = "password"

        val mockResponse = mock(LoginResponse::class.java)
        `when`(apiService.register(any())).thenReturn(Response.success(mockResponse))

        viewModel.createAccount()

        assert(viewModel.createAccountState is CreateAccountState.Success)
        verify(navigationObserver).onChanged(NavRoutes.Login)
    }
}