/*
package com.joni.videocallassignment.navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appointmentbooking.service.ZegoCallManager
import com.joni.videocallassignment.models.UserType
import com.joni.videocallassignment.screens.DoctorDashboard
import com.joni.videocallassignment.screens.LoginScreen
import com.joni.videocallassignment.screens.PatientDashboard
import com.joni.videocallassignment.viewModels.AppointmentViewModel
import com.joni.videocallassignment.viewModels.AuthViewModel


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
