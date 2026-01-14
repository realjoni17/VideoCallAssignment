import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joni.videocallassignment.models.AppUser
import com.joni.videocallassignment.models.UserRole
import com.joni.videocallassignment.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repo: AuthRepository = AuthRepository()
) : ViewModel() {

    var users by mutableStateOf<List<AppUser>>(emptyList())
        private set
    var error by mutableStateOf<String?>(null)
        private set

    fun loadUsers() {
        viewModelScope.launch {
            try {
                users = repo.getAllUsers()
            } catch (e: Exception) {
                error = e.message
            }
        }
    }

    fun signUp(
        email: String,
        password: String,
        role: UserRole,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                repo.signUp(email, password, role)
                onSuccess()
            } catch (e: Exception) {
                error = e.message
            }
        }
    }

    fun login(
        email: String,
        password: String,
        onSuccess: (UserRole) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val role = repo.login(email, password)
                onSuccess(role)
            } catch (e: Exception) {
                error = e.message
            }
        }
    }
}
