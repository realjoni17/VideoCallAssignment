import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.joni.videocallassignment.LoginScreen
import com.joni.videocallassignment.models.UserRole
import com.joni.videocallassignment.screens.DoctorHomeScreen
import com.joni.videocallassignment.screens.PatientHomeScreen
import com.joni.videocallassignment.screens.SignUpScreen

/*



@Composable
fun MainAppNavigation(){

    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()
    val appointmentViewModel: AppointmentViewModel = viewModel()
    val context = LocalContext.current

    // Initialize Zego when user logs in
    LaunchedEffect(authViewModel.currentUser) {
        val user = authViewModel.currentUser.value
        user?.let {
            ZegoCallManager.initializeZegoUIKit(
                context as Application,
                it.id,
                it.name
            )
        }
    }

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(
                viewModel = authViewModel,
                onLoginSuccess = {
                    val user = authViewModel.currentUser.value
                    when (user?.userType) {
                        UserType.PATIENT -> navController.navigate("patient_dashboard")
                        UserType.DOCTOR -> navController.navigate("doctor_dashboard")
                        null -> {}
                    }
                }
            )
        }

        composable("patient_dashboard") {
            val user = authViewModel.currentUser.value
            if (user != null && user.userType == UserType.PATIENT) {
                PatientDashboard(
                    appointmentViewModel = appointmentViewModel,
                    userName = user.name,
                    userId = user.id,
                    onBookAppointment = {
                        // Navigate to booking screen
                        // navController.navigate("book_appointment")
                    },
                    onCallInitiated = { appointmentId, targetUserId ->
                        // Show snackbar or notification that call is being initiated
                        // You could also navigate to a waiting screen here
                    }
                )
            } else {
                navController.navigate("login")
            }
        }

        composable("doctor_dashboard") {
            val user = authViewModel.currentUser.value
            if (user != null && user.userType == UserType.DOCTOR) {
                DoctorDashboard(
                    appointmentViewModel = appointmentViewModel,
                    doctorName = user.name,
                    doctorId = user.id,
                    specialty = user.specialty,
                    onCallInitiated = { appointmentId, targetUserId ->
                        // Show snackbar or notification that call is being initiated
                    },
                    onUpdateAppointment = { appointment ->
                        appointmentViewModel.selectAppointment(appointment)
                    }
                )
            } else {
                navController.navigate("login")
            }
        }
    }
}

*/



sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Signup : Screen("signup")
    object PatientHome : Screen("patient_home")
    object DoctorHome : Screen("doctor_home")
}



@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {

        composable(Screen.Login.route) {
            com.joni.videocallassignment.screens.LoginScreen(
                viewModel = authViewModel,
                onLoginSuccess = { role ->
                    when (role) {
                        UserRole.PATIENT ->
                            navController.navigate(Screen.PatientHome.route) {
                                popUpTo(Screen.Login.route) { inclusive = true }
                            }

                        UserRole.DOCTOR ->
                            navController.navigate(Screen.DoctorHome.route) {
                                popUpTo(Screen.Login.route) { inclusive = true }
                            }
                    }
                },
                onSignupClick = {
                    navController.navigate(Screen.Signup.route)
                }
            )
        }

        composable(Screen.Signup.route) {
            SignUpScreen(
                viewModel = authViewModel,
                onSignupSuccess = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.PatientHome.route) {
            PatientHomeScreen()
        }

        composable(Screen.DoctorHome.route) {
            DoctorHomeScreen()
        }
    }
}
