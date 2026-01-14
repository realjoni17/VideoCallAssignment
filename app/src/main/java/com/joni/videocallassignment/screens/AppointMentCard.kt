/*
package com.joni.videocallassignment.screens


import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

import java.text.SimpleDateFormat
import androidx.compose.ui.Alignment

import com.joni.videocallassignment.models.Appointment
import com.joni.videocallassignment.models.AppointmentStatus

@Composable
fun AppointmentCardWithCall(
    appointment: Appointment,
    currentUserId: String,
    currentUserName: String,
    otherUserId: String,
    otherUserName: String,
    showCallButton: Boolean = true,
    onCallInitiated: () -> Unit = {}
) {
    val context = LocalContext.current

    // Initialize Zego when component is created
    LaunchedEffect(currentUserId) {
        ZegoCallManager.initializeZegoUIKit(context as Application, currentUserId, currentUserName)
    }

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
                    Icons.Default.Call,
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

            if (showCallButton && appointment.status == AppointmentStatus.CONFIRMED) {
                Spacer(modifier = Modifier.height(12.dp))

                // Option 1: Using the custom button that triggers hidden call button
                CallNowButton(
                    targetUserId = otherUserId,
                    targetUserName = otherUserName,
                    onClick = onCallInitiated
                )

                // Option 2: Direct call button (less customizable)
                // ComposeCallButton(
                //     modifier = Modifier.fillMaxWidth(),
                //     targetUserId = otherUserId,
                //     targetUserName = otherUserName,
                //     onButtonCreated = { button ->
                //         // Customize button appearance
                //         button.text = "Call Now"
                //         button.setBackgroundColor(ContextCompat.getColor(context, R.color.primary))
                //     }
                // )
            }
        }
    }
}*/
