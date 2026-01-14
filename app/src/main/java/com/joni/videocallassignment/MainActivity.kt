package com.joni.videocallassignment

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Badge
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.joni.videocallassignment.ui.theme.VideoCallAssignmentTheme
import com.zegocloud.uikit.prebuilt.call.ZegoUIKitPrebuiltCallService
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig
import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton
import com.zegocloud.uikit.service.defines.ZegoUIKitUser
import im.zego.uikit.libuikitreport.CommonUtils.getApplication


class MainActivity : ComponentActivity() {

    companion object {
        const val APP_ID: Long = 1478493845L
        const val APP_SIGN = "4ff5149f771aac5a7faa71a85710d07978abd366021dff0a9a7e11b8478f7297f"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TempNavigation()
                }
            }
        }
    }
}

// ── ViewModel ──────────────────────────────────────────────────────────────
class AppViewModel : ViewModel() {
    var currentUserId by mutableStateOf("")
    var currentUserName by mutableStateOf("")
    var userRole by mutableStateOf("") // "patient" or "doctor"
    var isLoggedIn by mutableStateOf(false)

    // Mock database of registered users
    val registeredUsers = mutableMapOf(
        "patient_123" to User("patient_123", "John Patient", "patient"),
        "doctor_456" to User("doctor_456", "Dr. Smith", "doctor"),
        "patient_789" to User("patient_789", "Sarah Wilson", "patient"),
        "doctor_101" to User("doctor_101", "Dr. Johnson", "doctor")
    )

    // Mock appointments
    val appointments = mutableListOf(
        Appointment("1", "patient_123", "John Patient", "doctor_456", "Dr. Smith", "10:00 AM", "Booked"),
        Appointment("2", "patient_789", "Sarah Wilson", "doctor_456", "Dr. Smith", "11:30 AM", "Confirmed"),
        Appointment("3", "patient_123", "John Patient", "doctor_101", "Dr. Johnson", "2:00 PM", "Booked")
    )

    fun login(userId: String, displayName: String, role: String): Boolean {
        // Check if user exists in our mock database
        val user = registeredUsers[userId]
        if (user != null && user.role == role) {
            currentUserId = userId
            currentUserName = displayName
            userRole = role
            isLoggedIn = true
            return true
        }
        return false
    }

    fun logout() {
        currentUserId = ""
        currentUserName = ""
        userRole = ""
        isLoggedIn = false
    }

    fun getAppointmentsForCurrentUser(): List<Appointment> {
        return if (userRole == "patient") {
            appointments.filter { it.patientId == currentUserId }
        } else {
            appointments.filter { it.doctorId == currentUserId }
        }
    }

    fun confirmAppointment(appointmentId: String) {
        val appointment = appointments.find { it.id == appointmentId }
        appointment?.status = "Confirmed"
    }
}

data class User(val userId: String, val name: String, val role: String)
data class Appointment(
    val id: String,
    val patientId: String,
    val patientName: String,
    val doctorId: String,
    val doctorName: String,
    val time: String,
    var status: String
)

// ── Main Navigation ────────────────────────────────────────────────────────
@Composable
fun AppNavigation() {
    val viewModel: AppViewModel = viewModel()
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = if (viewModel.isLoggedIn) "main" else "role"
    ) {
        composable("role") {
            RoleSelectionScreen {
                navController.navigate("login/$it")
            }
        }

        composable("login/{role}") { backStackEntry ->
            val role = backStackEntry.arguments?.getString("role") ?: "patient"
            LoginScreen(role) { userId, displayName ->
                if (viewModel.login(userId, displayName, role)) {
                    navController.navigate("main")
                } else {
                    // Show error - user doesn't exist
                    navController.navigate("user_not_found")
                }
            }
        }

        composable("user_not_found") {
            UserNotFoundScreen {
                navController.popBackStack()
            }
        }

        composable("main") {
            MainScreen(viewModel) {
                viewModel.logout()
                navController.navigate("role") {
                    popUpTo("main") { inclusive = true }
                }
            }
        }
    }
}

// ── Screens ────────────────────────────────────────────────────────────────
@Composable
fun RoleSelectionScreen(onRoleSelected: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to MediCall",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(48.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onRoleSelected("patient") }
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    Icons.Default.Call,
                    contentDescription = "Patient",
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Patient",
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = "Book appointments & video consultations",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onRoleSelected("doctor") }
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    Icons.Default.Call,
                    contentDescription = "Doctor",
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Doctor",
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = "Manage appointments & video consultations",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "Demo Credentials:",
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = "Patients: patient_123 (John), patient_789 (Sarah)",
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = "Doctors: doctor_456 (Dr. Smith), doctor_101 (Dr. Johnson)",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun LoginScreen(
    role: String,
    onLoginSuccess: (String, String) -> Unit
) {
    var userId by remember { mutableStateOf("") }
    var displayName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Login as ${role.replaceFirstChar { it.uppercase() }}",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(48.dp))

        OutlinedTextField(
            value = userId,
            onValueChange = { userId = it },
            label = { Text("User ID") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Enter your user ID") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = displayName,
            onValueChange = { displayName = it },
            label = { Text("Display Name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Enter your name") }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                if (userId.isNotBlank() && displayName.isNotBlank()) {
                    onLoginSuccess(userId, displayName)
                }
                val callInvitationConfig = ZegoUIKitPrebuiltCallInvitationConfig()

                ZegoUIKitPrebuiltCallService.init(getApplication(), 1478493845, "4ff5149f771aac5a7faa71a85710d07978abd366021dff0a9a7e11b8478f7297f", "joni", "joni",callInvitationConfig);
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = userId.isNotBlank() && displayName.isNotBlank()
        ) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Demo IDs (copy & paste):",
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = if (role == "patient")
                "• patient_123 (John Patient)\n• patient_789 (Sarah Wilson)"
            else
                "• doctor_456 (Dr. Smith)\n• doctor_101 (Dr. Johnson)",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun UserNotFoundScreen(onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            Icons.Default.Call,
            contentDescription = "Error",
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.error
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "User Not Found",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Please use the demo credentials or register the user first.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = onRetry) {
            Text("Try Again")
        }
    }
}

