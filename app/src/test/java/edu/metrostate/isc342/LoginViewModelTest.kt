import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import edu.metrostate.isc342.ApiService
import edu.metrostate.isc342.LoginResponse
import edu.metrostate.isc342.LoginState
import edu.metrostate.isc342.LoginViewModel
import edu.metrostate.isc342.NavRoutes
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
class LoginViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: LoginViewModel
    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var apiService: ApiService

    @Mock
    private lateinit var navigationObserver: Observer<NavRoutes>

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = LoginViewModel()
        viewModel.navigationEvent.observeForever(navigationObserver)
    }

    @Test
    fun `login should set error state when email or password is blank`() {
        viewModel.email = ""
        viewModel.password = "password"
        viewModel.login()

        assert(viewModel.loginState is LoginState.Error)
        assert((viewModel.loginState as LoginState.Error).message == "Email and password must not be empty")
    }

    @Test
    fun `login should set success state and navigate to main when credentials are valid`() = runBlockingTest {
        viewModel.email = "test@example.com"
        viewModel.password = "password"

        val mockResponse = mock(LoginResponse::class.java)
        `when`(apiService.login(any())).thenReturn(Response.success(mockResponse))

        viewModel.login()

        assert(viewModel.loginState is LoginState.Success)
        verify(navigationObserver).onChanged(NavRoutes.Main)
    }
}