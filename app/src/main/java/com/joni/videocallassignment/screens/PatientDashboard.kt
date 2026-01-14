/*
package com.joni.videocallassignment.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.VideoCall
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.appointmentbooking.data.Appointment
import com.example.appointmentbooking.data.AppointmentStatus
import com.example.appointmentbooking.viewmodel.AppointmentViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientDashboard(
    appointmentViewModel: AppointmentViewModel,
    userName: String,
    onBookAppointment: () -> Unit,
    onStartVideoCall: (Appointment) -> Unit
) {
    val appointments by appointmentViewModel.appointments.collectAsState()
    val patientAppointments = appointments.filter {
        it.patientName == userName
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Patient Dashboard") },
                subtitle = { Text("Welcome, $userName") }
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
                    Text("No upcoming appointments")
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(patientAppointments) { appointment ->
                        AppointmentCard(
                            appointment = appointment,
                            showCallButton = appointment.status == AppointmentStatus.CONFIRMED,
                            onCallClick = { onStartVideoCall(appointment) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AppointmentCard(
    appointment: Appointment,
    showCallButton: Boolean,
    onCallClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = appointment.doctorName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Patient: ${appointment.patientName}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Badge(
                    containerColor = when (appointment.status) {
                        AppointmentStatus.BOOKED -> MaterialTheme.colorScheme.primaryContainer
                        AppointmentStatus.CONFIRMED -> MaterialTheme.colorScheme.secondaryContainer
                        AppointmentStatus.COMPLETED -> MaterialTheme.colorScheme.tertiaryContainer
                        AppointmentStatus.CANCELLED -> MaterialTheme.colorScheme.errorContainer
                    }
                ) {
                    Text(appointment.status.name)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.CalendarMonth,
                    contentDescription = "Date",
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = SimpleDateFormat("MMM dd, yyyy HH:mm").format(appointment.dateTime),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Symptoms: ${appointment.symptoms}",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 4.dp)
            )

            if (showCallButton) {
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = onCallClick,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Icon(Icons.Default.VideoCall, contentDescription = "Call")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Call Now")
                }
            }
        }
    }
}*/