@Composable
fun MainScreen(viewModel: AppViewModel, onLogout: () -> Unit) {
    val appointments = viewModel.getAppointmentsForCurrentUser()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Header
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = if (viewModel.userRole == "patient")
                        "Patient Dashboard"
                    else
                        "Doctor Dashboard",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Text(
                    text = "Welcome, ${viewModel.currentUserName}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = onLogout,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onPrimary,
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("Logout")
                }
            }
        }

        // Content
        if (appointments.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    Icons.Default.Call,
                    contentDescription = "No appointments",
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text("No appointments scheduled")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(appointments) { appointment ->
                    AppointmentCard(
                        appointment = appointment,
                        userRole = viewModel.userRole,
                        currentUserId = viewModel.currentUserId,
                        onConfirmAppointment = { viewModel.confirmAppointment(appointment.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun AppointmentCard(
    appointment: Appointment,
    userRole: String,
    currentUserId: String,
    onConfirmAppointment: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = if (userRole == "patient")
                            appointment.doctorName
                        else
                            appointment.patientName,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Time: ${appointment.time}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Badge(
                    containerColor = when (appointment.status) {
                        "Confirmed" -> MaterialTheme.colorScheme.secondaryContainer
                        "Booked" -> MaterialTheme.colorScheme.primaryContainer
                        else -> MaterialTheme.colorScheme.tertiaryContainer
                    }
                ) {
                    Text(appointment.status)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Buttons based on role and status
            if (userRole == "doctor" && appointment.status == "Booked") {
                Button(
                    onClick = onConfirmAppointment,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.Call, contentDescription = "Confirm")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Confirm Appointment")
                }
            }

            if (appointment.status == "Confirmed") {
                Spacer(modifier = Modifier.height(8.dp))

                // Determine target user for call
                val targetUserId = if (userRole == "patient")
                    appointment.doctorId
                else
                    appointment.patientId

                val targetUserName = if (userRole == "patient")
                    appointment.doctorName
                else
                    appointment.patientName

                // Call Button using AndroidView
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    ZegoCallButton(
                        targetUserId = "joni",
                        targetUserName = "joni",
                        isVideoCall = true
                    )
                }
            }
        }
    }
}

@Composable
fun ZegoCallButton(
    targetUserId: String,
    targetUserName: String,
    isVideoCall: Boolean = true
) {
    AndroidView(
        factory = { context ->
            ZegoSendCallInvitationButton(context).apply {
                setIsVideoCall(isVideoCall)
                setResourceID("zego_uikit_call")

                // Set invitee
                setInvitees(listOf(ZegoUIKitUser(targetUserId, targetUserName)))

                // Set timeout
                setTimeout(60)

                // Customize appearance
                text = "Call Now"
                setAllCaps(false)

                // Set styling
               // setTextColor(android.graphics.Color.WHITE)
               // setBackgroundResource(R.drawable.ic_launcher_background)
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}


@Composable
fun RoleSelectionScreen(navController: NavController) {
    val callInvitationConfig = ZegoUIKitPrebuiltCallInvitationConfig()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Select Role",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 48.dp)
        )
        Button(
            onClick = { navController.navigate(Screen.PatientLogin.route)
                ZegoUIKitPrebuiltCallService.init(getApplication(), 1478493845, "4ff5149f771aac5a7faa71a85710d07978abd366021dff0a9a7e11b8478f7297f", "patient", "patient",callInvitationConfig);
                      },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text("Patient")
        }
        Button(
            onClick = { navController.navigate(Screen.DoctorLogin.route)
                ZegoUIKitPrebuiltCallService.init(getApplication(), 1478493845, "4ff5149f771aac5a7faa71a85710d07978abd366021dff0a9a7e11b8478f7297f", "doctor", "doctor",callInvitationConfig);
                      },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Doctor")
        }
    }
}

sealed class Screen(val route: String) {
    object RoleSelection : Screen("role_selection")
    object PatientLogin : Screen("patient_login")
    object DoctorLogin : Screen("doctor_login")
}


@Composable
fun TempNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.RoleSelection.route
    ) {
        composable(Screen.RoleSelection.route) {
            RoleSelectionScreen(navController)
        }
        composable(Screen.PatientLogin.route) {
            PatientLoginScreen(navController)
        }
        composable(Screen.DoctorLogin.route) {
            DoctorLoginScreen(navController)
        }
    }
}

@Composable
fun DoctorLoginScreen(x0: NavHostController) {
    Box(contentAlignment = Alignment.Center){
        Text("Hello Doctor")
    }
}

@Composable
fun PatientLoginScreen(x0: NavHostController) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        ZegoCallButton(
            targetUserId = "doctor",
            targetUserName = "doctor",
            isVideoCall = true
        )
    }
}



