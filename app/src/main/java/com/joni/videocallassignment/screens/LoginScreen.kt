
package com.joni.videocallassignment.screens


/*import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.joni.videocallassignment.models.UserType
import com.joni.videocallassignment.viewModels.AuthViewModel


@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    onLoginSuccess: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var selectedUserType by remember { mutableStateOf(UserType.PATIENT) }
    val loginError by viewModel.loginError.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Appointment Booking",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // User Type Selection
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FilterChip(
                selected = selectedUserType == UserType.PATIENT,
                onClick = { selectedUserType = UserType.PATIENT },
                label = { Text("Patient") }
            )
            FilterChip(
                selected = selectedUserType == UserType.DOCTOR,
                onClick = { selectedUserType = UserType.DOCTOR },
                label = { Text("Doctor") }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Email Field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password Field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        if (loginError != null) {
            Text(
                text = loginError!!,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Login Button
        Button(
            onClick = {
                viewModel.login(email, password, selectedUserType)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }

        // Demo credentials
        Spacer(modifier = Modifier.height(32.dp))
        Text("Demo Credentials:", style = MaterialTheme.typography.labelSmall)
        Text("Patient: patient@email.com / 123456", style = MaterialTheme.typography.labelSmall)
        Text("Doctor: doctor@email.com / 123456", style = MaterialTheme.typography.labelSmall)
    }

    // Observe login success
    LaunchedEffect(Unit) {
        viewModel.currentUser.collect { user ->
            if (user != null) {
                onLoginSuccess()
            }
        }
    }
}*/
