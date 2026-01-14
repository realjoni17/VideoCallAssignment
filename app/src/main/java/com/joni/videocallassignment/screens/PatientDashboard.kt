/*

package com.joni.videocallassignment.screens

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.appointmentbooking.service.ZegoCallManager
import com.joni.videocallassignment.models.Appointment
import com.joni.videocallassignment.models.AppointmentStatus
import com.joni.videocallassignment.viewModels.AppointmentViewModel

import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientDashboard(
    appointmentViewModel: AppointmentViewModel,
    userName: String,
    userId: String,
    onBookAppointment: () -> Unit,
    onCallInitiated: (String, String) -> Unit
) {
    val appointments by appointmentViewModel.appointments.collectAsState()
    val patientAppointments = appointments.filter {
        it.patientName == userName
    }
    val context = LocalContext.current

    // Initialize Zego when dashboard loads
    LaunchedEffect(userId) {
        ZegoCallManager.initializeZegoUIKit(context as Application, userId, userName)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Patient Dashboard") },
            //    subtitle = { Text("Welcome, $userName") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onBookAppointment,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Icon(Icons.Default.DateRange, contentDescription = "Book Appointment")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "Upcoming Appointments",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (patientAppointments.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            Icons.Default.DateRange,
                            contentDescription = "No appointments",
                            modifier = Modifier.size(64.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("No appointments scheduled")
                    }
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(patientAppointments) { appointment ->
                        AppointmentCardWithCall(
                            appointment = appointment,
                            currentUserId = userId,
                            currentUserName = userName,
                            otherUserId = appointment.doctorId,
                            otherUserName = appointment.doctorName,
                            onCallInitiated = {
                                onCallInitiated(appointment.id, appointment.doctorId)
                            }
                        )
                    }
                }
            }
        }
    }
}*/
