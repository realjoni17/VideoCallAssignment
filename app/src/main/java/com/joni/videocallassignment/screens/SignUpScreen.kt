package com.joni.videocallassignment.screens

import AuthViewModel
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.joni.videocallassignment.ZegoCallButton
import com.joni.videocallassignment.models.AppUser
import com.joni.videocallassignment.models.UserRole

@Composable
fun SignUpScreen(
    viewModel: AuthViewModel,
    onSignupSuccess: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var role by remember { mutableStateOf(UserRole.PATIENT) }

    Column(Modifier.padding(24.dp)) {
        Text("Sign Up", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(email, { email = it }, label = { Text("Email") })
        OutlinedTextField(password, { password = it }, label = { Text("Password") })

        Text("Select Role")

        Row {
            RoleChip("Patient", role == UserRole.PATIENT) {
                role = UserRole.PATIENT
            }
            Spacer(Modifier.width(12.dp))
            RoleChip("Doctor", role == UserRole.DOCTOR) {
                role = UserRole.DOCTOR
            }
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.signUp(email, password, role, onSignupSuccess)
            }
        ) {
            Text("Create Account")
        }
    }
}

@Composable
fun RoleChip(text: String, selected: Boolean, onClick: () -> Unit) {
    Surface(
        modifier = Modifier.clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        color = if (selected) MaterialTheme.colorScheme.primary else Color.LightGray
    ) {
        Text(
            text,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            color = Color.White
        )
    }
}


@Composable
fun PatientHomeScreen() {
    UsersScreen()
}

@Composable
fun DoctorHomeScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Doctor Home")
       // UsersScreen()
    }
}


@Composable
fun UserItem(user: AppUser) {
    if (user.role != UserRole.DOCTOR) return

    var showCallActions by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE3F2FD) // light blue
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = "👨‍⚕️ Doctor Available",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(8.dp))

            Text(text = "Email: ${user.email}")


            Spacer(Modifier.height(12.dp))

            // ✅ Book Appointment Button
            Button(
                onClick = { showCallActions = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Book Appointment")
            }

            // ✅ Show call buttons AFTER booking
            AnimatedVisibility(visible = showCallActions) {

                Column {
                    Spacer(Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                        ZegoCallButton(targetUserId = user.uid, targetUserName = user.uid, isVideoCall = false, callType = "Audio Call")

                        ZegoCallButton(targetUserId = user.uid, targetUserName = user.uid, isVideoCall = true, callType = "Video Call")


                    }
                }
            }
        }
    }
}



@Composable
fun UsersScreen(
    viewModel: AuthViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.loadUsers()
    }

    Column(modifier = Modifier.padding(16.dp)) {

        Text(
            text = "Registered Users",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(12.dp))

        LazyColumn {
            items(viewModel.users) { user ->
                UserItem(user)
            }
        }

        viewModel.error?.let {
            Text(it, color = Color.Red)
        }
    }
}
