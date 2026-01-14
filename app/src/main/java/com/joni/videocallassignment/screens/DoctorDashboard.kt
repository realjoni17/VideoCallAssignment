/*

package com.joni.videocallassignment.screens

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.joni.videocallassignment.models.Appointment
import com.joni.videocallassignment.models.AppointmentStatus
import com.joni.videocallassignment.viewModels.AppointmentViewModel

import java.text.SimpleDateFormat
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorDashboard(
    appointmentViewModel: AppointmentViewModel,
    doctorName: String,
    doctorId: String,
    specialty: String?,
    onCallInitiated: (String, String) -> Unit,
    onUpdateAppointment: (Appointment) -> Unit
) {
    val appointments by appointmentViewModel.appointments.collectAsState()
    val doctorAppointments = appointments.filter {
        it.doctorName == doctorName
    }
    val context = LocalContext.current

    // Initialize Zego when dashboard loads
    LaunchedEffect(doctorId) {
        ZegoCallManager.initializeZegoUIKit(context as Application, doctorId, doctorName)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Doctor Dashboard") },
             */
/*   subtitle = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Dr. $doctorName")
                        if (!specialty.isNullOrEmpty()) {
                            Text(specialty, style = MaterialTheme.typography.labelSmall)
                        }
                    }
                }*//*

            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "Today's Appointments",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (doctorAppointments.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            Icons.Default.Call,
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
                    items(doctorAppointments) { appointment ->
                        DoctorAppointmentCardWithCall(
                            appointment = appointment,
                            doctorId = doctorId,
                            doctorName = doctorName,
                            onStartCall = {
                                onCallInitiated(appointment.id, appointment.patientId)
                            },
                            onConfirm = {
                                appointmentViewModel.updateAppointmentStatus(
                                    appointment.id,
                                    AppointmentStatus.CONFIRMED
                                )
                            },
                            onComplete = {
                                appointmentViewModel.updateAppointmentStatus(
                                    appointment.id,
                                    AppointmentStatus.COMPLETED
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DoctorAppointmentCardWithCall(
    appointment: Appointment,
    doctorId: String,
    doctorName: String,
    onStartCall: () -> Unit,
    onConfirm: () -> Unit,
    onComplete: () -> Unit
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
                        text = appointment.patientName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = SimpleDateFormat("MMM dd, yyyy HH:mm").format(appointment.dateTime),
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

            Text(
                text = "Symptoms: ${appointment.symptoms}",
                style = MaterialTheme.typography.bodyMedium
            )

            if (appointment.notes.isNotEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Notes: ${appointment.notes}",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            when (appointment.status) {
                AppointmentStatus.BOOKED -> {
                    OutlinedButton(
                        onClick = onConfirm,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Default.Check, contentDescription = "Confirm")
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Confirm Appointment")
                    }
                }
                AppointmentStatus.CONFIRMED -> {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Call button
                        CallNowButton(
                            targetUserId = appointment.patientId,
                            targetUserName = appointment.patientName,
                            onClick = onStartCall,
                            //modifier = Modifier.weight(1f)
                        )

                        // Complete button
                        OutlinedButton(
                            onClick = onComplete,
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Default.Check, contentDescription = "Complete")
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Complete")
                        }
                    }
                }
                else -> {
                    // Show status for completed/cancelled appointments
                    Text(
                        text = "Status: ${appointment.status}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}*/
